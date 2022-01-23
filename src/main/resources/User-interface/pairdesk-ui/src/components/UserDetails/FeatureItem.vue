<template>
  <div class="col" data-label="featureName">{{info.featureName}}</div>
  <div class="border col col-8">
    <div style="height:24px; text-align: center; background-color: #0d6efd; color: white" v-bind:style="'width: ' + progress + '%'">{{progress}}%</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "FeatureItem",
  props: ['featureId'],
  data(){
    return{
      info:[],
      progress: '',
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }
    }
  },
  mounted() {
    axios.get("http://localhost:8080/features/api/" + this.featureId,this.yourConfig).then((resp) => {
      this.info = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        console.log("token expired")
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });

    axios.get("http://localhost:8080/features/api/progress/" + this.featureId, this.yourConfig).then((resp) => {
      this.progress = resp.data;
      this.progress = this.progress.toFixed(1);
    });
  }
}
</script>

<style scoped>
.border{
  border-style: solid;
  border-color: #1e1e24;
}
</style>