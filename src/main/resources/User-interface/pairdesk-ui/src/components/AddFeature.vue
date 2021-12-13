<template>
  <h1 style="text-align: center">New Feature</h1>
  <form style="text-align: center;">
    <label>Feature Name: </label>
    <input v-model="featureName" placeholder="New Feature Name"/>
    <p></p>
    <label>Priority </label>
    <select v-model="selected">
      <option disabled value="">Please select one</option>
      <option>Low</option>
      <option>Medium</option>
      <option>High</option>
    </select>
    <p></p>
    <label>Description: </label>
    <input v-model="description" placeholder="Description"/>
    <p></p>
    <label>Deadline: </label>
    <input type="datetime-local" v-model="deadline" placeholder="New Feature Name"/>
    <p></p>
    <button type="submit" style="padding-left: 30px; padding-right: 30px; padding-top: 20px; padding-bottom: 20px; font-size: 20">Add</button>
  </form>
</template>

<script>
export default {
  name: "AddFeature"
}
</script>

<style scoped>

</style>