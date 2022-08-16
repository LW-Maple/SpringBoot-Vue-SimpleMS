import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "@/views/Login";
import Layout from "@/layout/Layout";
import Book from "@/views/Book";
import Data from "@/views/Data";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/home',
    children:[
      {
        path: 'home',
        name: 'Home',
        component: HomeView
      },
      {
        path: 'book',
        name: 'Book',
        component: Book
      },
      {
        path: 'data',
        name: 'Data',
        component: Data
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
