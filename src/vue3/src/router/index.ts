import {createRouter, createWebHistory} from 'vue-router'


import LoginView from '@/views/Login.vue'
import LayoutView from '@/views/layout.vue'
import doctorLoginView from '@/views/doctorLogin.vue'
import adminLoginView from '@/views/adminLogin.vue'


const route = [
    { path: '/', component: LoginView},
    { path: '/home' , component: LayoutView},
    { path: '/doctor',component: doctorLoginView},
    { path: '/admin', component: adminLoginView}
]

const router = createRouter({
    history:createWebHistory(),
    routes:route
})

export default router