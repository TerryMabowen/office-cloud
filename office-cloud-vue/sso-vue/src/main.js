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
// 引入 axios封装类,与后端数据交互
import AxiosUtil from './unit/axiosUtil';
Vue.use(AxiosUtil);

new Vue({
  router,
  store,
  config,
  render: h => h(App)
}).$mount("#app");
