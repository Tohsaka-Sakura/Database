<template>
    <div class="doctor-info-container">
      <h2>医生信息</h2>
      <div class="info-box">
        <p>姓名: {{ doctor.name }}</p>
        <p>用户名: {{ doctor.username }}</p>
        <p>电话: {{ doctor.phone }}</p>
        <p>科室: {{ doctor.department }}</p>
        <p>邮箱: {{ doctor.email }}</p>
      </div>
      <button @click="editInfo" class="edit-button">修改信息</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { getDoctorService } from '@/api/doctor.ts';
  
  const doctor = ref({
    id: null,
    name: '',
    username: '',
    phone: '',
    department: '',
    email: ''
  });
  
  const router = useRouter();
  
  const fetchDoctorInfo = async () => {
    try {
      const response = await getDoctorService(doctor.value.id);
      doctor.value = response.data;
    } catch (error) {
      console.error('Error fetching doctor info:', error);
    }
  };
  
  const editInfo = () => {
    router.push('/doctor/edit-doctor-info');
  };
  
  onMounted(fetchDoctorInfo);
  </script>
  
  <style scoped>
  .doctor-info-container {
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
    color: #333;
  }
  
  p {
    margin: 10px 0;
    color: #fff;
  }
  
  .edit-button {
    padding: 10px 20px;
    background-color: #409eff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .edit-button:hover {
    background-color: #66b1ff;
  }
  </style>
  