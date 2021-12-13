<template>
  <div id="nav">
    <navigation/>
  </div>
  <router-view/>
  <AddFeature/>
</template>

<script>
import Navigation from './components/navigation.vue'
import FeatureList from "@/components/FeatureList";
import AddFeature from "@/components/AddFeature";


export default {
  name: 'Home',
  components: {
    AddFeature,
    FeatureList,
    Navigation
  }
}
</script>
