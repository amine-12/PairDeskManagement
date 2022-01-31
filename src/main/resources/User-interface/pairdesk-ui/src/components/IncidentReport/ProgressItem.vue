<template>

  {{progress}}
</template>

<script>
import axios from "axios";

export default {
  name: "ProgressItem",
  props:["featureId"],
  data(){
    return{
      progress:''
    }
  },
  mounted() {

    let yourConfig = {
      headers: {
        Authorization: localStorage.getItem("user-token")
      }
    }
    axios.get("http://localhost:8080/features/api/progress/" + this.featureId, yourConfig).then((resp) => {
      this.progress = resp.data;
      this.progress = this.progress.toFixed(1);
      console.log(this.progress);
    });
  }
}
</script>

<style scoped>

</style>