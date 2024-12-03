import {createRouter, createWebHistory} from 'vue-router'


import LoginView from '@/views/Login.vue'
import LayoutView from '@/views/doctor/DoctorLayout.vue'

import doctorLoginView from '@/views/doctor/doctorLogin.vue'
import adminLoginView from '@/views/admin/adminLogin.vue'

import adminView from '@/views/admin.vue'

import updateView from '@/views/update.vue'
import patientView from '@/views/patient.vue'

import doctorView from '@/views/doctor.vue'
import doctorUpdateView from '@/views/updateDoctor.vue'
import doctorEditView from '@/views/editDoctor.vue'

import component from 'element-plus/es/components/tree-select/src/tree-select-option.mjs'
import Layout from '@/views/doctor/DoctorLayout.vue'
import ArticleCategory from '@/views/article/ArticleCategory.vue'
import ArticleManage from '@/views/article/ArticleManage.vue'
import DoctorInfo from '@/views/doctor/DoctorInfo.vue'
import AdminLayout from '@/views/admin/AdminLayout.vue'
import DoctorRequire from '@/views/admin/DoctorRequire.vue'
import DoctorDepartment from '@/views/admin/DoctorDepartment.vue'
//import Person from '@/views/person.vue'


const route = [
    { path: '/', component: LoginView},
    { path: '/home' , component: LayoutView},
    { path: '/doctor',component: doctorLoginView},
    { path: '/admin', component: adminLoginView},
    { path: '/patient', component: patientView},
    // { path: '/patient/edit-patient-info', component: updateView },
    // { path: '/doctor/Info',component: doctorView},
    // { path: '/doctor/edit-doctor-info',component: doctorUpdateView},
    { path: '/doctor/home', component: Layout, children: [
        // { path: '/doctor/record', component: ArticleCategory},
        // { path: '/doctor/test', component: ArticleManage},
        { path: '/doctor/personinfo', component: DoctorInfo}
    ]},
    //admin:
    { path: '/admin/home',component: AdminLayout, children: [
        {path: '/admin/require', component: DoctorRequire},
        {path: '/admin/doctorInfo', component: DoctorDepartment}
    ]}



    ///



    // { path: '/doctor/add-info',component: doctorEditView},
    // //{ path: '/doctor/home', component: Layout},
    // { path: '/admin/request', component: adminView}
]

const router = createRouter({
    history:createWebHistory(),
    routes:route
})

export default router