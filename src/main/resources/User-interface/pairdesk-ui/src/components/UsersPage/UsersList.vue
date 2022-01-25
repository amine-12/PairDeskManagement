<template>
  <div class="container">
    <div class="row">
      <div class="col"><h2 style="float: left;">All Users</h2></div>
      <div class="col"><button v-on:click="displayComponent" type="button"  class="btn btn-primary add-btn">Add User</button></div>
    </div>
    <add-user-form></add-user-form>
    <ul class="responsive-table" style="padding-left: 0px;">
      <li class="table-header">
        <div class="col col-1">User Id</div>
        <div class="col col-2">Email</div>
        <div class="col col-3">Username</div>
        <div class="col col-4">Actions</div>
      </li>
      <div v-for="user in userList" v-bind:key="user.userId">
        <router-link :to="{ name: 'UsersDetail', params: { userId: user.userId } }">
        <li class="table-row" >
            <div class="col col-1" data-label="User Id">{{user.userId}}</div>
            <div class="col col-2" data-label="Email">{{user.email}}</div>
            <div class="col col-3" data-label="Username">{{ user.username }}</div>
            <div class="col col-4" data-label="Actions">
              <button id="seeUserDetailsBtnId" class="btn btn-primary" style="background: transparent; border: none;">
                <img src="@/assets/account-details.png" alt="See Details" style="height: 30px; width: 30px"/>
              </button>
              <button id="deleteUserBtnId" class="btn btn-danger" @click.stop.prevent="deleteUser(user.userId)" style="background: transparent; border: none;">
                <img src="@/assets/delete.png" alt="Delete User" style="height: 30px; width: 30px"/>
              </button>
            </div>
        </li>
        </router-link>
      </div>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
import AddUserForm from "@/components/UsersPage/AddUserForm";
export default {
  name: "UsersuserList",
  components: {AddUserForm},
  data() {
    return {
      userList:undefined,
      fid:undefined,
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      },
      users: [],
      isVisible: false
    }
  },
  methods:{
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
          window.location.reload()
        }
        // `this` inside methods points to the Vue instance
      }
    },
    deleteUser(userId) {
      let confirmed = confirm("Are you sure you would like to delete this user ");
      console.log("user id : " + userId);
      if(confirmed){
        console.log("form is valid")

        axios.delete('http://localhost:8080/users/api/delete/' + userId, this.yourConfig)
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
  },
  mounted()
  {
    axios.get("http://localhost:8080/users/api/all", this.yourConfig).then((resp) => {
      this.userList = resp.data;
      console.log(this.userList)
    }).catch((error) => {
      if (error.response.status === 401) {
        console.log("token expired")
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {
    });
  }
}
</script>

<style scoped>
body {
  font-family: 'lato', sans-serif;
}
.container {
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 25px;
}
h2 {
  font-size: 26px;
}
.add-btn{
  float: right;
}

h2 small {
  font-size: 0.5em;
}
.responsive-table li {
  border-radius: 3px;
  padding: 25px 30px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 25px;
}
.responsive-table .table-header {
  background-color: #2b3d8c;
  font-size: 14px;
  text-transform: uppercase;
  letter-spacing: 0.03em;
  color: white;
  margin-top: 2%;
}
.responsive-table .table-row {
  background-color: #fff;
  box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);

}

a{
  text-decoration: none;
  color: #1e1e24;
  font-size: medium;
}

.table-row:hover {
  transform: scale(1.02);
}

.responsive-table .col-1 {
  flex-basis: 15%;
}
.responsive-table .col-2 {
  flex-basis: 30%;
}
.responsive-table .col-3 {
  flex-basis: 20%;
}
.responsive-table .col-4 {
  flex-basis: 20%;
}
@media all and (max-width: 767px) {
  .responsive-table .table-header {
    display: none;
  }
  .responsive-table li {
    display: block;
  }
  .responsive-table .col {
    flex-basis: 100%;
  }
  .responsive-table .col {
    display: flex;
    padding: 10px 0;
  }
  .responsive-table .col:before {
    color: #6c7a89;
    padding-right: 10px;
    content: attr(data-label);
    flex-basis: 50%;
    text-align: right;
  }
}


h1 {
  text-align: center;
  font-family: Tahoma, Arial, sans-serif;
  color: #06D85F;
  margin: 80px 0;
}

.box {
  width: 40%;
  margin: 0 auto;
  background: rgba(255,255,255,0.2);
  padding: 35px;
  border: 2px solid #fff;
  border-radius: 20px/50px;
  background-clip: padding-box;
  text-align: center;
}

.button {
  font-size: 1em;
  padding: 10px;
  color: #fff;
  border: 2px solid #06D85F;
  border-radius: 20px/50px;
  text-decoration: none;
  cursor: pointer;
  transition: all 0.3s ease-out;
}
.button:hover {
  background: #06D85F;
}

.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  visibility: hidden;
  opacity: 0;
}
.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 70px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  position: relative;
  transition: all 5s ease-in-out;
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 30px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.popup .close:hover {
  color: #06D85F;
}
.popup .content {
  max-height: 30%;
  overflow: auto;
}

@media screen and (max-width: 700px){
  .box{
    width: 70%;
  }
  .popup{
    width: 70%;
  }
}
</style>