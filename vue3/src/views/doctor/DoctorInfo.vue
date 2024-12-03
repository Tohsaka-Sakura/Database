<script setup>
import { ref } from 'vue'


import useDoctorIndoStore from '@/stores/doctorInfo.ts'
const doctorInfoStore = useDoctorIndoStore();

const doctorInfo = ref({...doctorInfoStore.info})

const rules = {
    nickname: [
        { required: true, message: '请输入用户昵称', trigger: 'blur' },
        {
            pattern: /^\S{2,10}$/,
            message: '昵称必须是2-10位的非空字符串',
            trigger: 'blur'
        }
    ],
    email: [
        { required: true, message: '请输入用户邮箱', trigger: 'blur' },
        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
    ]
}

//修改个人信息
import {userInfoUpdateService} from '@/api/user.ts'
import {ElMessage} from 'element-plus'
const updateUserInfo = async ()=>{
    //调用接口
    let result = await userInfoUpdateService(userInfo.value);
    ElMessage.success(result.msg? result.msg : '修改成功');
    
    //修改pinia中的个人信息
    userInfoStore.setInfo(userInfo.value)
}

import {updateDoctorService} from '@/api/doctor.ts'

const updateDoctorInfo = async () => {
    let result = await updateDoctorService(doctorInfo.value);
    ElMessage.success(result.message?result.message: " sucess");

    doctorInfoStore.setInfo(doctorInfo.value)
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>基本资料</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="doctorInfo" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="登录名称">
                        <el-input v-model="doctorInfo.username" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="医生名字" prop="nickname">
                        <el-input v-model="doctorInfo.name"></el-input>
                    </el-form-item>
                    <el-form-item label="用户邮箱" prop="email">
                        <el-input v-model="doctorInfo.email"></el-input>
                    </el-form-item>
                    <el-form-item label = "联系电话">
                        <el-input v-model = "doctorInfo.phone" ></el-input>
                    </el-form-item>
                    <el-form-item label = "医生部门">
                        <el-input v-model = "doctorInfo.department" disabled></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updateDoctorInfo">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>