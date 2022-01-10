<template>
  <navigation></navigation>
  <users-list></users-list>
</template>

<script>
import UsersList from "@/components/UserListPage/UsersList";
import Navigation from "@/components/navigation";
export default {
  name: "UsersPage",
  components: {UsersList, Navigation}
}
</script>

<style scoped>

</style>