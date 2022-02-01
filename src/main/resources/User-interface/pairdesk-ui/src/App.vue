<template>
  <router-view/>
  <language-selector></language-selector>
</template>

<script>
import languageSelector from "./components/languageSelector"
export default {
  name: 'Home',
  components:{
    languageSelector
  }
}
</script>

<style>
.body::-webkit-scrollbar {
  width: 0.5rem;
}

.body::-webkit-scrollbar-track {
  background: #1e1e24;
}

.body::-webkit-scrollbar-thumb {
  background: #6649b8;
}
</style>