<template>
  <h1 style="text-align: center;margin-top: 3%">PairDesk Project Manager</h1>
  <div class="main">

    <p class="sign" align="center">Sign in</p>
    <form class="form1" v-on:submit.prevent="submitForm">
      <p id="error" class="error-message"  align="center" style="color: red"/>
      <div id="username" class="error-message"  align="left" style="color: red;margin-left: 46px;"/>
      <input  v-model="form.username" id="usernameLogin" class="un" type="text" align="center" placeholder="Username">

      <div id="password" class="error-message"  align="left" style="color: red;margin-left: 46px;"/>
      <input v-model="form.password" id="passwordLogin" class="pass" type="password" align="center" placeholder="Password">
      <button id="loginBtn" class="submit" align="center">Sign in</button>
      <p class="forgot" align="center"><a href="#">Forgot Password?</a></p>
    </form>
  </div>

</template>

<script>
import axios from "axios";
let user = {}
export default {
  name: "LoginForm",
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      isVisible: false
    }
  },
  mounted() {
    localStorage.removeItem('user-token')
    localStorage.removeItem('userInfo')
  },

  computed: {

    passwordIsValid() {
      return !!this.form.password
    },

    usernameIsValid() {
      return !!this.form.username
    },

    formIsValid() {
      return this.passwordIsValid && this.usernameIsValid
    }//may also use vuelidate in the future to perform input validation
  },
  methods: {
    submitForm() {
      if (this.formIsValid) {
        console.log("form is valid")
        axios.post('http://3.99.41.187/api/auth/signin', this.form)
            .then((resp) => {
              this.form = resp.data;
              const token = resp.data.accessToken
              localStorage.setItem('user-token', "Bearer " + token)
              user = resp.data
              localStorage.setItem('userInfo', JSON.stringify(user))
              this.$router.push('/')
              console.log(this.form);
              console.log(user);
            })
            .catch((error) => {
              localStorage.removeItem('user-token')
              if (error.response.status === 401) {
                document.getElementById("error").innerHTML = "Wrong Credentials"
                document.getElementById("username").innerHTML = ""
                document.getElementById("password").innerHTML = ""
              }
              console.log(error)
            }).finally(() => {
        });
      } else {
        if(!this.usernameIsValid){
          document.getElementById("username").innerHTML = "Username Required"
        }if(this.usernameIsValid){
          document.getElementById("username").innerHTML = ""
        }

        if(!this.passwordIsValid){
          document.getElementById("password").innerHTML = "Password Required"
        }if(this.passwordIsValid){
          document.getElementById("password").innerHTML = ""
        }

        console.log("form is invalid")
      }
    }
  }
}

</script>

<style scoped>
body {
  background-color: #F3EBF6;
  font-family: 'Ubuntu', sans-serif;
}

.main {
  background-color: #FFFFFF;
  width: 400px;
  height: 400px;
  margin: 7em auto;
  border-radius: 1.5em;
  box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
}

.sign {
  padding-top: 40px;
  color: #8C55AA;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bold;
  font-size: 23px;
}

.un {
  width: 76%;
  color: rgb(38, 50, 56);
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1px;
  background: rgba(136, 126, 126, 0.04);
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  outline: none;
  box-sizing: border-box;
  border: 2px solid rgba(0, 0, 0, 0.02);
  margin-bottom: 50px;
  margin-left: 46px;
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Ubuntu', sans-serif;
}

form.form1 {
  padding-top: 13px;
}

.pass {
  width: 76%;
  color: rgb(38, 50, 56);
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1px;
  background: rgba(136, 126, 126, 0.04);
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  outline: none;
  box-sizing: border-box;
  border: 2px solid rgba(0, 0, 0, 0.02);
  margin-bottom: 50px;
  margin-left: 46px;
  text-align: center;
  margin-bottom: 27px;
  font-family: 'Ubuntu', sans-serif;
}


.un:focus, .pass:focus {
  border: 2px solid rgba(0, 0, 0, 0.18) !important;

}

.submit {
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #0d6efd, #7257d7);
  border: 0;
  padding-left: 40px;
  padding-right: 40px;
  padding-bottom: 10px;
  padding-top: 10px;
  font-family: 'Ubuntu', sans-serif;
  margin-left: 35%;
  font-size: 13px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
}

.forgot {
  text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
  color: #E1BEE7;
  padding-top: 15px;
}

a {
  text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
  color: #E1BEE7;
  text-decoration: none
}

@media (max-width: 600px) {
  .main {
    border-radius: 0px;
  }
}

</style>