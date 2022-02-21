<template>
  <div class="container">
    <div class="row">
      <div class="col"><h2 style="float: left;">{{ $t('invoice') }}</h2></div>
    </div>
    <ul class="responsive-table" style="padding-left: 0px;">
      <li class="table-header">
        <div class="col col-1">{{ $t('invoice').replace('s','') }} Id</div>
        <div class="col col-2">{{ $t('user') }}</div>
        <div class="col col-3">{{ $t('creation') }}</div>
        <div class="col col-4">Actions</div>
      </li>
      <div v-for="invoice in invoices" v-bind:key="invoice.invoiceId">
        <router-link :to="{ name: 'InvoiceDetails', params: { userId: invoice.userId } }">
          <li class="table-row" >
            <div class="col col-1" data-label="Invoice Id">{{invoice.invoiceId}}</div>
            <user-item v-bind:user-id="invoice.userId"></user-item>
            <date-item v-bind:user-id="invoice.userId"></date-item>
            <div class="col col-4" data-label="Actions">
              <button id="seeUserDetailsBtnId" class="btn btn-primary" style="background: transparent; border: none;">
                <img src="@/assets/account-details.png" alt="See Details" style="height: 30px; width: 30px"/>
              </button>
              <button id="exportAsPdfIconId" @click.stop.prevent="exportReport(invoice.userId)" class="btn btn-primary" style="background: transparent; border: none;">
                <img src="@/assets/file-export.png" alt="Export PDF" style="height: 30px; width: 30px"/>
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
import UserItem from "@/components/InvoicePage/UserItem";
import DateItem from "@/components/InvoicePage/DateItem";
// import InvoiceDetails from "@/components/InvoiceDetails/InvoiceDetail";

export default {
  name: "InvoiceList",
  components: {UserItem, DateItem,/*InvoiceDetails*/},
  data() {
    return {
      invoices:[],
      fid:undefined,
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      },
    }
  },
  methods:{
    exportReport(userId){
      let route = this.$router.resolve({ path: "/invoiceGeneration/" + userId });
      window.open(route.href,'','width=800,height=450');
    }
  },
  mounted() {
    axios.get("http://3.99.41.187/invoices/api/all", this.yourConfig).then((resp) => {
      this.invoices = resp.data;
      console.log(this.invoices)
    }).catch((error) => {
      if (error.response.status === 401) {
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
  flex-basis: 20%;
}
.responsive-table .col-3 {
  flex-basis: 30%;
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