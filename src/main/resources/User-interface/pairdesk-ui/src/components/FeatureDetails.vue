<template>
<h1>{{ list }}</h1>
</template>

<script>
import axios from "axios";
export default {
  name: "FeatureDetails",
  data()
  {
    return {list:undefined}
  },
  mounted()
  {
    try {
      axios.get("http://localhost:8080/features/api/" + this.$route.params.featureId).then((resp) => {
        this.list = resp.data;
        console.log(this.list)
        console.log(this.$route.params.featureId)
      })
    }
    catch(error){
      console.log(error)
    }
  }
}
</script>

<style scoped>

</style>