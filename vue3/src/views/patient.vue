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
    <div v-if="record">
      <h3>病历信息</h3>
      <div class="info-box">
        <p>病人ID: {{ record.PId }}</p>
        <p>医生ID: {{ record.DId }}</p>
        <p>护士ID: {{ record.NId }}</p>
        <p>科室: {{ record.department }}</p>
        <p>备注: {{ record.notes }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { userInfoService, patientRecordService } from '@/api/user.ts';

const patient = ref({
  id: null,
  name: '',
  gender: '',
  username: '',
  contactNumber: '',
});

const record = ref({ PId: null, NId: null, DId: null, department: '', notes: '' });

const router = useRouter();

const fetchPatientInfo = async () => {
  try {
    const response = await userInfoService();
    patient.value = response.data.data;
    await fetchPatientRecord(); // 在获取患者信息后立即获取病历
  } catch (error) {
    console.error('Error fetching patient info:', error);
  }
};

const fetchPatientRecord = async () => {
  try {
    const response = await patientRecordService(patient.value.username);
    record.value = response.data.data;
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

h2, h3 {
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
