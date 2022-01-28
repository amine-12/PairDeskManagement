import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import i18n from "./locales/i18n"

const app = createApp(App)






app.use(router);
app.use(VueSweetalert2);

app.use(i18n).mount("#app");
