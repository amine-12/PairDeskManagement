<template>
  <p></p>

  <div class="col-xs-1" align="center">
    <label class="col-md-4 control-label" for="submit"></label>
    <div class="col-md-4">
      <button v-on:click="isVisible = !isVisible" @click="goto()" id="viewFeatureCreationForm" name="viewFeatureCreationForm" class="btn btn-primary">Add a new feature</button>
    </div>
  </div>
  <p></p>

  <div class="col-xs-1" align="center" v-show="isVisible">
  <form class="form-horizontal" v-on:submit.prevent="submitForm" @submit="checkForm">
    <fieldset>
      <h1>New Feature</h1>

      <div class="form-group">
        <label class="col-md-4 control-label" for="featureName">Feature Name:</label>
        <div class="col-md-4">
          <input id="featureName" name="Feature Name" type="text" placeholder="New Feature Name" class="form-control input-md" v-model="form.featureName">
          <p v-if="!featureNameIsValid" class="error-message" style="color: red">Feature Name Required</p>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label" for="description">Description</label>
        <div class="col-md-4">
          <input id="description" name="description" type="text" placeholder="description" class="form-control input-md" v-model="form.description">
          <p v-if="!descriptionIsValid" class="error-message" style="color: red">Description Required</p>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label" for="priority">Priority</label>
        <div class="col-md-4">
          <select id="priority" name="priority" class="form-control" v-model="form.priority">
            <option value="">Low</option>
            <option value="">Medium</option>
            <option value="">High</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label" for="deadline">Deadline</label>
        <div class="col-md-4">
          <input id="deadline" name="deadline" type="datetime-local" placeholder="deadline" class="form-control input-md" v-model="form.deadline">
        </div>
      </div>

      <div class="form-group">
        <label class="col-md-4 control-label" for="submit"></label>
        <div class="col-md-4">
          <button :disabled="!formIsValid" id="submit" name="submit" class="btn btn-primary">Create Feature</button>
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
      form: {
        featureName: '',
        priority: 'TEST',
        description: '',
        deadline: ''
      },
      isVisible: false
    }
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
    submitForm() {
      if(this.formIsValid) {
        console.log("form is valid")
        axios.post('http://localhost:8080/features/api/add', this.form)
            .then((resp) => {
              this.form = resp.data;
              console.log(this.form);
            })
            .catch((error) => {
              console.log(error)
            }).finally(() => {
        });
      }else{
        console.log("form is invalid")
      }
    },
    goto() {
      window.scrollTo(0, document.body.scrollHeight);
    }
  }
}
</script>

<style scoped>

</style>