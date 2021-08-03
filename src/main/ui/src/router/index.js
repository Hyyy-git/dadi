import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import ConfigList from '../components/ConfigList'
import store from '../store'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path:'/',
      redirect:'/login'
    },
    {
      path:'/login',
      component:Login
    },
    {
      path:'/home',
      component:Home,
      redirect:'/configList',
      children:[
        {
          path:'/configList',
          component:ConfigList
        }
      ]
    },
  ],
})

router.beforeEach((to,from,next) => {
  if(to.path == '/login') return next()

  if(!store.state.login_state) return  next('/login')

  next()
})

export default router
