import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import elementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/iconfont.css';
import '@/assets/css/style.css';
Vue.config.productionTip = false;
Vue.use(elementUI);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  elementUI,
  render: h => h(App)
}).$mount("#app");
