<template>
  <router-view/>
  <div id="nav">
    <navigation v-if="this.$route.name !== 'Login'"></navigation>
  </div>

</template>

<script>

import Navigation from "@/components/navigation";
export default {
  name: 'Home',
  components: {Navigation}
}
</script>
