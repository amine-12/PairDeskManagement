<template>
  <div class="home">
    <h1 style="margin-left: 10%;margin-top: 2%">{{$t('overview') }}</h1>
    <navigation></navigation>
    <overview-feature></overview-feature>
  </div>

</template>

<script>
// @ is an alias to /src
import OverviewFeature from "@/components/OverviewPage/OverviewFeature";
import Navigation from "@/components/navigation";
export default {
  name: 'Home',
  components: {OverviewFeature, Navigation},
}
</script>