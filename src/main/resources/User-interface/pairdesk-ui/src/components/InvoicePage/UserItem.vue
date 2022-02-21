<template>
  <div class="col col-2" data-label="User">{{info.username}}</div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserItem",
  data()
  {
    return {
      info:[],
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }
    }
  },
  props: ['userId'],
  mounted() {
    axios.get("http://3.99.41.187/users/api/" + this.userId,this.yourConfig).then((resp) => {
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

</style>