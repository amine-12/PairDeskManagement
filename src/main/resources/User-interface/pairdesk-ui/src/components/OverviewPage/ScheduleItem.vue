<template>
  <div v-bind:style="getStatusText(info.deadline)" class="col" data-label="featureName">{{info.featureName}}</div>
  <div v-bind:style="getStatusText(info.deadline)" class="col" data-label="deadline">{{formattedDate}}</div>
  <span v-bind:style="getStatus(info.deadline)" id="statusDot2" style="float: left;" class="dot"></span>
</template>

<script>
import axios from "axios";

export default {
  name: "FeatureItem",
  props: ['featureId'],
  data(){
    return{
      info:[],
      formattedDate:'',
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }
    }
  },
  methods: {
    getStatusText(date){
      let d = new Date(date)
      let diff = new Date().getTime() - d.getTime();

      if (diff > 0) {
        return 'color: red'
      }
    },
    getStatus(date){
      let d = new Date(date)
      let diff = new Date().getTime() - d.getTime();
      if (diff < 0) {
        return 'background-color: green;'
      }
      if (diff > 0) {
        return 'background-color: red;'
      }
    }
  },
  mounted() {
    axios.get("http://3.99.41.187:8080/features/api/" + this.featureId,this.yourConfig).then((resp) => {
      this.info = resp.data;
      const month = ["January","February","March","April","May","June","July","August","September","October","November","December"];
      let d = new Date(this.info.deadline )
      this.formattedDate = month[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear()
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
.dot {
  height: 25px;
  width: 25px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  position: relative;
  margin-top: 1%;
}
</style>