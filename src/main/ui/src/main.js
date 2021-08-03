import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/global.css'
import store from './store'

Vue.prototype.$http = axios
//pro
//axios.defaults.baseURL = 'http://10.1.68.58:8093/webCallConfig/'

//sit
//axios.defaults.baseURL = 'http://10.1.12.42:8080/gd-alarm/webCallConfig/'

//uat
axios.defaults.baseURL = 'http://10.1.12.42:8099/webCallConfig/'

Vue.config.productionTip = false
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})

