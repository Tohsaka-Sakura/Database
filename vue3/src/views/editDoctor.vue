<template>
  <div>
    <h1>医生管理系统</h1>

    <div>
      <h2>添加护士</h2>
      <form @submit.prevent="addNurse">
        <input v-model="nurse.name" placeholder="护士姓名" required />
        <input v-model="nurse.room" type="number" placeholder="房间号" required />
        <input v-model="nurse.department" placeholder="科室" required />
        <button type="submit">添加护士</button>
      </form>
      <p v-if="nurseMessage">{{ nurseMessage }}</p>
    </div>

    <div>
      <h2>添加科室</h2>
      <form @submit.prevent="addRoom">
        <input v-model="room.Type" placeholder="科室类型" required />
        <button type="submit">添加科室</button>
      </form>
      <p v-if="roomMessage">{{ roomMessage }}</p>
    </div>

    <div>
      <h2>添加病历</h2>
      <form @submit.prevent="addMedicalRecord">
        <input v-model="medicalRecord.Pid" type="number" placeholder="患者ID" required />
        <input v-model="medicalRecord.Did" type="number" placeholder="医生ID" required />
        <input v-model="medicalRecord.Nid" type="number" placeholder="护士ID" required />
        <input v-model="medicalRecord.time" type="datetime-local" required />
        <input v-model="medicalRecord.department" placeholder="科室" required />
        <textarea v-model="medicalRecord.notes" placeholder="病历备注" required></textarea>
        <button type="submit">添加病历</button>
      </form>
      <p v-if="recordMessage">{{ recordMessage }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { addMedicalRecordService, addNurseService, addRoomService } from '@/api/doctor.ts';

const newRecord = ref({
  PId: null,
  NId: null,
  DId: null,
  time: null ,
  department: '',
  notes: ''
});

const nurse = ref({
  id: null,
  name: '',
  room: null,
  department: '',
});

const room = ref({
  id: null,
  Type: '',
});

const showRecordModal = ref(false);
const router = useRouter();

const addNurse = async (nurse) => {
    try {
        const response = await addNurseService(nurse);
        console.log('护士添加成功:', response.data.message);
    } catch (error) {
        console.error('添加护士失败:', error.response.data.message);
    }
};

const addRoom = async (room) => {
    try {
        const response = await addRoomService(room);
        console.log('科室添加成功:', response.data.message);
    } catch (error) {
        console.error('添加科室失败:', error.response.data.message);
    }
};

const addMedicalRecord = async () => {
  try {
    await addMedicalRecordService(newRecord.value);
    ElMessage.success('病历添加成功');
    showRecordModal.value = false; // 关闭弹窗
    // 清空输入
    newRecord.value = { PId: '', NId: '', DId: '', time: '', department: '', notes: '' };
  } catch (error) {
    ElMessage.error('病历添加失败');
    console.error('Error adding medical record:', error);
  }
};

const cancelEdit = () => {
  router.push('/doctor');
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
  .cancel-button {
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
  .cancel-button:hover {
    background-color: #66b1ff;
  }
  
  .cancel-button {
    background-color: #f56c6c;
  }
  
  .cancel-button:hover {
    background-color: #f78989;
  }
  </style>
  