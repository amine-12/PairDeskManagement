<template>
  <div id="nav">
    <navigation/>
  </div>
  <div class="about">
    <feature-summary></feature-summary>
  </div>
</template>

<script>
import Navigation from "@/components/navigation";
import FeatureSummary from "@/components/ReportsPage/FeatureSummary";

export default {
  name: "ReportsPage",
  components: {Navigation,FeatureSummary}
}
</script>

<style scoped>

</style>