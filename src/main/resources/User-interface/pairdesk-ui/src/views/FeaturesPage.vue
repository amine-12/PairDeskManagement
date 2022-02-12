<template>
  <div id="nav">
    <navigation/>
  </div>
  <div class="about">
    <div class="heading">
      <h1 class="heading__title">{{$t('FeaturesList')}}</h1>
    </div>
    <AddFeature/>
    <feature-list></feature-list>
  </div>

</template>

<script>
import FeatureList from "@/components/FeatureListPage/FeatureList";
import AddFeature from "@/components/FeatureListPage/AddFeature";
import Navigation from "@/components/navigation";

export default {
  name: "FeaturesPage",
  components: {FeatureList,AddFeature,Navigation}
}
</script>

<style scoped>

</style>