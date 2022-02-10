<template class="body">
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

::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #2b3d8c;
}
</style>