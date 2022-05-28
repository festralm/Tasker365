import router from './router'
import store from './store'
import App from './App.vue';
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import { createApp } from 'vue';


const app = createApp(App);


app.use(store);
app.use(router);

app.use(Toast, {
    transition: "Vue-Toastification__bounce",
    maxToasts: 20,
    newestOnTop: true
});

app.component("app", App);
app.mount('#app');

