<template>
  <div id="nav">
    <navigation/>
  </div>
  <div class="about">
    <feature-list></feature-list>
  </div>
  <AddFeature/>
</template>

<script>
import FeatureList from "@/components/FeatureListPage/FeatureList";
import AddFeature from "@/components/FeatureListPage/AddFeature";
export default {
  name: "FeaturesPage",
  components: {FeatureList,AddFeature}
}
</script>

<style scoped>

</style>