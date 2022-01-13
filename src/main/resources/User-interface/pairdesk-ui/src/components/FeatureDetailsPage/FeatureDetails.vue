<template>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <div class="content">
    <div class="container2">
      <div class="row">
        <div class="col-lg-8">
          <div class="card-box task-detail">
              <div class="media-body">

            <h1 class="m-b-20">{{ info.featureName }}</h1>
                <h5 class="media-heading mb-0 mt-0">Assigned to: {{ assigned_user.username }}</h5></div>
            <h2 class="m-b-5">Priority</h2>
            <h4 id="pC">{{info.priority}}</h4>
            <h3 class="m-b-5">Description</h3>
            <p class="text-muted">{{info.description}}</p>
            <ul class="list-inline task-dates m-b-0 mt-5">

              <li>
                <h3 class="m-b-5">Deadline</h3>
                <p>{{ formattedDate}}</p>

              </li>
            </ul>
            <div class="clearfix"></div>

          </div>
        </div>
        <!-- end col -->

        <!-- end col -->
      </div>
      <!-- end row -->
    </div>
    <!-- container -->
  </div>
<task-list></task-list>
</template>

<script>
import axios from "axios";
import TaskList from "@/components/FeatureDetailsPage/TaskList";

export default {

  name: "FeatureDetails",
  components: {TaskList},
  data()
  {
    return {
      info:[],
      assigned_user: [],
      user: JSON.parse(localStorage.getItem('userInfo')),
      value: 45,
      max: 100,
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }

    }
  },
  mounted()
  {

      axios.get("http://localhost:8080/features/api/" + this.$route.params.featureId,this.yourConfig).then((resp) => {
        this.info = resp.data;
        console.log("this.$route.meta.Navigation" )
        if(this.info.priority === "Low"){
          document.getElementById("pC").style.color = "green"
        }else if(this.info.priority === "Medium"){
          document.getElementById("pC").style.color = "orange"
        }else if(this.info.priority === "High"){
          document.getElementById("pC").style.color = "red"
        }
        this.formattedDate = new Date(this.info.deadline)

        try{
          axios.get("http://localhost:8080/users/api/" + this.info.userId, this.yourConfig).then((resp) => {
            console.log(this.info.userId)
            this.assigned_user = resp.data;
            console.log(this.assigned_user)
          })
        }
        catch(error)
        {
          console.log(error)
        }
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
.container2{
  margin-right: 10%;
  margin-left: 10%;
}

</style>