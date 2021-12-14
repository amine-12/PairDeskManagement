<template>
  <div id="nav">
    <navigation/>
  </div>
  <router-view/>
</template>

<script>
import Navigation from './components/navigation.vue'


export default {
  name: 'Home',
  components: {
    Navigation
  }
}
</script>
