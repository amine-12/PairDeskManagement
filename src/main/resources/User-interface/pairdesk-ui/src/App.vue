<template>
  <router-view/>

  <!--<select style=" position: absolute;
  top: 8px;
  right: 16px;
  font-size: 18px;" v-model="lang">
    <option value="en">English</option>
    <option value="fr">Francais</option>
  </select>-->
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
