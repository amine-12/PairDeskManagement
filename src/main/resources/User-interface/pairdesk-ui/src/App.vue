<template>
  <navigation />
  <FeatureList/>
</template>

<script>
import Navigation from './components/navigation.vue'
import FeatureList from "@/components/FeatureList";


export default {
  name: 'App',
  components: {
    FeatureList,
    Navigation
  }
}
</script>
