<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import usePatientInfoStore from '@/stores/patientInfo.ts'
import { userInfoUpdateService } from '@/api/user.ts'

const patientInfoStore = usePatientInfoStore()
const patientInfo = ref({})
const rules = {
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    {
      pattern: /^\S{2,10}$/,
      message: '姓名必须是2-10位的非空字符串',
      trigger: 'blur'
    }
  ],
  contactNumber: [
    { required: true, message: '请输入联系电话', trigger: 'blur' },
    {
      pattern: /^\d{10,15}$/,
      message: '联系电话必须是10-15位的数字',
      trigger: 'blur'
    }
  ]
}

// 修改个人信息
const updateUserInfo = async () => {
  // 调用接口
  let result = await userInfoUpdateService(patientInfo.value)
  ElMessage.success(result.msg ? result.msg : '修改成功')
  
  // 修改pinia中的个人信息
  patientInfoStore.setInfo(patientInfo.value)
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
        <el-form :model="patientInfo" :rules="rules" label-width="100px" size="large">
          <el-form-item label="登录名称">
            <el-input v-model="patientInfo.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="patientInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="patientInfo.gender"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="contactNumber">
            <el-input v-model="patientInfo.contactNumber"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateUserInfo">提交修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>
