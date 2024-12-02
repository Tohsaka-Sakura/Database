<template>
  <div class="update-doctor-container">
    <h2>编辑医生信息</h2>
    <form @submit.prevent="updateDoctorInfo" class="doctor-form">
      <div class="form-group">
        <label>姓名:</label>
        <input v-model="doctor.name" type="text" />
      </div>
      <div class="form-group">
        <label>用户名:</label>
        <input v-model="doctor.username" type="text" />
      </div>
      <div class="form-group">
        <label>电话:</label>
        <input v-model="doctor.phone" type="text" />
      </div>
      <div class="form-group">
        <label>科室:</label>
        <input v-model="doctor.department" type="text" />
      </div>
      <div class="form-group">
        <label>邮箱:</label>
        <input v-model="doctor.email" type="email" />
      </div>
      <button type="submit" class="submit-button">保存</button>
      <button type="button" class="cancel-button" @click="cancelEdit">取消</button>
    </form>

    <button type="button" class="add-record-button" @click="showRecordModal = true">添加病历</button>
    
    <!-- 新增的按钮，用于跳转到 editDoctor 视图 -->
    <button type="button" class="edit-doctor-button" @click="goToEditDoctor">编辑医生</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { getDoctorService, updateDoctorService } from '@/api/doctor.ts';

const doctor = ref({
  id: null,
  name: '',
  username: '',
  phone: '',
  department: '',
  email: ''
});

const showRecordModal = ref(false);
const router = useRouter();

const fetchDoctorInfo = async () => {
  try {
    const response = await getDoctorService(doctor.value.id);
    doctor.value = response.data;
  } catch (error) {
    console.error('Error fetching doctor info:', error);
  }
};

const updateDoctorInfo = async () => {
  try {
    await updateDoctorService(doctor.value);
    ElMessage.success('信息更新成功');
    router.push('/doctor');
  } catch (error) {
    ElMessage.error('信息更新失败');
    console.error('Error updating doctor info:', error);
  }
};

const cancelEdit = () => {
  router.push('/doctor');
};

// 跳转到 editDoctor 视图的函数
const goToEditDoctor = () => {
  router.push('/add-info'); 
};

fetchDoctorInfo();
</script>

<style scoped>
.update-doctor-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
  text-align: center;
  background-color: #888484;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

.doctor-form {
  display: flex;
  flex-direction: column;
  width: 300px;
}

.form-group {
  margin-bottom: 15px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

label {
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

input {
  padding: 8px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.submit-button,
.cancel-button,
.edit-doctor-button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover,
.cancel-button:hover,
.edit-doctor-button:hover {
  background-color: #66b1ff;
}

.cancel-button {
  background-color: #f56c6c;
}

.cancel-button:hover {
  background-color: #f78989;
}

.edit-doctor-button {
  background-color: #67c23a; /* 自定义编辑按钮颜色 */
}

.edit-doctor-button:hover {
  background-color: #85d7a9; /* 自定义编辑按钮 hover 颜色 */
}
</style>