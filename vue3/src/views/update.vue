<template>
  <div class="update-patient-container">
    <h2>Edit Information</h2>
    <form @submit.prevent="updateInfo" class="patient-form">
      <div class="form-group">
        <label>Name:</label>
        <input v-model="patient.name" type="text" />
      </div>
      <div class="form-group">
        <label>Gender:</label>
        <input v-model="patient.gender" type="text" />
      </div>
      <div class="form-group">
        <label>PhoneNumber:</label>
        <input v-model="patient.contactNumber" type="text" />
      </div>
      <button type="submit" class="submit-button">保存</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { userInfoService, userInfoUpdateService } from '@/api/user.ts'; // 假设服务文件路径

const patient = ref({
  id: null,
  name: '',
  gender: '',
  username: '',
  contactNumber: '',
  creatTime: null,
  updateTime: null
});

const router = useRouter();

const fetchPatientInfo = async () => {
  try {
    const response = await userInfoService();
    patient.value = response.data.data;
  } catch (error) {
    console.error('Error fetching patient info:', error);
  }
};

const updateInfo = async () => {
  try {
    await userInfoUpdateService(patient.value);
    router.push('/patient');
  } catch (error) {
    console.error('Error updating patient info:', error);
  }
};

onMounted(fetchPatientInfo);
</script>

<style scoped>
.update-patient-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 60vh;
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

.patient-form {
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

.submit-button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #66b1ff;
}
</style>
