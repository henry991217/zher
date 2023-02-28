
//引入组件import Vue from'vue'
import Vue from'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export default new VueRouter(
{
	routes:[
		
		{
			path:'',
			component:()=>import('../components/ZH-table'),
			
		},
		{
			path:'/login',
			component:()=>import('../components/Login')
		},
		{
			path:'/zh168168',
			component:()=>import('../components/backend/Home')
		}
	]
})

