<template>
  <navigation></navigation>
<InvoiceList></InvoiceList>
</template>

<script>
import InvoiceList from "@/components/InvoicePage/InvoiceList";
import Navigation from "@/components/navigation";

export default {
  name: "InvoicePage",
  components:{InvoiceList,Navigation},
  data(){
    return{
      currentUser: JSON.parse(localStorage.getItem('userInfo')),
    }
  },
  mounted() {
    if (this.currentUser.roles[0] === 'ROLE_USER') {
      this.$router.push('/invoices/' + this.currentUser.userId)
    }
  }
}
</script>

<style scoped>

</style>