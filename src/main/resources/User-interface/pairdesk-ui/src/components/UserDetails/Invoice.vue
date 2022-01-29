<template>
  <div class="container" id="x">
    <div class="row" style="margin-bottom: 2%" >
      <div class="col" style="float: left;width: auto"><h1 style="text-align: left;float: left">{{ $t('invoice').replace('s','') }}</h1></div>
      <div class="col"  style="float: right;margin-top: 1%;width: auto">
        <h3 style="text-align: right;float: right">{{ $t('invoice').replace('s','') }} #{{invoiceId}}</h3></div>
    </div>

    <div class="row">
      <h4>{{ $t('invoiceCreation') }}: <span style="font-size: smaller;font-weight: normal">{{creationDate}}</span></h4>
    </div>

    <div class="row">
      <h4>{{ $t('invoiceFor') }}: <span style="font-size: medium;font-weight: normal">{{info.username}}</span></h4>
    </div>
    <div class="row">
      <h3 style="font-weight: bold;">{{ $t('featuresPayout') }}</h3>
    </div>

    <div class="row" style="width: 80%;margin-left: 8%">
      <ul class="responsive-table" v-if="featuresListPay && featuresListPay.length > 0">
        <div style="height: 250px;overflow-y:auto">
          <li v-for="feature in featuresListPay" v-bind:key="feature.featureId" class="border-bottom">
            <p>{{ feature.featureName }}</p>
            <p style="margin-bottom: 2%" class="alignright">$ {{ feature.price.toFixed(2) }}</p>
          </li>
        </div>

        <li class="total border-top border-3" >
          <p class="alignright" style="font-weight: bold;font-size: large">Total</p>
          <p class="alignright" style="font-weight: bold;font-size: large">$ {{ payout.toFixed(2) }}</p>
        </li>
      </ul>
      <div v-else>
        <h2 style="text-align: center;margin-top: 20%">{{ $t('noPayout') }}</h2>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Invoice",
  data(){
    return{
      invoiceId:'',
      creationDate:'',
      invoice:[],
      info:[],
      payout:0,
      featuresListPay:[],
      yourConfig: {
        headers: {
          Authorization: localStorage.getItem("user-token")
        }
      }
    }
  },
  mounted() {
    axios.get("http://localhost:8080/users/api/" + this.$route.params.userId,this.yourConfig).then((resp) => {
      this.info = resp.data;
      this.formattedDate = new Date(this.info.creationTime)
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {

    });

    axios.get("http://localhost:8080/features/api/user/completed/" + this.$route.params.userId,this.yourConfig).then((resp) => {
      this.featuresListPay = resp.data;
      if(this.featuresListPay == null){
        this.isListEmpty = true
      }
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    }).finally(() => {

    });

    axios.get("http://localhost:8080/invoices/api/user/payout/" + this.$route.params.userId,this.yourConfig).then((resp) => {
      this.payout = resp.data;
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    })

    let form = {
      userId: this.$route.params.userId,
      invoicePay: this.payout,
    }

    axios.get("http://localhost:8080/invoices/api/user/" + this.$route.params.userId,this.yourConfig).then((resp) => {
      this.invoice = resp.data;
      this.invoiceId = this.invoice.invoiceId
      this.creationDate = new Date(this.invoice.creationTime)
      if(this.invoice === ""){
        axios.post('http://localhost:8080/invoices/api/add', form, this.yourConfig)
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
      }
    }).catch((error) => {
      if (error.response.status === 401) {
        this.$router.push('/login')
      }
      console.log(error)
    })


  }
}
</script>

