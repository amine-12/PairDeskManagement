<template>

  <div class="container-a" style="margin-left:12%;margin-right: 5%; height: 150%">
    <h1 style="float: none">User Profile</h1>
    <div class="container-b">
      <div class="card-u">
        <p style="font-size: larger" ><span style="font-weight: bold">Username: </span>{{ info.username }}</p>
        <p><span style="font-weight: bold">Email: </span>{{info.email}}</p>
        <p><span style="font-weight: bold">Account Created at:</span> {{formattedDate}}</p>
      </div>
      <div class="card-u" style="height: 325px;overflow-y:auto">
        <h3>Assigned Features</h3>
        <ul class="responsive-table" style="padding-left: 0px;" v-if="featuresList && featuresList.length > 0">
          <div  v-for="feature in featuresList" v-bind:key="feature.featureId" >
            <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
              <li class="table-row " >
                <feature-item v-bind:feature-id="feature.featureId"></feature-item>
              </li>
            </router-link>
          </div>
        </ul>
        <div v-else>
          <h3 style="text-align: center;margin-top: 10%">No Assigned Features</h3>
        </div>
      </div>
    </div>

    <div class="container-c">
      <div class="card-i" >
        <invoice></invoice>

      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import FeatureItem from "@/components/UserDetails/FeatureItem";
import Invoice from "@/components/UserDetails/Invoice";
export default {
  name: "UserDetails",
  components: {Invoice, FeatureItem},
  data()
  {
    return {
      info:[],
      isListEmpty: false,
      featuresList:[],
      mapped: null,
      formattedDate: '',
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }

    }
  },
  mounted() {
    axios.get("http://localhost:8080/users/api/" + this.$route.params.userId,this.yourConfig).then((resp) => {
      this.info = resp.data;
      this.formattedDate = new Date(this.info.creationTime)
      console.log(this.info)
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {

    });

    axios.get("http://localhost:8080/features/api/users/" + this.$route.params.userId,this.yourConfig).then((resp) => {
        this.featuresList = resp.data;
        if(this.featuresList == null){
          this.isListEmpty = true
        }
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {

    });
  }
}
</script>

<style scoped>

.card-u {
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.25);
  margin-top: 3%;
  width: 95%;
}
.card-i {
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.25);
  margin-top:3%;
  height: 515px;
  width: 95%;
}

.container-a{
  margin-left:10%;
  margin-right: 6%;
}
.container-b{
  width: 60%;
  margin: 0;
  float: left;
}

.container-c{
  width: 40%;
  float: right;
}

.responsive-table li {
  border-radius: 3px;
  padding: 15px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 25px;
}
.responsive-table .table-header {
  background-color: #2b3d8c;
  font-size: 14px;
  text-transform: uppercase;
  letter-spacing: 0.03em;
  color: white;
  margin-top: 2%;
}
.responsive-table .table-row {
  background-color: #fff;
  box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);

}

a{
  text-decoration: none;
  color: #1e1e24;
  font-size: medium;
}

.table-row:hover {
  transform: scale(1.02);
}

@media all and (max-width: 600px) {
  .responsive-table .table-header {
    display: none;
  }
  .responsive-table li {
    display: block;
  }
  .responsive-table .col {
    flex-basis: 100%;
  }
  .responsive-table .col {
    display: flex;
    padding: 10px 0;
  }
  .responsive-table .col:before {
    color: #6c7a89;
    padding-right: 10px;
    content: attr(data-label);
    flex-basis: 50%;
    text-align: right;
  }
}

</style>