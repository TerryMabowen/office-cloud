import Vue from "vue";
import App from "./App.vue";
//
import store from "./store";
//
import router from './router';
// 引入配置
import config from "./config";
// 阻止启动生产消息
Vue.config.productionTip = false;
// 引入 element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
// 引入 axios,与后端数据交互
import axios from 'axios';
Vue.prototype.axios = axios;

new Vue({
  router,
  store,
  config,
  render: h => h(App)
}).$mount("#app");
