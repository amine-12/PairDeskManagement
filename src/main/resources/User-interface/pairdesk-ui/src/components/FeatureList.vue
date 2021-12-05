<template>
  <div>
    <h1 style="text-align: center">Feature List</h1>
    <div v-for="feature in list" v-bind:key="feature.id">
      <h2>{{ feature.featureName }}</h2>
      <h2>{{ feature.priority }}</h2>
    </div>
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
      axios.get("http://localhost:8080/features/api/all", {
        headers: {
          'Access-Control-Allow-Origin': '*',
        },
          }).then((resp) => {
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
<!--<template>-->
<!--  <div>-->
<!--    <div v-for="feature in features" v-bind:key="feature.id">-->
<!--      <h2>{{ feature.featureName }}</h2>-->
<!--      <p>{{ feature.description }}</p>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      features: [],-->
<!--    };-->
<!--  },-->

<!--  methods: {-->
<!--    async getData() {-->
<!--      try {-->
<!--        const response = await this.$http.get(-->
<!--            "http://localhost:8080/features/api/all"-->
<!--        );-->
<!--        // JSON responses are automatically parsed.-->
<!--        this.features = response.data;-->
<!--        console.log(this.features);-->
<!--      } catch (error) {-->
<!--        console.log(error);-->
<!--      }-->
<!--    },-->
<!--  },-->

<!--  created() {-->
<!--    this.getData();-->
<!--  },-->
<!--};-->
<!--</script>-->