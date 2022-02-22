<template>
  <div class="col col-3" data-label="Creation">{{ date }}</div>
</template>

<script>
import axios from "axios";

export default {
  name: "DateItem",
  props: ['userId'],
  data()
  {
    return {
      invoice:[],
      date: '',
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }
    }
  },
  mounted() {
    axios.get("http://3.99.41.187:8080/invoices/api/user/" + this.userId,this.yourConfig).then((resp) => {
      this.invoice = resp.data;
      let d = new Date(this.invoice.creationTime )
      this.date = (d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear()
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    })
  }
}
</script>

<style scoped>

</style>