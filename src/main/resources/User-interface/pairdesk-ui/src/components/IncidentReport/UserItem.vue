<template>

  {{tableUserName}}
</template>

<script>
import axios from "axios";

export default {
  name: "UserItem",
  props:["userId"],
data(){
  return{
      tableUserName:''
  }
},
  mounted() {

    let yourConfig = {
      headers: {
        Authorization: localStorage.getItem("user-token")
      }
    }
    axios.get("http://3.99.41.187:8080/features/api/user/username/" + this.userId, yourConfig).then((resp) => {
      this.tableUserName = resp.data;

    })
  }
}
</script>

<style scoped>

</style>