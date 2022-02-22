<style >

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

::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #2b3d8c;
}
</style>
<template>
  <div>

    <div class="main-container row center" v-if="currentUser.roles[0] === 'ROLE_ADMIN'">

      <div class="column" v-for="feature in list" v-bind:key="feature.featureId">
        <div class="cards">

          <div class="card card-1">
<!--            <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }" class="heading__link "> <h2 class="card__title">{{ feature.featureName }}</h2></router-link>-->
            <h2 class="card__body">{{ feature.description }}</h2>
            <p id="id" hidden>{{feature.featureId}}</p>
            <div style="text-align: right" class="card__title" id="editDeleteButtonsDiv">
              <button id="editButton" v-on:click="getId(feature.featureId); hideShowFunction(); prefillUpdateForm()" @click="goto()" style="background: transparent; border: none;" name="editFeatureButton" v-if="currentUser.roles[0] === 'ROLE_ADMIN'">
                <img src="@/assets/pencil.png" alt="Edit Feature" style="height: 30px; width: 30px"/>
              </button>
              <button @click="deleteFeature(feature.featureId)" style="background: transparent; border: none;" name="deleteFeatureButton" id="deleteFeatureButtonId" v-if="currentUser.roles[0] === 'ROLE_ADMIN'">
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

  <div class="main-container row center" v-if="currentUser.roles[0] === 'ROLE_USER'">
    <div class="column" v-for="feature in userSpecificList" v-bind:key="feature.featureId">
      <div class="cards">

        <div class="card card-1">
          <!--            <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }" class="heading__link "> <h2 class="card__title">{{ feature.featureName }}</h2></router-link>-->
          <h2 class="card__body">{{ feature.description }}</h2>
          <p id="id2" hidden>{{feature.featureId}}</p>
          <div style="text-align: right" class="card__title" id="editDeleteButtonsDiv2">
            <span v-bind:style="getStatus(feature.priority)" id="statusDot2" style="float: left;" class="dot"></span>
            <router-link :to="{ name: 'FeaturesDetail', params: { featureId: feature.featureId } }" style="float: left; margin-left: 2%;" class="heading__link "> <h2 class="card__title">{{ feature.featureName }}</h2></router-link>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="col-xs-1" align="center" id="updateFormDiv" style="display: none">
    <form class="form-horizontal" v-on:submit.prevent="submitForm" @submit="checkForm">
      <fieldset>
        <h1>{{$t('EditFeatureFormHeader')}}</h1>

        <div class="form-group" id="inputUpdateFeatureName">
          <label class="col-md-4 control-label" for="featureName">{{$t('InputFeatureName')}}</label>
          <div class="col-md-4">
            <input id="featureName" name="Feature Name" type="text" placeholder="New Feature Name" class="form-control input-md" v-model="form.featureName">
            <p v-if="!featureNameIsValid" class="error-message" style="color: red">{{$t('InputRequiredFeatureName')}}</p>
          </div>
        </div>

        <div class="form-group" id="inputUpdateFeatureDescription">
          <label class="col-md-4 control-label" for="description">{{$t('InputDescription')}}</label>
          <div class="col-md-4">
            <textarea id="description" name="description" type="text" placeholder="description" class="form-control input-md" v-model="form.description"/>
            <p v-if="!descriptionIsValid" class="error-message" style="color: red">{{$t('InputRequiredDescription')}}</p>
          </div>
        </div>

        <div class="form-group" id="inputUpdateFeaturePriority">
          <label class="col-md-4 control-label" for="priority">{{$t('InputPriority')}}</label>
          <div class="col-md-4">
            <select id="priority" name="priority" class="form-control" v-model="form.priority">
              <option value="Low">{{$t('InputPriorityLow')}}</option>
              <option value="Medium">{{$t('InputPriorityMedium')}}</option>
              <option value="High">{{$t('InputPriorityHigh')}}</option>
            </select>
          </div>
        </div>

        <div class="form-group" id="inputUpdateFeatureDeadline">
          <label class="col-md-4 control-label" for="deadline">{{$t('InputDeadline')}}</label>
          <div class="col-md-4">
            <input id="deadline" name="deadline" type="datetime-local" placeholder="deadline" class="form-control input-md" v-model="form.deadline">
          </div>
        </div>

        <div class="form-group" id="inputFeatureUser">
          <label class="col-md-4 control-label" for="priority">{{$t('InputAssignUser')}}</label>
          <div class="col-md-4">
            <select id="user" name="user" class="form-control" v-model="form.userId">
              <option v-for="user in users" v-bind:key="user.userId" v-bind:value="user.userId">{{ user.username }}</option>
            </select>
            <p v-if="!userIsValid" class="error-message" style="color: red">{{$t('InputRequiredUser')}}</p>
          </div>
        </div>

        <div class="form-group" id="inputPrice">
          <label class="col-md-4 control-label" for="price">{{$t('InputPrice')}}</label>
          <div class="col-md-4">
            <input id="price" name="Price" type="text" placeholder="Price" class="form-control input-md" v-model="form.price">
            <p v-if="!priceIsValid" class="error-message" style="color: red">{{$t('InputRequiredPrice')}}</p>
          </div>
        </div>

        <div class="form-group">
          <label class="col-md-4 control-label" for="submit"></label>
          <div class="col-md-4">
            <button :disabled="!formIsValid" id="submit" name="submit" class="btn btn-primary">{{$t('SubmitEditButton')}}</button>
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
      userSpecificList: undefined,
      fid:undefined,
      form: {
        featureName: '',
        price: '',
        priority: '',
        description: '',
        deadline: '',
        userId: ''
      },
      currentUser: JSON.parse(localStorage.getItem('userInfo')),
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      },
      users: [],
      isVisible: false}
  },
  mounted()
  {

      axios.get("http://3.99.41.187:8080/features/api/all", this.yourConfig).then((resp) => {
        this.list = resp.data;
        console.log(this.$route.name)
      }).catch((error) => {
        if (error.response.status === 401) {
          this.$router.push('/login')
        }
        console.log(error)
      }).finally(() => {
    });

    axios.get("http://3.99.41.187:8080/features/api/users/" + this.currentUser.userId, this.yourConfig).then((resp) => {
      this.userSpecificList = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });

    try {
      axios.get("http://3.99.41.187:8080/users/api/all", this.yourConfig).then((resp) => {
        this.users = resp.data;
      })
    }
    catch(error){
      console.log(error)
    }
  },
  computed: {
    featureNameIsValid() {
      return !!this.form.featureName
    },

    descriptionIsValid() {
      return !!this.form.description
    },

    userIsValid(){
      return !!this.form.userId
    },

    priceIsValid(){
      return !!this.form.price
    },

    formIsValid() {
      return this.featureNameIsValid && this.descriptionIsValid
    }
  },
  methods: {

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
      if(this.formIsValid && this.userIsValid && this.priceIsValid) {

        console.log("form is valid")
        axios.put('http://3.99.41.187:8080/features/api/update/' + this.fid, this.form, this.yourConfig)
            .then((resp) => {
              this.form = resp.data;
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
    prefillUpdateForm(){

      axios.get("http://3.99.41.187:8080/features/api/" + this.fid, this.yourConfig).then((resp) => {
        this.form = resp.data;
        //Will perhaps use momentJs to reformat the deadline datetime saved format.
      }).catch((error) => {
        if (error.response.status === 401) {
          this.$router.push('/login')
        }
        console.log(error)
      }).finally(() => {
      });
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
      this.$swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.delete('http://3.99.41.187:8080/features/api/' + featureId, this.yourConfig)
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
          this.$swal.fire(
              'Deleted!',
              'The feature has been deleted.',
              'success'
          ).then((result2) => {
            if (result2.isConfirmed) {
              window.location.reload()
            }
          })
        }
      })
    }
  }
}
</script>
