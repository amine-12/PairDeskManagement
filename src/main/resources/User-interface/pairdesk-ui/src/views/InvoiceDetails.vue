<template>
  <navigation></navigation>
  <invoice-detail></invoice-detail>
</template>

<script>
import InvoiceDetail from "@/components/InvoiceDetails/InvoiceDetail.vue";
import Navigation from "@/components/navigation";
export default {
  name: "InvoiceDetails",
  components:{Navigation,InvoiceDetail}
}
</script>

<style scoped>

</style>