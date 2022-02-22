<template>
  <div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <span class="close">&times;</span>

      </div>
      <div class="modal-body">

        <form class="form-horizontal" v-on:submit.prevent="submitForm" @submit="checkForm"  enctype="multipart/form-data">
          <fieldset id="formContainer">
            <h1>New User</h1>
            <div class="form-group">
              <label class="col-md-7 control-label" for="taskName">Username:</label>
              <div class="col-md-7">
                <input style="width: 100%" id="taskName" v-on:input="verifyUsername" name="Username" type="text" placeholder="Username" class="form-control input-md" v-model="form.username">
                <p v-if="!userNameIsValid" class="error-message" style="color: red">Username Required</p>
                <p id="usernameUsed" class="error-message" style="color: red;display: none">Username already used</p>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-6  control-label" for="description">Email</label>
              <div class="col-md-7 ">
                <input style="width: 100%" id="description"  v-on:input="verifyEmail" name="email" type="text" placeholder="Email" class="form-control input-md" v-model="form.email">
                <p v-if="!descrptionIsValid" class="error-message" style="color: red">Email Required</p>
                <p id="emailUsed" class="error-message" style="color: red;display: none">Email already used</p>

              </div>
            </div>

            <div class="form-group">
              <label class="col-md-6  control-label" for="submit"></label>
              <div class="col-md-7 ">
                <button :disabled="!formIsValid" id="submit" name="submit" class="btn btn-primary" style="margin-right: 15%">Create User</button>
                <button type="reset" style="float: right" class="btn btn-primary">Reset</button>
              </div>
            </div>
          </fieldset>
        </form>
        <div class="row card-u">
          <h4 style="float: left">Credentials</h4>
          <h5 style="float: left">Username: {{ form.username }}</h5>
          <h5 style="float: left">Password: {{ form.password }}</h5>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AddUserForm",
  data() {
    return {
      checkTheEmail:false,
      check:false,
      form: {
        userId: '',
        username: '',
        email: '',
        password: '',
        roles: ['user']
      },
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }
    }
  },mounted() {
    axios.get("http://3.99.41.187:8080/users/api/password", this.yourConfig).then((resp) => {
      this.form.password = resp.data;
    })
  },
  computed: {

    userNameIsValid() {
      return !!this.form.username
    },

    descrptionIsValid() {
      return !!this.form.email
    },

    formIsValid() {
      return this.userNameIsValid && this.descrptionIsValid && !this.check && !this.checkTheEmail
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
        window.location.reload()
      }

// When the user clicks anywhere outside of the modal, close it
      window.onclick = function(event) {
        if (event.target === modal) {
          modal.style.display = "none";
          // window.location.reload()
        }
        // `this` inside methods points to the Vue instance
      }
    },
    async submitForm() {
      if (this.formIsValid) {
        await axios.post('http://3.99.41.187:8080/api/auth/signup', this.form, this.yourConfig)
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
      } else {
        console.log("form is invalid")
      }
    },
    verifyUsername(){
      axios.post('http://3.99.41.187:8080/api/auth/existing-username/', this.form.username, this.yourConfig)
          .then((resp) => {
            console.log(this.form.username)
            this.check = resp.data;
            if(this.check){
              document.getElementById("usernameUsed").style.display = "block";
            }

            if(!this.check){
              document.getElementById("usernameUsed").style.display = "none";
            }
            console.log(this.check)
          })
          .catch((error) => {
            if (error.response.status === 401) {
              this.$router.push('/login')
            }
            console.log(error)
          }).finally(() => {
      });
    },
    verifyEmail(){
      axios.post('http://3.99.41.187:8080/api/auth/existing-email/', this.form.email, this.yourConfig)
          .then((resp) => {
            console.log(this.form.email)
            this.checkTheEmail = resp.data;
            if(this.checkTheEmail){
              document.getElementById("emailUsed").style.display = "block";
            }

            if(!this.checkTheEmail){
              document.getElementById("emailUsed").style.display = "none";
            }
            console.log(this.checkTheEmail)
          })
          .catch((error) => {
            if (error.response.status === 401) {
              this.$router.push('/login')
            }
            console.log(error)
          }).finally(() => {
      });
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
  width: 45%;
  height: 600px;
  -webkit-animation-name: slideIn;
  -webkit-animation-duration: 0.4s;
  animation-name: slideIn;
  animation-duration: 0.4s;
  position: absolute;
  left: 50%;
  top: 45%;
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
  left: 20%;
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

.card-u {
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.25);
  margin-top: 3%;
  margin-right: 40%;
}


</style>