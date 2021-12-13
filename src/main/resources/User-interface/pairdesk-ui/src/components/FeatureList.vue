<style>
table.GeneratedTable {
  width: 55%;
  background-color: #ffffff;
  border-collapse: collapse;
  border-width: 2px;
  border-color: #000000;
  border-style: double;
  color: #000000;
}

table.GeneratedTable td, table.GeneratedTable th {
  border-width: 2px;
  border-color: #000000;
  border-style: double;
  padding: 3px;
}

table.GeneratedTable thead {
  background-color: #94afff;
}
</style>
<template>
  <div>
    <h1 style="text-align: center">Features List</h1>
    <table style="margin-left: 25%" class="GeneratedTable" >
      <thead>
      <tr>
        <th>Feature Name</th>
        <th>Priority</th>
        <th>Description</th>
        <th>Progress</th>
        <th>deadline</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="feature in list" v-bind:key="feature.id">
        <td>{{ feature.featureName }}</td>
        <td>{{ feature.priority }}</td>
        <td>{{ feature.description }}</td>
        <td>{{ feature.progress }}%</td>
        <td>{{ feature.deadline }}</td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name:"FeatureList",
  data()
  {
    return {list:undefined}
  },
  mounted()
  {
    try {
      axios.get("http://localhost:8080/features/api/all").then((resp) => {
        this.list = resp.data;
        console.log(this.list)
      })
    }
    catch(error){
      console.log(error)
    }
  }
}
</script>
