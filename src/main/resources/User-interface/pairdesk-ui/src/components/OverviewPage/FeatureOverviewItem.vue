<template>

  <div data-label="featureName">{{info.featureName}}</div>

  <div class="progress">
    <div class="progress-bar bg-info" role="progressbar" v-bind:style="'width: ' + progress + '%'" aria-valuemin="0" aria-valuemax="100">{{progress}}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "FeatureOverviewItem",
  props: ['featureId'],
  data(){
    return{
      progress:undefined,
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

    axios.get("http://3.99.41.187/features/api/" + this.featureId,this.yourConfig).then((resp) => {
      this.info = resp.data;
      console.log(this.featureId)
    }).catch((error) => {
      if (error.response.status === 401) {
        console.log("token expired")
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });

    axios.get("http://3.99.41.187/features/api/progress/" + this.featureId, this.yourConfig).then((resp) => {
      this.progress = resp.data;
      this.progress = this.progress.toFixed(1);
      console.log(this.progress);
    });
  }
}
</script>

<style scoped>

</style>