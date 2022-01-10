<style>

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
  width: 100%;
  min-height: 150px;
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
  height: 45px;
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

.row {margin: 0 -5px;}

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
.dot {
  height: 25px;
  width: 25px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  position: relative;
  margin-top: 1%;
}
</style>
<template>
  <div>
    <div class="heading">
    <h1 class="heading__title">Features List</h1>
  </div>
    <div class="main-container row center" >

      <div class="column" v-for="feature in list" v-bind:key="feature.featureId">
        <div class="cards">

          <div class="card card-1">
<!--            <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }" class="heading__link "> <h2 class="card__title">{{ feature.featureName }}</h2></router-link>-->
            <h2 class="card__body">{{ feature.description }}</h2>
            <p id="id" hidden>{{feature.featureId}}</p>
            <div style="text-align: right" class="card__title" id="editDeleteButtonsDiv">
              <button id="editButton" v-on:click="getId(feature.featureId); hideShowFunction();" @click="goto()" style="background: transparent; border: none;" name="editFeatureButton">
                <img src="@/assets/pencil.png" alt="Edit Feature" style="height: 30px; width: 30px"/>
              </button>
              <button @click="deleteFeature(feature.featureId)" style="background: transparent; border: none;" name="deleteFeatureButton">
                <img src="@/assets/delete.png" alt="Delete Feature" style="height: 30px; width: 30px"/>
              </button>
              <span v-bind:style="getStatus(feature.priority)" id="statusDot" style="float: left;" class="dot"></span>
              <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }" style="float: left; margin-left: 2%;" class="heading__link "> <h2 class="card__title">{{ feature.featureName }}</h2></router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="col-xs-1" align="center" id="updateFormDiv" style="display: none">
    <form class="form-horizontal" v-on:submit.prevent="submitForm" @submit="checkForm">
      <fieldset>
        <h1>Edit Feature</h1>

        <div class="form-group" id="inputUpdateFeatureName">
          <label class="col-md-4 control-label" for="featureName">Feature Name:</label>
          <div class="col-md-4">
            <input id="featureName" name="Feature Name" type="text" placeholder="New Feature Name" class="form-control input-md" v-model="form.featureName">
            <p v-if="!featureNameIsValid" class="error-message" style="color: red">Feature Name Required</p>
          </div>
        </div>

        <div class="form-group" id="inputUpdateFeatureDescription">
          <label class="col-md-4 control-label" for="description">Description</label>
          <div class="col-md-4">
            <textarea id="description" name="description" type="text" placeholder="description" class="form-control input-md" v-model="form.description"/>
            <p v-if="!descriptionIsValid" class="error-message" style="color: red">Description Required</p>
          </div>
        </div>

        <div class="form-group" id="inputUpdateFeaturePriority">
          <label class="col-md-4 control-label" for="priority">Priority</label>
          <div class="col-md-4">
            <select id="priority" name="priority" class="form-control" v-model="form.priority">
              <option value="Low">Low</option>
              <option value="Medium">Medium</option>
              <option value="High">High</option>
            </select>
          </div>
        </div>

        <div class="form-group" id="inputUpdateFeatureDeadline">
          <label class="col-md-4 control-label" for="deadline">Deadline</label>
          <div class="col-md-4">
            <input id="deadline" name="deadline" type="datetime-local" placeholder="deadline" class="form-control input-md" v-model="form.deadline">
          </div>
        </div>

        <div class="form-group">
          <label class="col-md-4 control-label" for="submit"></label>
          <div class="col-md-4">
            <button :disabled="!formIsValid" id="submit" name="submit" class="btn btn-primary">Update Feature</button>
          </div>
        </div>

      </fieldset>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name:"FeatureList",
  data() {
    return {
      list:undefined,
      fid:undefined,
      form: {
        featureName: '',
        priority: '',
        description: '',
        deadline: ''
      },
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      },

      isVisible: false}
  },
  mounted()
  {
      axios.get("http://localhost:8080/features/api/all", this.yourConfig).then((resp) => {
        this.list = resp.data;
        console.log(this.$route.name)
      }).catch((error) => {
        if (error.response.status === 401) {
          console.log("token expired")
          this.$router.push('/login')
        }
        console.log(error)
      }).finally(() => {
    });
  },
  computed: {
    featureNameIsValid() {
      return !!this.form.featureName
    },

    descriptionIsValid() {
      return !!this.form.description
    },

    formIsValid() {
      return this.featureNameIsValid && this.descriptionIsValid
    }//may also use vuelidate in the future to perform input validation
  },
  methods: {
    // getFeatureById() {
    //   axios.get("http://localhost:8080/features/api/" + this.fid).then((resp) => {
    //     this.list = resp.data;
    //   })
    // },
    getStatus(property){
      if(property === "Low"){
        return 'background-color: green;'
      }
      if(property === "Medium"){
        return 'background-color: orange;'
      }
      return 'background-color: red;'
    },
    submitForm() {
      if(this.formIsValid) {

        console.log("form is valid")
        axios.put('http://localhost:8080/features/api/update/' + this.fid, this.form, this.yourConfig)
            .then((resp) => {
              this.form = resp.data;
              console.log(this.fid);
              console.log(resp);
            })
            .catch((error) => {
              if (error.response.status === 401) {
                console.log("token expired")
                this.$router.push('/login')
              }
              console.log(error)
            }).finally(() => {
        });
        window.location.reload()

      }else{
        console.log("form is invalid")
      }
    },
    goto() {
      window.scrollTo(0, document.body.scrollHeight);
    },
    getId(id) {
      this.fid = id;
      console.log(this.fid)
      return id;
    },
    hideShowFunction: function (){
      let x = document.getElementById("updateFormDiv");
      if (x.style.display === "none") {
        x.style.display = "block";
      } else {
        x.style.display = "none";
      }
    },
    deleteFeature(featureId) {
      let confirmed = confirm("Are you sure you would like to delete this feature ");

      if(confirmed){
      console.log("form is valid")

      axios.delete('http://localhost:8080/features/api/' + featureId, this.yourConfig)
          .then((resp) => {
            this.form = resp.data;
            console.log(this.form);
          }).catch((error) => {
        if (error.response.status === 401) {
          console.log("token expired")
          this.$router.push('/login')
        }
        console.log(error)
      }).finally(() => {
      });
        window.location.reload()
      }
    }
  }
}
</script>
