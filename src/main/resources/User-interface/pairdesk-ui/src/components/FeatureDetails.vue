<template>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-lg-8">
          <div class="card-box task-detail">
              <div class="media-body">

            <h1 class="m-b-20">{{ info.featureName }}</h1>
                <h5 class="media-heading mb-0 mt-0">Assigned to: //User Placeholder</h5><span class="badge badge-danger">Urgent</span></div>
            <h3 class="m-b-5">Priority</h3>
            <p class="text-muted">{{info.priority}}//TODO</p>
            <h3 class="m-b-5">Description</h3>
            <p class="text-muted">{{info.description}}</p>
            <ul class="list-inline task-dates m-b-0 mt-5">

              <li>
                <h3 class="m-b-5">Due Date</h3>
                <p>{{ info.deadline}}<small class="text-muted">12:00 PM</small></p>
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
</template>

<script>
import axios from "axios";
export default {

  name: "FeatureDetails",
  data()
  {
    return {
      info:[]
    }
  },
  mounted()
  {
    try {
      axios.get("http://localhost:8080/features/api/" + this.$route.params.featureId).then((resp) => {
        this.info = resp.data;
        console.log(this.info.description)
        console.log(this.$route.params.featureId)
      })
    }
    catch(error){
      console.log(error)
    }
  }
}
</script>

<style scoped>

</style>