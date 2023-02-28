import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/index.js'
// import {Table,Button,TableColumn,Container,Header,Main,Menu} from 'element-ui'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/global.css'
import request from "@/utils/request";

Vue.config.productionTip = false


Vue.prototype.request=request
Vue.use(ElementUI)
// Vue.use(VueAxios, axios)
// Vue.use(Table)
// Vue.use(TableColumn)
// Vue.use(Button)
// Vue.use(Container)
// Vue.use(Header)
// Vue.use(Main)

new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
