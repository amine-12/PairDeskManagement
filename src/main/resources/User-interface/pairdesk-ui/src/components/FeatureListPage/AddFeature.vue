<template>
  <p></p>

  <div class="col-xs-1" align="center">
    <label class="col-md-4 control-label" for="submit"></label>
    <div class="col-md-4">
      <button v-on:click="hideShowFunction()" @click="goto()" id="viewFeatureCreationForm" name="viewFeatureCreationForm" class="btn btn-primary" v-if="currentUser.roles[0] === 'ROLE_ADMIN'">{{$t('AddNewFeature')}}</button>
    </div>
  </div>
  <p></p>

  <div class="col-xs-1" align="center" id="addFeatureForm" style="display: none">
  <form class="form-horizontal" v-on:submit.prevent="submitForm" @submit="checkForm">
    <fieldset>
      <h1>{{$t('NewFeatureFormHeader')}}</h1>

      <div class="form-group" id="inputFeatureName">
        <label class="col-md-4 control-label" for="featureName">{{$t('InputFeatureName')}}</label>
        <div class="col-md-4">
          <input id="featureName" name="Feature Name" type="text" placeholder="New Feature Name" class="form-control input-md" v-model="form.featureName">
          <p v-if="!featureNameIsValid" class="error-message" style="color: red">{{$t('InputRequiredFeatureName')}}</p>
        </div>
      </div>

      <div class="form-group" id="inputFeatureDescription">
        <label class="col-md-4 control-label" for="description">{{$t('InputDescription')}}</label>
        <div class="col-md-4">
          <textarea id="description" name="description" type="text" placeholder="description" class="form-control input-md" v-model="form.description"/>
          <p v-if="!descriptionIsValid" class="error-message" style="color: red">{{$t('InputRequiredDescription')}}</p>
        </div>
      </div>

      <div class="form-group" id="inputFeaturePriority">
        <label class="col-md-4 control-label" for="priority">{{$t('InputPriority')}}</label>
        <div class="col-md-4">
          <select id="priority" name="priority" class="form-control" v-model="form.priority">
            <option value="Low">{{$t('InputPriorityLow')}}</option>
            <option value="Medium">{{$t('InputPriorityMedium')}}</option>
            <option value="High">{{$t('InputPriorityHigh')}}</option>
          </select>
        </div>
      </div>

      <div class="form-group" id="inputFeatureDeadline">
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

      <div class="form-group" id="inputFeatureSubmit">
        <label class="col-md-4 control-label" for="submit"></label>
        <div class="col-md-4">
          <button :disabled="!formIsValid" id="submit" name="submit" class="btn btn-primary">{{$t('SubmitFeatureButton')}}</button>
        </div>
      </div>

    </fieldset>
  </form>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "AddFeature",
  data() {
    return {
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      },
      form: {
        featureName: '',
        price: '',
        priority: '',
        description: '',
        deadline: '',
        userId: ''
      },
      currentUser: JSON.parse(localStorage.getItem('userInfo')),
      users: [],
      isVisible: false
    }
  },
  mounted(){
    try {
      axios.get("http://localhost:8080/users/api/all", this.yourConfig).then((resp) => {
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
    }//may also use vuelidate in the future to perform input validation
  },
  methods: {
    submitForm() {
      if(this.formIsValid && this.userIsValid && this.priceIsValid) {
        axios.post('http://localhost:8080/features/api/add', this.form, this.yourConfig)
            .then((resp) => {
              this.form = resp.data;
            })
            .catch((error) => {
              if (error.response.status === 401) {
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
    hideShowFunction: function (){
      let x = document.getElementById("addFeatureForm");
      if (x.style.display === "none") {
        x.style.display = "block";
      } else {
        x.style.display = "none";
      }
    },
    goto() {
      window.scrollTo(0, document.body.scrollHeight);
    }
  }
}
</script>

<style scoped>
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