<template>
  <div class="col" data-label="featureName">{{info.featureName}}</div>
  <div class="col" data-label="deadline">{{info.deadline}}</div>
</template>

<script>
import axios from "axios";

export default {
  name: "FeatureItem",
  props: ['featureId'],
  data(){
    return{
      info:[],
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
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
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