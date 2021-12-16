<template>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css" integrity="sha256-mmgLkCYLUQbXn0B1SRqzHar6dCnv9oZFPEC1g1cwlkk=" crossorigin="anonymous" />


  <div class="container2">
    <div class="col-md-12 col-12 col-sm-12">
      <div class="card1">
        <div class="card-header">
          <h4>Tasks</h4>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-striped">
              <tbody><tr>

                <th>Task Name</th>
                <th>DESCRIPTION</th>
                <th>PRIORITY</th>
                <th>STATUS</th>
                <th>Action</th>
              </tr>
              <tr v-for="task in list" v-bind:key="task.featureId" id="rowForTask" v-bind:style="setBackground(task.status)">
                <td>{{ task.taskName }}</td>
                <td>{{ task.description }}</td>
                <td  v-bind:style="getStatus(task.priority)">{{ task.priority }} </td>
                <td>{{ task.status }}</td>
                <td>
                  <a class="btn btn-primary btn-action mr-1" data-toggle="tooltip" title="" data-original-title="Edit"><i class="fas fa-pencil-alt"></i></a>

                  <a class="btn btn-danger btn-action" @click="deleteTask(task.taskId)" data-toggle="tooltip" title=""
                     data-confirm="Are You Sure?|This action can not be undone. Do you want to continue?"
                     data-confirm-yes="alert('Deleted')" data-original-title="Delete">
                    <i class="fas fa-trash"></i></a>

                  <a class="btn btn-success btn-action" v-on:click="completeTask(task.taskId)" data-toggle="tooltip" title="" data-original-title="Complete"><i class="fas fa-check"></i></a>

                </td>
              </tr>

              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TaskList",
  data()
  {
    return {
      list:undefined,
      tid:undefined,
      form: {
        featureId: this.$route.params.featureId,
        taskName: this.taskName,
        description: this.description,
        priority: this.priority,
        status: "DONE"
      }
    }
  },
  mounted()
  {
    try {
      axios.get("http://localhost:8080/features/api/tasks/" +  this.$route.params.featureId).then((resp) => {
        this.list = resp.data;
      })
    }
    catch(error){
      console.log(error)
    }
  },
  methods:{
    getStatus(property){
      if(property === "Low"){
        return 'color: #4CC441;'
      }
      if(property === "Medium"){
        return 'color: orange;'
      }
      return 'color: red;'
    },
    deleteTask(taskId) {
      let confirmed = confirm("Are you sure you would like to delete this task ");

      if(confirmed){
        console.log("task id : " +taskId);
        console.log("task form is valid");
        axios.delete('http://localhost:8080/features/api/tasks/' + taskId, this.form)
            .then((resp) => {
              this.form = resp.data;
              console.log(this.form);
            })
      }
    },
    setBackground(status) {
      if(status === "DONE"){
        return "background-color: #97f7ac";
      }
    },
    completeTask(id) {
      let confirmed = confirm("Is this task complete?");
      this.tid = id;
      console.log(this.tid)
      if(confirmed){
          axios.get("http://localhost:8080/features/api/task/" + id).then((resp) => {
            this.info = resp.data;
            console.log(this.description)
            console.log(this.info)
          })

        axios.put('http://localhost:8080/features/api/tasks/update/' + id, this.form)
            .then((resp) => {
              this.form = resp.data;
              console.log(this.tid);
              console.log(resp);
            })
            .catch((error) => {
              console.log(error)});
        document.getElementById("rowForTask").style.backgroundColor = "#97f7ac";
        return id;
      }
    }
  }
}

</script>

<style scoped>
body{
  background-color: #f6f6f6;
  margin-top:20px;
}

.container2{
  margin-right: 10%;
  margin-left: 10%;
}
.card1 {
  background-color: #fff;
  border-radius: 10px;
  border: none;
  position: relative;
  margin-bottom: 30px;
  box-shadow: 0 0.46875rem 2.1875rem rgba(90,97,105,0.1), 0 0.9375rem 1.40625rem rgba(90,97,105,0.1), 0 0.25rem 0.53125rem rgba(90,97,105,0.12), 0 0.125rem 0.1875rem rgba(90,97,105,0.1);
}

