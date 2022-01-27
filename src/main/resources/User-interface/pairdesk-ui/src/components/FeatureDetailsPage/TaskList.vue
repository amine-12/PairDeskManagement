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
            <div class="text-dark"> Overall Progress : {{ progress }} %</div>
            <div class="progress">
              <div id="progress-bar" class="progress-bar progress-bar-striped bg-success" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <table class="table table-striped" v-if="list && list.length > 0">
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

                  <a class="btn btn-primary btn-action mr-1"  @click="displayUpdateForm(task.taskId)"
                     data-toggle="tooltip" title="" data-original-title="Edit">
                    <i class="fas fa-pencil-alt"></i></a>

                  <a class="btn btn-danger btn-action" @click="deleteTask(task.taskId);" data-toggle="tooltip" title=""
                     data-confirm="Are You Sure?|This action can not be undone. Do you want to continue?"
                     data-confirm-yes="alert('Deleted')" data-original-title="Delete">
                    <i class="fas fa-trash"></i></a>

                  <a class="btn btn-success btn-action" v-on:click="completeTask(task.taskId, task.status)" data-toggle="tooltip" title="" data-original-title="Complete" id="completeButton"><i class="fas fa-check"></i></a>

                </td>
              </tr>
              </tbody>
            </table>
            <div v-else>
              <h3 style="text-align: center;margin-top: 2%">No Tasks</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  <div id="myModalTaskUpdate" class="modal" style="display: none">

    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <span class="close">&times;</span>

      </div>
      <div class="modal-body">
        <form class="form-horizontal" @submit="checkForm" v-on:submit.prevent="submitForm2">
          <fieldset>
            <h1>Update this Task</h1>
            <div class="form-group">
              <label class="col-md-4 control-label" for="taskName">Task Name:</label>
              <div class="col-md-4">
                <input id="taskName" v-model="form3.taskName" class="form-control input-md" name="Feature Name"
                       placeholder="New Task Name" type="text">
                <p v-if="!featureNameIsValid2" class="error-message" style="color: red">Task Name Required</p>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-4 control-label" for="description">Description</label>
              <div class="col-md-4">
                <textarea id="description" v-model="form3.description" class="form-control input-md" name="description"
                          placeholder="description" type="text"/>
                <p v-if="!descriptionIsValid2" class="error-message" style="color: red">Description Required</p>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-4 control-label" for="priority">Priority</label>
              <div class="col-md-4">
                <select id="priority" v-model="form3.priority" class="form-control" name="priority">
                  <option value="Low">Low</option>
                  <option value="Medium">Medium</option>
                  <option value="High">High</option>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label class="col-md-4 control-label" for="submit"></label>
              <div class="col-md-4">
                <button v-on:click="updateTask" id="submit" :disabled="!formIsValid2" class="btn btn-primary" name="submit"
                        style="margin-right: 15%">Update Task
                </button>
                <button class="btn btn-primary" type="reset">Reset</button>
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
  name: "TaskList",
  data() {
    return {
      progress: undefined,
      list: undefined,
      tid: undefined,
      tid2: undefined,
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      },
      form2: {
        featureId: this.$route.params.featureId,
        taskName: this.taskName,
        description: this.description,
        priority: this.priority,
        status: ''
      },
      form3: {
        featureId: this.$route.params.featureId,
        taskName: this.taskName,
        description: this.description,
        priority: this.priority,
        status: this.status
      }
    }


  },
  mounted() {
    try {
      axios.get("http://localhost:8080/features/api/tasks/" + this.$route.params.featureId, this.yourConfig).then((resp) => {
        this.list = resp.data;
      })

      axios.get("http://localhost:8080/features/api/progress/" + this.$route.params.featureId, this.yourConfig).then((resp) => {
        this.progress = resp.data;
        this.progress = this.progress.toFixed(1);
        document.getElementById("progress-bar").style.width = this.progress + "%";
      });

    } catch (error) {
      console.log(error)
    }
  },
  computed: {

    featureNameIsValid() {
      return !!this.form2.taskName
    },

    descriptionIsValid() {
      return !!this.form2.description
    },
    featureNameIsValid2() {
      return !!this.form3.taskName
    },

    descriptionIsValid2() {
      return !!this.form3.description
    },

    formIsValid() {
      return this.featureNameIsValid && this.descriptionIsValid
    }//may also use vuelidate in the future to perform input validation
    ,

    formIsValid2() {
      return this.featureNameIsValid2 && this.descriptionIsValid2
    }//may also use vuelidate in the future to perform input validation

  },

  methods: {
    getStatus(property) {
      if (property === "Low") {
        return 'color: #4CC441;'
      }
      if (property === "Medium") {
        return 'color: orange;'
      }
      return 'color: red;'
    },
    deleteTask(taskId) {
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
          axios.delete('http://localhost:8080/features/api/tasks/' + taskId, this.yourConfig)
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
              'The task has been deleted.',
              'success'
          ).then((result2) => {
            if (result2.isConfirmed) {
              window.location.reload()
              
            }
          })
        }
      })
    },
    setBackground(status) {
      if (status === "DONE") {
        return "background-color: #97f7ac";
      }
    },
    completeTask(id, status) {
      this.tid = id;

      if (status === "TODO") {
        this.form2.status = "DONE"
      }

      if (status === "DONE") {
        this.form2.status = "TODO"
      }
      axios.put('http://localhost:8080/features/api/tasks/update/' + id, this.form2, this.yourConfig)
          .then((resp) => {
            this.form2 = resp.data;
          })
          .catch((error) => {
            console.log(error)
          });
      window.location.reload()
      return id;
    },


    displayUpdateForm(id) {

      this.tid2 = id
      var modal = document.getElementById("myModalTaskUpdate");

// Get the button that opens the modal
//       var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
//       btn.onclick = function() {
      modal.style.display = "block";
      // }

// When the user clicks on <span> (x), close the modal
      span.onclick = function () {
        modal.style.display = "none";
        window.location.reload()
      }

// When the user clicks anywhere outside of the modal, close it
      window.onclick = function (event) {
        if (event.target === modal) {
          modal.style.display = "none";
          window.location.reload()
        }
        // `this` inside methods points to the Vue instance
      }
    },


    submitForm() {
      if (this.formIsValid) {

        document.getElementById("description").value = "";
        document.getElementById("taskName").value = "";
        console.log("form is valid")
        axios.post('http://localhost:8080/features/api/task/add', this.form, this.yourConfig)
            .then((resp) => {
              this.form = resp.data;
              console.log(this.form);
            })
            .catch((error) => {
              if (error.response.status === 401) {
                console.log("token expired")
                this.$router.push('/login')
              }
              console.log(error)
            }).finally(() => {
        });
      } else {
        console.log("form is invalid")
      }

    },

    submitForm2() {
      if (this.formIsValid2) {
        console.log("form is valid update")
        axios.put('http://localhost:8080/features/api/tasks/update/' + this.tid2, this.form3, this.yourConfig)
            .then((resp) => {
              this.form3 = resp.data;

              console.log(this.form3);
              console.log(this.tid2);
            })
            .catch((error) => {
              if (error.response.status === 401) {
                console.log("token expired")
                this.$router.push('/login')
              }
              console.log(error)
            }).finally(() => {
        });
      } else {
        console.log("form is invalid")
      }
      window.location.reload();

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
