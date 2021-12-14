<template>
  <div class="about">
    <feature-list></feature-list>
  </div>
  <AddFeature/>
</template>

<script>
import FeatureList from "@/components/FeatureList";
import AddFeature from "@/components/AddFeature";
export default {
  name: "FeaturesPage",
  components: {FeatureList,AddFeature}
}
</script>

<style scoped>

</style>