<template>
  <navigation></navigation>
  <user-details></user-details>
</template>

<script>
import Navigation from "@/components/navigation";
import UserDetails from "@/components/UserDetails/UserDetails";

export default {
  name: "UserDetailsPage",
  components: {Navigation, UserDetails}
}
</script>

<style scoped>

</style>