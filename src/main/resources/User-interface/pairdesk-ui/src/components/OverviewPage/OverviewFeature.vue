<template>
  <div class="main-container row center">
    <div class="cards">
      <h1>{{ this.user.username }}</h1>

    </div>
    <div class="heading">
      <h1 class="heading__title">Overview Panel</h1>
    </div>


    <div style="overflow-y:auto;" class="card card-1">

    <table class="table table">
      <thead class="thead-dark">
      <tr>
        <th scope="col">Feature Overview</th>
      </tr>
      </thead>
      <tr v-for="feature in list" v-bind:key="feature.featureId">

        <td>{{ feature.featureName }}</td>

        <td>{{ feature.description }}</td>
        <!--  <div id="id" hidden>{{feature.featureId}}</div>-->
        <td> progression here</td>
      </tr>
    </table>
    </div>



  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "OverviewFeature",
  data() {
    return {
      list: undefined,
      user: JSON.parse(localStorage.getItem('userInfo'))
    }
  },
  mounted() {
    let yourConfig = {
      headers: {
        Authorization: localStorage.getItem("user-token")
      }
    }
    axios.get("http://localhost:8080/features/api/all", yourConfig).then((resp) => {
      this.list = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        console.log("token expired")
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });

  },
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica,
  Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
}

/*.main-container {*/
/*  padding-left: 20%;*/
/*}*/

/* HEADING */

.heading {
  text-align: center;
}

.heading__title {
  font-weight: 600;
}

.heading__credits {
  margin: 10px 0px;
  color: #888888;
  font-size: 25px;
  transition: all 0.5s;
}

.heading__link {
  text-decoration: none;
}

.heading__credits .heading__link {
  color: inherit;
}

/* CARDS */

.cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.card {
  margin: 20px;
  padding: 20px;
  width: 500px;
  min-height: 350px;
  display: grid;
  grid-template-rows: 20px 50px 1fr 50px;
  border-radius: 10px;
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.25);
  transition: all 0.2s;
}

.card:hover {
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.4);
  transform: scale(1.01);
}

.card__link,
.card__exit,
.card__icon {
  position: relative;
  text-decoration: none;
  color: rgba(0, 0, 0, 0.9);
}

.card__link::after {
  position: absolute;
  top: 25px;
  left: 0;
  content: "";
  width: 0%;
  height: 3px;
  background-color: rgba(255, 255, 255, 0.6);
  transition: all 0.5s;
}

.card__link:hover::after {
  width: 100%;
}

/*.card__exit {*/
/*  grid-row: 1/2;*/
/*  justify-self: end;*/
/*}*/

/*.card__icon {*/
/*  grid-row: 2/3;*/
/*  font-size: 30px;*/
/*}*/

.card__title {
  grid-row: 1;
  font-weight: 400;
  color: #5e5fb8;
}

.card__body {
  grid-row: 3;
  font-size: medium;
  font-weight: 400;
  color: #5e5fb8;
}

.card__apply {
  grid-row: 4;
  align-self: center;
}

/* CARD BACKGROUNDS */

.card-1 {
  background: #dfe2f0;
}

.card-2 {
  background: radial-gradient(#fbc1cc, #fa99b2);
}

.card-3 {
  background: radial-gradient(#76b2fe, #b69efe);
}

.card-4 {
  background: radial-gradient(#60efbc, #58d5c9);
}

.card-5 {
  background: radial-gradient(#f588d8, #c0a3e5);
}

.column {
  float: left;
  width: 50%;
  padding: 0 10px;
}

.row {
  margin: 0 -5px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}


.center {
  margin: auto;
  width: 70%;
  padding: 10px;
}

/* RESPONSIVE */

@media (max-width: 1600px) {
  .cards {
    justify-content: center;
  }
}

</style>
