<template>
  <navigation></navigation>
  <feature-details></feature-details>
  <floating-button ></floating-button>
</template>

<script>
import FeatureDetails from "@/components/FeatureDetailsPage/FeatureDetails";
import FloatingButton from "@/components/FeatureDetailsPage/FloatingButton";
import Navigation from "@/components/navigation";

export default {
  name: "FeatureDetailPage",
  components: {FeatureDetails, FloatingButton, Navigation}
}
</script>

<style scoped>

</style>