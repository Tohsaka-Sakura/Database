import {createRouter, createWebHistory} from 'vue-router'


import LoginView from '@/views/Login.vue'
import LayoutView from '@/views/layout.vue'

import doctorLoginView from '@/views/doctorLogin.vue'
import adminLoginView from '@/views/adminLogin.vue'

import updateView from '@/views/update.vue'
import patientView from '@/views/patient.vue'

import doctorView from '@/views/doctor.vue'
import doctorUpdateView from '@/views/updateDoctor.vue'

import component from 'element-plus/es/components/tree-select/src/tree-select-option.mjs'


const route = [
    { path: '/', component: LoginView},
    { path: '/home' , component: LayoutView},
    { path: '/doctor',component: doctorLoginView},
    { path: '/admin', component: adminLoginView},
    { path: '/patient', component: patientView},
    { path: '/patient/edit-patient-info', component: updateView },
    { path: '/doctor/Info',component: doctorView},
    { path: '/doctor/edit-doctor-info',component: doctorUpdateView},
]

const router = createRouter({
    history:createWebHistory(),
    routes:route
})

export default router