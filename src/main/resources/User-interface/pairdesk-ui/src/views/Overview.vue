<template>
  <div class="home">
    <h1 style="margin-left: 10%;margin-top: 2%">Overview</h1>
    <overview-feature></overview-feature>
  </div>
</template>

<script>
// @ is an alias to /src
import OverviewFeature from "@/components/OverviewFeature";
export default {
  name: 'Home',
  components: {OverviewFeature}
}
</script>
