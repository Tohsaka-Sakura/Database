<template>
    <div class="patient-info-container">
      <h2>User Information</h2>
      <div class="info-box">
        <p>Name: {{ patient.name }}</p>
        <p>Gender: {{ patient.gender }}</p>
        <p>Username: {{ patient.username }}</p>
        <p>PhoneNumber: {{ patient.contactNumber }}</p>
      </div>
      <button @click="editInfo">修改信息</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { userInfoService } from '@/api/user.ts'; // 假设服务文件路径
  
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
  
  const editInfo = () => {
    router.push('/patient/edit-patient-info');
  };
  
  onMounted(fetchPatientInfo);
  </script>
  
  <style scoped>
  .patient-info-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    text-align: center;
  }
  
  .info-box {
    background-color: #888484;
    border: 2px solid #409eff;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
    width: 300px;
  }
  
  h2 {
    margin-bottom: 20px;
  }
  
  p {
    margin: 10px 0;
  }
  
  button {
    padding: 10px 20px;
    background-color: #409eff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #66b1ff;
  }
  </style>
  