// Vuex and Router imports
import router from './router'
import store from './store'

// Toast lib imports
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

// Ready components lib imports
import AntV from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

// Vue core objects imports
import { createApp } from 'vue';
import App from './App.vue';


// creating app instance
const app = createApp(App);


// connecting Vuex storage and Vue-Router
app.use(store);
app.use(router);

// using ready components lib AntD
app.use(AntV);

// connecting Toast lib
app.use(Toast, {
    transition: "Vue-Toastification__bounce",
    maxToasts: 20,
    newestOnTop: true
});

// build component and mount
app.component("app", App);
app.mount('#app');
