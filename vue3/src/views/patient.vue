<template>
    <div class="patient-info-container">
      <h2>User Information</h2>
      <div class="info-box">
        <p>姓名: {{ patient.name }}</p>
        <p>性别: {{ patient.gender }}</p>
        <p>用户名: {{ patient.username }}</p>
        <p>电话号码: {{ patient.contactNumber }}</p>
      </div>
      <button @click="editInfo">修改信息</button>
      <button @click="viewRecord">查看病历</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { userInfoService, patientRecordService } from '@/api/user.ts'; // 假设服务文件路径
  
  const patient = ref({
    id: null,
    name: '',
    gender: '',
    username: '',
    contactNumber: '',
    creatTime: null,
    updateTime: null
  });

  const record = ref({
    patientId: '',
    time: null,
    department: '',
    notes: '',
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

  const viewRecord = async () => {
    try {
      const response = await patientRecordService(patient.value.username); // 传递用户名以获取病历
      record.value = response.data.data;
      router.push({ name: 'patient-record', params: { record: record.value } }); // 跳转到病历页面，并传递病历数据
    } catch (error) {
      console.error('Error fetching patient record:', error);
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
  