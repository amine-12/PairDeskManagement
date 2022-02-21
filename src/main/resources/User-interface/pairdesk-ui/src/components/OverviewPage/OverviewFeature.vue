<template>
  <div class="container-a">

    <div  class="row" >
      <div class="card-u col" v-if="user.roles[0] === 'ROLE_ADMIN'">
        <h3>{{ $t('featureOverview') }}</h3>
        <div style="height: 450px;overflow-y:auto">
          <ul class="responsive-table" style="padding-left: 0px;" v-if="list && list.length > 0">
            <div  v-for="feature in list" v-bind:key="feature.featureId" >
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
                <li class="table-row " style="width: 98%;margin-left: 1%">
                  <feature-item v-bind:feature-id="feature.featureId" ></feature-item>
                </li>
              </router-link>
            </div>
          </ul>
          <div v-else>
            <h3 style="text-align: center;margin-top: 10%">{{ $t('noFeatures') }}</h3>
          </div>
        </div>
      </div>

      <div class="card-u col" v-if="user.roles[0] === 'ROLE_ADMIN'">
        <h3>{{ $t('featureAssignedToYou') }}</h3>
        <div style="height: 450px;overflow-y:auto">
          <ul class="responsive-table" style="padding-left: 0px;" v-if="userSpecificList && userSpecificList.length > 0">
            <div  v-for="feature in userSpecificList" v-bind:key="feature.featureId" >
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
                <li class="table-row " style="width: 98%;margin-left: 1%">
                  <feature-item v-bind:feature-id="feature.featureId" ></feature-item>
                </li>
              </router-link>
            </div>
          </ul>
          <div v-else>
            <h3 style="text-align: center;margin-top: 25%">{{ $t('noFeatures') }}</h3>
          </div>
        </div>
      </div>
    </div>


    <div  class="row" style="margin-bottom: 2%" >
      <div class="card-u col" v-if="user.roles[0] === 'ROLE_ADMIN'">
        <h3>{{ $t('featureOverviewSchedule') }}</h3>
        <div style="height: 450px;overflow-y:auto">
          <ul class="responsive-table" style="padding-left: 0px;" v-if="list && list.length > 0">
            <div  v-for="feature in list" v-bind:key="feature.featureId" >
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
                <li class="table-row " style="width: 98%;margin-left: 1%">
                  <schedule-item v-bind:feature-id="feature.featureId" ></schedule-item>
                </li>
              </router-link>
            </div>
          </ul>
          <div v-else>
            <h3 style="text-align: center;margin-top: 10%">{{ $t('noFeatures') }}</h3>
          </div>
        </div>
      </div>

      <div class="card-u col" v-if="user.roles[0] === 'ROLE_ADMIN'">
        <h3>{{ $t('featureSchedule') }}</h3>
        <div style="height: 450px;overflow-y:auto">
          <ul class="responsive-table" style="padding-left: 0px;" v-if="userSpecificList && userSpecificList.length > 0">
            <div  v-for="feature in userSpecificList" v-bind:key="feature.featureId" >
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
                <li class="table-row " style="width: 98%;margin-left: 1%">
                  <schedule-item v-bind:feature-id="feature.featureId" ></schedule-item>
                </li>
              </router-link>
            </div>
          </ul>
          <div v-else>
            <h3 style="text-align: center;margin-top: 25%">{{ $t('noFeatures') }}</h3>
          </div>
        </div>
      </div>
    </div>



    <div  class="row" style="margin-bottom: 2%" v-if="user.roles[0] === 'ROLE_USER'">
      <div class="card-u col">
        <h3>{{ $t('featureAssignedToYou') }}</h3>
        <div style="height: 600px;overflow-y:auto">
          <ul class="responsive-table" style="padding-left: 0px;" v-if="userSpecificList && userSpecificList.length > 0">
            <div  v-for="feature in userSpecificList" v-bind:key="feature.featureId" >
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
                <li class="table-row " style="width: 98%;margin-left: 1%">
                  <feature-item v-bind:feature-id="feature.featureId" ></feature-item>
                </li>
              </router-link>
            </div>
          </ul>
          <div v-else>
            <h3 style="text-align: center;margin-top: 25%">{{ $t('noFeatures') }}</h3>
          </div>
        </div>
      </div>

      <div class="card-u col" >
        <h3>{{ $t('featureSchedule') }}</h3>
        <div style="height: 600px;overflow-y:auto">
          <ul class="responsive-table" style="padding-left: 0px;" v-if="userSpecificList && userSpecificList.length > 0">
            <div  v-for="feature in userSpecificList" v-bind:key="feature.featureId" >
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }">
                <li class="table-row " style="width: 98%;margin-left: 1%">
                  <schedule-item v-bind:feature-id="feature.featureId" ></schedule-item>
                </li>
              </router-link>
            </div>
          </ul>
          <div v-else>
            <h3 style="text-align: center;margin-top: 25%">{{ $t('noFeatures') }}</h3>
          </div>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
import axios from "axios";
import FeatureItem from "@/components/UserDetails/FeatureItem";
import ScheduleItem from "@/components/OverviewPage/ScheduleItem";
export default {
  name: "OverviewFeature",
  components: {FeatureItem, ScheduleItem},
  data() {
    return {
      list: undefined,
      userSpecificList: undefined,
      user: JSON.parse(localStorage.getItem('userInfo'))
    }
  },
  mounted() {
    let yourConfig = {
      headers: {
        Authorization: localStorage.getItem("user-token")
      }
    }
    axios.get("http://3.99.41.187/features/api/all", yourConfig).then((resp) => {
      this.list = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        console.log("token expired")
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });

    axios.get("http://3.99.41.187/features/api/users/" + this.user.userId, yourConfig).then((resp) => {
      this.userSpecificList = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        console.log("token expired")
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });

  },
}
</script>

<style scoped>
.body::-webkit-scrollbar {
  width: 0.5rem;
}

.body::-webkit-scrollbar-track {
  background: #1e1e24;
}

.body::-webkit-scrollbar-thumb {
  background: #6649b8;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica,
  Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
}

.row {
  margin: 0 -5px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}
/* RESPONSIVE */

@media (max-width: 1600px) {
  .cards {
    justify-content: center;
  }
}



.card-u {
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.25);
  width: 95%;
  min-height: 450px;
  margin: 3% 20px 20px;
}

.container-a{
  margin-left:10%;
  margin-right: 4%;
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
  /*transform: scale(1.02);*/
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
