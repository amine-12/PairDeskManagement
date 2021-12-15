<template>
<button id="myBtn" v-on:click="displayComponent">
  <a class="float">
    <i class="fa fa-plus my-float"></i>
  </a>
</button>
  <div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <span class="close">&times;</span>

      </div>
      <div class="modal-body">
        <form class="form-horizontal" v-on:submit.prevent="submitForm" @submit="checkForm">
          <fieldset>
            <h1>New Task</h1>
            <div class="form-group">
              <label class="col-md-4 control-label" for="taskName">Task Name:</label>
              <div class="col-md-4">
                <input id="taskName" name="Feature Name" type="text" placeholder="New Task Name" class="form-control input-md" v-model="form.taskName">
                <p v-if="!featureNameIsValid" class="error-message" style="color: red">Task Name Required</p>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-4 control-label" for="description">Description</label>
              <div class="col-md-4">
                <textarea id="description" name="description" type="text" placeholder="description" class="form-control input-md" v-model="form.description"/>
                <p v-if="!descriptionIsValid" class="error-message" style="color: red">Description Required</p>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-4 control-label" for="priority">Priority</label>
              <div class="col-md-4">
                <select id="priority" name="priority" class="form-control" v-model="form.priority">
                  <option value="Low">Low</option>
                  <option value="Medium">Medium</option>
                  <option value="High">High</option>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-4 control-label" for="submit"></label>
              <div class="col-md-4">
                <button :disabled="!formIsValid" id="submit" name="submit" class="btn btn-primary" style="margin-right: 15%">Create Task</button>
                <button type="reset" class="btn btn-primary">Reset</button>
              </div>
            </div>
          </fieldset>
        </form>

      </div>

    </div>

  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "FloatingButton",
  data() {
    return {
      form: {
        featureId: this.$route.params.featureId,
        taskName: '',
        status: 'TODO',
        priority: '',
        description: '',
      },
      isVisible: false
    }
  },
  computed: {

    featureNameIsValid() {
      return !!this.form.taskName
    },

    descriptionIsValid() {
      return !!this.form.description
    },

    formIsValid() {
      return this.featureNameIsValid && this.descriptionIsValid
    }//may also use vuelidate in the future to perform input validation
  },
  methods: {
    displayComponent: function () {
      var modal = document.getElementById("myModal");

// Get the button that opens the modal
//       var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
//       btn.onclick = function() {
        modal.style.display = "block";
      // }

// When the user clicks on <span> (x), close the modal
      span.onclick = function() {
        modal.style.display = "none";
      }

// When the user clicks anywhere outside of the modal, close it
      window.onclick = function(event) {
        if (event.target === modal) {
          modal.style.display = "none";
        }
      // `this` inside methods points to the Vue instance
    }
  },
    submitForm() {
      if(this.formIsValid) {
        document.getElementById("description").value = "";
        document.getElementById("taskName").value = "";
        console.log("form is valid")
        axios.post('http://localhost:8080/features/api/task/add', this.form)
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
    }
}


}

</script>

<style scoped>
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  -webkit-animation-name: fadeIn; /* Fade in the background */
  -webkit-animation-duration: 0.4s;
  animation-name: fadeIn;
  animation-duration: 0.4s
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  width: 40%;
  -webkit-animation-name: slideIn;
  -webkit-animation-duration: 0.4s;
  animation-name: slideIn;
  animation-duration: 0.4s;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 2px 16px;
  background-color: #2b3d8c;
  color: white;
}

.modal-body {
  left: 30%;
}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}

/* Add Animation */
@-webkit-keyframes slideIn {
  from {bottom: -300px; opacity: 0}
  to {bottom: 0; opacity: 1}
}

@keyframes slideIn {
  from {bottom: -300px; opacity: 0}
  to {bottom: 0; opacity: 1}
}

@-webkit-keyframes fadeIn {
  from {opacity: 0}
  to {opacity: 1}
}

@keyframes fadeIn {
  from {opacity: 0}
  to {opacity: 1}
}

.float{
  position:fixed;
  width:60px;
  height:60px;
  bottom:50px;
  right:50px;
  background-color:#2b3d8c;
  color:#FFF;
  border-radius:50px;
  text-align:center;
  box-shadow: 2px 2px 3px #999;
}

.my-float{
  margin-top:22px;
}

body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

/* The popup form - hidden by default */
.form-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
</style>