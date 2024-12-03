<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'

const doctorRegister = ref([

])

import {useTokenStore} from '@/stores/token.ts'
const tokenStore = useTokenStore();

import {getDoctorRequests} from '@/api/admin.ts';


const doctorInfo=ref([

])

import {getAllDoctorService} from '@/api/doctor.ts'

const doctorInfoList = async() =>{
    let result = await getAllDoctorService();
    doctorInfo.value = result.data;
}

doctorInfoList();

//控制添加分类弹窗
const dialogVisible = ref(false)



// //添加分类数据模型
// const categoryModel = ref({
//     categoryName: '',
//     categoryAlias: ''
// })
// //添加分类表单校验
// const rules = {
//     categoryName: [
//         { required: true, message: '请输入分类名称', trigger: 'blur' },
//     ],
//     categoryAlias: [
//         { required: true, message: '请输入分类别名', trigger: 'blur' },
//     ]
// }


//调用接口,添加表单
// import { ElMessage } from 'element-plus'
// const addCategory = async () => {
//     //调用接口
//     let result = await articleCategoryAddService(categoryModel.value);
//     ElMessage.success(result.msg ? result.msg : '添加成功')

//     //调用获取所有文章分类的函数
//     articleCategoryList();
//     dialogVisible.value = false;
// }

//定义变量,控制标题的展示
const title = ref('')


const doctorInfoModel = ref({
    username:"",
    name:"",
    phone:"",
    department:"",
    email:""
})


//展示编辑弹窗
const showDialog = (row) => {
    dialogVisible.value = true; title.value = 'modify infomation'
    //数据拷贝
    // categoryModel.value.categoryName = row.categoryName;
    // categoryModel.value.categoryAlias = row.categoryAlias;
    doctorInfoModel.value.username = row.username;
    doctorInfoModel.value.name = row.name;
    doctorInfoModel.value.phone = row.phone;
    doctorInfoModel.value.department = row.department;
    doctorInfoModel.value.email = row.email;
    // //扩展id属性,将来需要传递给后台,完成分类的修改
    // categoryModel.value.id = row.id
}

import {updateDoctorService} from '@/api/doctor.ts'
const updateCategory = async () => {
    //调用接口
    // let result = await articleCategoryUpdateService(categoryModel.value);

    let result = await updateDoctorService(doctorInfoModel.value);

    ElMessage.success(result.msg ? result.msg : '修改成功')

    // //调用获取所有分类的函数
    // articleCategoryList();

    doctorInfoList();

    //隐藏弹窗
    dialogVisible.value = false;
}

// //清空模型的数据
// const clearData = () => {
//     categoryModel.value.categoryName = '';
//     categoryModel.value.categoryAlias = '';
// }

//删除分类
import {ElMessageBox,ElMessage} from 'element-plus'
import {rejectDoctorRequest, approveDoctorRequest} from '@/api/admin.ts'

const allowRequire = (row) =>{
    ElMessageBox.confirm(
        'Do you want to accpet the register?',
        'reminder',
        {
            confirmButtonText: 'yes',
            cancelButtonText: 'No',
            type:'warning',
        }
    )
        .then(async() =>{
            let result = await approveDoctorRequest(row.username);
            ElMessage({
                type:'success',
                message:'accpet success',
            })
            doctorRequrieList();

        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: 'user cancel accpet',
            })
        })
}


const rejectRequire = (row) => {
    //提示用户  确认框

    ElMessageBox.confirm(
        'Do you want to reject the register?',
        'reminder',
        {
            confirmButtonText: 'yes',
            cancelButtonText: 'no',
            type: 'warning',
        }
    )
        .then(async() => {
            let result = await rejectDoctorRequest(row.username);
            ElMessage({
                type:'success',
                message:'reject success',
            })
            doctorRequrieList();

        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: 'user cancel delete',
            })
        })
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>doctor info</span>
                <!-- <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = '添加分类'; clearData()">添加分类</el-button>
                </div> -->
            </div>
        </template>
        <el-table :data="doctorInfo" style="width: 100%">
            <el-table-column label="username" prop="username"></el-table-column>
            <el-table-column label="doctor name" prop="name"> </el-table-column>
            <el-table-column label="phone" prop="phone"> </el-table-column>
            <el-table-column label="department" prop="department"> </el-table-column>
            <el-table-column label="email" prop="email"></el-table-column>
            <!-- <el-table-column label="分类别名" prop="categoryAlias"></el-table-column> -->
            <el-table-column label="operation" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="rejectRequire(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="no data" />
            </template>
        </el-table>

        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="doctorInfoModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="username" prop="username">
                    <el-input v-model="doctorInfoModel.username" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="name" prop="name">
                    <el-input v-model="doctorInfoModel.name" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="phone" prop="phone">
                    <el-input v-model="doctorInfoModel.phone" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="department" prop="department">
                    <el-input v-model="doctorInfoModel.department" minlength="1" maxlength="15"></el-input>
                </el-form-item>
                <el-form-item label="email" prop="email">
                    <el-input v-model="doctorInfoModel.email" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">cancel</el-button>
                    <el-button type="primary" @click="title == 'modify infomation' ? updateCategory() : updateCategory()"> modify </el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>