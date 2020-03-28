import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const state = {
  topNavState: 'home',
  leftNavState: 'home'
};

export default new Vuex.Store({
  state: {state},
  mutations: {},
  actions: {},
  modules: {}
});
