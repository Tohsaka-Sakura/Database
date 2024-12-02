<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus';


const isRegister = ref(false)



const RegisterData = ref({
    username: "",
    password: "",
    rePassword: ""
})




const checkRePassword = (rule,value,callback)=>{
    if(value == ""){
        callback(new Error("please enter the password again"))
    }else if(value!==RegisterData.value.password){
        callback(new Error("please input the same password"))
    }else {
        callback()
    }
}

const rules={
    username: [
        {required:true,message:"please input username",trigger:"blur"},
        {min:5,max:16,message:"the length should be 5 to 16",trigger:"blur"}
    ],
    password: [
    {required:true,message:"please input password",trigger:"blur"},
    {min:5,max:16,message:"the length should be 5 to 16",trigger:"blur"}
    ],
    rePassword: [
        {validator:checkRePassword,trigger:"blur"}
    ]
}

import {doctorRegisterService,doctorLoginService} from '@/api/doctor.ts'

const register = async() =>{


    let result = await doctorRegisterService(RegisterData.value);

    

    ElMessage.success(result.message ? result.message:'register success')
}

const router = useRouter()

import {useTokenStore} from '@/stores/token'

const tokenStore = useTokenStore()

const login = async()=>{
    let result = await doctorLoginService(RegisterData.value);

    

    ElMessage.success(result.message ? result.message:'login success')

    tokenStore.setToken(result.data)

    router.push('/admin/request')

}



const clearRegisterData = ()=>{
    RegisterData.value={
        username:'',
        password:'',
        rePassword:''
    }
}


</script>

<template>
    <el-row :gutter="20" class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">

        

            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="RegisterData" :rules="rules" >
                <el-form-item>
                    <h1>Admin register</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="please input username" v-model="RegisterData.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="please input password" v-model="RegisterData.password"></el-input>
                </el-form-item>
                <el-form-item prop="rePassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="please verify the password" v-model="RegisterData.rePassword"></el-input>
                </el-form-item>


                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click = "register">
                        Register
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false;clearRegisterData()" >
                        ← Go back
                    </el-link>
                </el-form-item>
            </el-form>


            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-else :model="RegisterData" :rules="rules">
                <el-form-item>
                    <h1>Admin login</h1>
                </el-form-item>

                <el-form-item  prop="username">
                    <el-input :prefix-icon="User" placeholder="please input username" v-model="RegisterData.username"></el-input>
                </el-form-item>

                <el-form-item prop ="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="please input password" v-model="RegisterData.password"></el-input>
                </el-form-item>

                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>remember me</el-checkbox>
                        <el-link type="primary" :underline="false">forget password?</el-link>
                    </div>
                </el-form-item>
                
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login" >login</el-button>
                </el-form-item>

                <!-- <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true;clearRegisterData()">
                        Register →
                    </el-link>
                </el-form-item> -->

                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="clearRegisterData();router.push('/')">
                        Patient →
                    </el-link>
                </el-form-item>

                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="clearRegisterData();router.push('/doctor')">
                        Doctor →
                    </el-link>
                </el-form-item>






            </el-form>


        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>

/* 样式 */
.login-page {
    height: 90vh;
    width: 90vw;
    background-color: #fff;

    

    .bg {
        
        background: url('@/assets/OIP-C.jpg') no-repeat 60% center / 240px auto ,
            url('@/assets/login_bg.jpg') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
        
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;
        
        margin: auto;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 95%;
        }

        .flex {
            width: 95%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>