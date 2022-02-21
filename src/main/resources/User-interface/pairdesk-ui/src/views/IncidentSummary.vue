<template >

  <navigation></navigation>
<div style="margin-left: 6.5%">
<div id="incidentReport" >
  <h1>{{$t('IncidentReportHeader')}}</h1>
  <table style=" width: 100%;" class="table table-striped">
    <thead>
    <tr>
      <th class="text text-secondary" >{{$t('FeatureText')}}</th>
      <th class="text text-secondary" >{{$t('DueDate')}}</th>
      <th>{{$t('AssignedUser')}}</th>
      <th>{{$t('CurrentProgress')}}</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="feature in list" v-bind:key="feature.featureName">
      <td>{{ feature.featureName }}</td>
      <td>{{ feature.deadline}}</td>
      <td><UserItem v-bind:user-id="feature.userId"></UserItem></td>
      <td><progress-item v-bind:feature-id="feature.featureId"></progress-item></td>


    </tr>

    </tbody>
  </table>
</div>
  <button @click="generateReport()" class="btn btn-primary">{{$t('ExportAsPdf')}}</button>
</div>


</template>

<script>
import axios from "axios";
import Navigation from "@/components/navigation";
import html2pdf from "html2pdf.js/src";
import UserItem from "@/components/IncidentReport/UserItem";
import ProgressItem from "@/components/IncidentReport/ProgressItem";
export default {
  name: "IncidentSummary",
  components: {Navigation, UserItem,ProgressItem },
  list:undefined,
  tableUserName:undefined,
  assigned_user:'',
  user: JSON.parse(localStorage.getItem('userInfo')),

  data() {
    return {
      progress:undefined,
    assigned_user:[],
      tableUserName:undefined,
      list:undefined,
      user: JSON.parse(localStorage.getItem('userInfo'))
    }
  },
  mounted() {
    let yourConfig = {
      headers: {
        Authorization: localStorage.getItem("user-token")
      }
    }

    axios.get("http://3.99.41.187/features/api/lateFeatures", yourConfig).then((resp) => {
      this.list = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
    }).finally(() => {
    });



  },

  methods:{
    generateReport(){

      let el = document.getElementById('incidentReport');
      let opt = {
        margin:       0.2,
        filename:     'incidentReport'+ '.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait' }
      };

      html2pdf(el, opt);
    }
  }

}
</script>

<style scoped>

</style>