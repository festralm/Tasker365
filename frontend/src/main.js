import router from './router'
import store from './store'
import App from './App.vue';
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import { createApp } from 'vue';


// creating app instance
const app = createApp(App);


// connecting Vuex storage and Vue-Router
app.use(store);
app.use(router);

// connecting Toast lib
app.use(Toast, {
    transition: "Vue-Toastification__bounce",
    maxToasts: 20,
    newestOnTop: true
});

// build component and mount
app.component("app", App);
app.mount('#app');

