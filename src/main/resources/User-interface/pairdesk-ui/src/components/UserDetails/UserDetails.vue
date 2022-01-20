<template>

  <div class="container-a" style="margin-left:12%;margin-right: 5%; height: 150%">
    <h1 style="float: none">User Profile</h1>
    <div class="container-b">
      <div class="card-u">
        <p style="font-size: larger" ><span style="font-weight: bold">Username: </span>{{ info.username }}</p>
        <p><span style="font-weight: bold">Email: </span>{{info.email}}</p>
        <p><span style="font-weight: bold">Account Created at:</span> {{formattedDate}}</p>
      </div>
      <div class="card-u">
        <h3>Assigned Features</h3>
        <ul class="responsive-table" style="padding-left: 0px;">
          <div v-for="feature in featuresList" v-bind:key="feature.featureId">
            <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
              <li class="table-row " >
                <feature-item v-bind:feature-id="feature.featureId"></feature-item>
              </li>
            </router-link>
          </div>
        </ul>
      </div>
    </div>

    <div class="container-c">
      <div class="card-i" >
        <h3 style="float: left">Invoice</h3>
        <p style="float: left"><span style="font-weight: bold">Generated:</span> <span style="font-size: small">Tue Jan 18 2022 17:48:25 GMT-0500 (Eastern Standard Time)</span></p>
        <p style="float: left"><span style="font-weight: bold">Pay Period:</span> Tue Jan 18 2022 to Tue Jan 30 2022</p>

      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import FeatureItem from "@/components/UserDetails/FeatureItem";
export default {
  name: "UserDetails",
  components: {FeatureItem},
  data()
  {
    return {
      info:[],
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
          console.log("token expired")
          this.$router.push('/login')
        }
        console.log(error)
      }).finally(() => {

      });

      axios.get("http://localhost:8080/features/api/users/" + this.$route.params.userId,this.yourConfig).then((resp) => {
          this.featuresList = resp.data;
      }).catch((error) => {
        if (error.response.status === 401) {
          console.log("token expired")
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