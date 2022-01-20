<template>
  <navigation></navigation>
  <users-list v-if="currentUser.roles[0] === 'ROLE_ADMIN'"></users-list>
</template>

<script>
import UsersList from "@/components/UsersPage/UsersList";
import Navigation from "@/components/navigation";
export default {
  name: "UsersPage",
  data(){
    return{
      currentUser: JSON.parse(localStorage.getItem('userInfo')),
    }
  },
  mounted() {
    if (this.currentUser.roles[0] === 'ROLE_USER') {
      this.$router.push('/users/' + this.currentUser.userId)
    }
  },
  components: {UsersList, Navigation}
}
</script>

<style scoped>

</style>