.card1 .card-header {
  border-bottom-color: #f9f9f9;
  line-height: 30px;
  -ms-grid-row-align: center;
  align-self: center;
  width: 100%;
  padding: 10px 25px;
  display: flex;
  align-items: center;
}

.card1 .card-header, .card1 .card-body, .card1 .card-footer {
  background-color: transparent;
  padding: 20px 25px;
}
.card-header:first-child {
  border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0;
}
.card-header {
  padding: .75rem 1.25rem;
  margin-bottom: 0;
  background-color: rgba(0,0,0,.03);
  border-bottom: 1px solid rgba(0,0,0,.125);
}


.table:not(.table-sm) thead th {
  border-bottom: none;
  background-color: #e9e9eb;
  color: #666;
  padding-top: 15px;
  padding-bottom: 15px;
}

.table .table-img img {
  width: 35px;
  height: 35px;
  border-radius: 50%;
  border: 2px solid #bbbbbb;
  -webkit-box-shadow: 5px 6px 15px 0px rgba(49,47,49,0.5);
  -moz-box-shadow: 5px 6px 15px 0px rgba(49,47,49,0.5);
  -ms-box-shadow: 5px 6px 15px 0px rgba(49,47,49,0.5);
  box-shadow: 5px 6px 15px 0px rgba(49,47,49,0.5);
  text-shadow: 0 0 black;
}

.table .team-member-sm {
  width: 32px;
  -webkit-transition: all 0.25s ease;
  -o-transition: all 0.25s ease;
  -moz-transition: all 0.25s ease;
  transition: all 0.25s ease;
}
.table .team-member {
  position: relative;
  width: 30px;
  white-space: nowrap;
  border-radius: 1000px;
  vertical-align: bottom;
  display: inline-block;
}

.table .order-list li img {
  border: 2px solid #ffffff;
  box-shadow: 4px 3px 6px 0 rgba(0,0,0,0.2);
}
.table .team-member img {
  width: 100%;
  max-width: 100%;
  height: auto;
  border: 0;
  border-radius: 1000px;
}
.rounded-circle {
  border-radius: 50% !important;
}

.table .order-list li+li {
  margin-left: -14px;
  background: transparent;
}
.avatar.avatar-sm {
  font-size: 12px;
  height: 30px;
  width: 30px;
}
.avatar {
  background: #6777ef;
  border-radius: 50%;
  color: #e3eaef;
  display: inline-block;
  font-size: 16px;
  font-weight: 300;
  margin: 0;
  position: relative;
  vertical-align: middle;
  line-height: 1.28;
  height: 45px;
  width: 45px;
}

.table .order-list li .badge {
  background: rgba(228,222,222,0.8);
  color: #6b6f82;
  margin-bottom: 6px;
}

.badge {
  vertical-align: middle;
  padding: 7px 12px;
  font-weight: 600;
  letter-spacing: 0.3px;
  border-radius: 30px;
  font-size: 12px;
}

.progress-bar {
  display: -ms-flexbox;
  display: -webkit-box;
  display: flex;
  -ms-flex-direction: column;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  flex-direction: column;
  -ms-flex-pack: center;
  -webkit-box-pack: center;
  justify-content: center;
  overflow: hidden;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  background-color: #007bff;
  -webkit-transition: width .6s ease;
  transition: width .6s ease;
}

.bg-success {
  background-color: #54ca68 !important;
}

.bg-purple {
  background-color: #9c27b0 !important;
  color: #fff;
}

.bg-cyan {
  background-color: #10cfbd !important;
  color: #fff;
}

.bg-red {
  background-color: #f44336 !important;
  color: #fff;
}

.progress {
  -webkit-box-shadow: 0 0.4rem 0.6rem rgba(0,0,0,0.15);
  box-shadow: 0 0.4rem 0.6rem rgba(0,0,0,0.15);
}

.btn-action {
  color: #fff !important;
  line-height: 25px;
  font-size: 12px;
  min-width: 35px;
  min-height: 35px;
}
</style>