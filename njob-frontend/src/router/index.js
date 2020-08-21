import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Companies from '../views/Companies.vue'
import CompanyList from '../components/CompanyList.vue'
import CompanyDetails from '../components/CompanyDetails.vue'
import ApplicationDetails from '../views/ApplicationDetails.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/companies',
    component: Companies,
    children: [
      { path: '', component: CompanyList },
      { path: ':id', component: CompanyDetails, props: route => ({ id: Number(route.params.id) }) },
      {
        path: 'applications/:id',
        component: ApplicationDetails,
        props: route => ({ id: Number(route.params.id) })
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