<style scoped>
.responsive-table li {
  border-radius: 3px;
  padding: 2px;
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

/* -------------------------------------
    GLOBAL
    A very basic CSS reset
------------------------------------- */
* {
  margin: 0;
  padding: 0;
  font-family: "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
  box-sizing: border-box;
  font-size: 14px;
}

.row{
  margin-bottom: 3%;
}

img {
  max-width: 100%;
}

body {
  -webkit-font-smoothing: antialiased;
  -webkit-text-size-adjust: none;
  width: 100% !important;
  height: 100%;
  line-height: 1.6;
}

/* Let's make sure all tables have defaults */
table td {
  vertical-align: top;
}

/* -------------------------------------
    BODY & CONTAINER
------------------------------------- */
body {
  background-color: #f6f6f6;
}



.container {
  display: block !important;
  max-width: 600px !important;
  margin: 0 auto !important;
  /* makes it centered */
  clear: both !important;
}

/*.content {*/
/*  max-width: 600px;*/
/*  margin: 0 auto;*/
/*  display: block;*/
/*  padding: 20px;*/
/*}*/

/*!* -------------------------------------*/
/*    HEADER, FOOTER, MAIN*/
/*------------------------------------- *!*/
/*.main {*/
/*  background: #fff;*/
/*  border: 1px solid #e9e9e9;*/
/*  border-radius: 3px;*/
/*}*/

/*.content-wrap {*/
/*  padding: 20px;*/
/*}*/

/*.content-block {*/
/*  padding: 0 0 20px;*/
/*}*/

.footer a {
  color: #999;
}
.footer p, .footer a, .footer unsubscribe, .footer td {
  font-size: 12px;
}

/* -------------------------------------
    TYPOGRAPHY
------------------------------------- */
/*h1, h3 {*/
/*  font-family: "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;*/
/*  color: #000;*/
/*  margin: 40px 0 0;*/
/*  line-height: 1.2;*/
/*  font-weight: 400;*/
/*}*/

h1 {
  font-size: 32px;
  font-weight: 500;
}

h2 {
  font-size: 24px;
}

h3 {
  font-size: 18px;
}

h4 {
  font-size: 14px;
  font-weight: 600;
}

p, ul, ol {
  margin-bottom: 10px;
  font-weight: normal;
}
p li, ul li, ol li {
  margin-left: 5px;
  list-style-position: inside;
}

/* -------------------------------------
    LINKS & BUTTONS
------------------------------------- */
a {
  color: #1ab394;
  text-decoration: underline;
}

.btn-primary {
  text-decoration: none;
  color: #FFF;
  background-color: #1ab394;
  border: solid #1ab394;
  border-width: 5px 10px;
  line-height: 2;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
  display: inline-block;
  border-radius: 5px;
  text-transform: capitalize;
}

/* -------------------------------------
    OTHER STYLES THAT MIGHT BE USEFUL
------------------------------------- */
.last {
  margin-bottom: 0;
}

.first {
  margin-top: 0;
}

.aligncenter {
  text-align: center;
}

.alignright {
  text-align: right;
}

.alignleft {
  text-align: left;
}

.clear {
  clear: both;
}

/* -------------------------------------
    ALERTS
    Change the class depending on warning email, good email or bad email
------------------------------------- */
.alert {
  font-size: 16px;
  color: #fff;
  font-weight: 500;
  padding: 20px;
  text-align: center;
  border-radius: 3px 3px 0 0;
}
.alert a {
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  font-size: 16px;
}
.alert.alert-warning {
  background: #f8ac59;
}
.alert.alert-bad {
  background: #ed5565;
}
.alert.alert-good {
  background: #1ab394;
}

/* -------------------------------------
    INVOICE
    Styles for the billing table
------------------------------------- */
.invoice {
  margin: 40px auto;
  text-align: left;
  width: 80%;
}
.invoice td {
  padding: 5px 0;
}


.invoice .invoice-items {
  width: 100%;
}
.invoice .invoice-items td {
  border-top: #eee 1px solid;
}
.invoice .invoice-items .total td {
  border-top: 2px solid #333;
  border-bottom: 2px solid #333;
  font-weight: 700;
}

/* -------------------------------------
    RESPONSIVE AND MOBILE FRIENDLY STYLES
------------------------------------- */
@media only screen and (max-width: 640px) {
  h1, h2, h3, h4 {
    font-weight: 600 !important;
    margin: 20px 0 5px !important;
  }

  h1 {
    font-size: 22px !important;
  }

  h2 {
    font-size: 18px !important;
  }

  h3 {
    font-size: 16px !important;
  }

  .container {
    width: 100% !important;
  }

  .content, .content-wrap {
    padding: 10px !important;
  }

  .invoice {
    width: 100% !important;
  }
}
</style>