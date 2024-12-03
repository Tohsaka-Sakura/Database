<template>
  <div>
    <h1>医生管理</h1>

    <button @click="showNurseModal">添加护士</button>
    <button @click="showRoomModal">添加科室</button>
    <button @click="showRecordModal">添加病历</button>

    <div v-if="isNurseModalVisible">
      <h2>添加护士</h2>
      <form @submit.prevent="addNurse">
        <input v-model="nurse.name" placeholder="护士姓名" required />
        <input v-model="nurse.department" placeholder="科室" required />
        <input v-model="nurse.room" type="number" placeholder="房间编号" required />
        <button type="submit">添加护士</button>
      </form>
      <p v-if="nurseMessage">{{ nurseMessage }}</p>
      <button @click="closeNurseModal">关闭</button>
    </div>

    <div v-if="isRoomModalVisible">
      <h2>添加房间</h2>
      <form @submit.prevent="addRoom">
        <input v-model="room.Type" placeholder="房间类型" required />
        <button type="submit">添加房间</button>
      </form>
      <p v-if="roomMessage">{{ roomMessage }}</p>
      <button @click="closeRoomModal">关闭</button>
    </div>

    <div v-if="isRecordModalVisible">
      <h2>添加病历</h2>
      <form @submit.prevent="addMedicalRecord">
        <input v-model="newRecord.PId" type="number" placeholder="患者ID" required />
        <input v-model="newRecord.DId" type="number" placeholder="医生ID" required />
        <input v-model="newRecord.NId" type="number" placeholder="护士ID" required />
        <input v-model="newRecord.department" placeholder="科室" required />
        <textarea v-model="newRecord.notes" placeholder="病历备注" required></textarea>
        <button type="submit">添加病历</button>
      </form>
      <p v-if="recordMessage">{{ recordMessage }}</p>
      <button @click="closeRecordModal">关闭</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { addMedicalRecordService, addNurseService, addRoomService } from '@/api/doctor.ts';

// 响应式数据
const nurse = ref({ name: '', room: null, department: '' });
const room = ref({ Type: '' });
const newRecord = ref({ PId: null, NId: null, DId: null, department: '', notes: '' });

const nurseMessage = ref('');
const roomMessage = ref('');
const recordMessage = ref('');

const isNurseModalVisible = ref(false);
const isRoomModalVisible = ref(false);
const isRecordModalVisible = ref(false);

// 显示和关闭护士模态框
const showNurseModal = () => {
  isNurseModalVisible.value = true;
  nurseMessage.value = ''; // 清空之前的消息
};
const closeNurseModal = () => {
  isNurseModalVisible.value = false;
  nurse.value = { name: '', room: null, department: '' }; // 清空输入
};

// 显示和关闭科室模态框
const showRoomModal = () => {
  isRoomModalVisible.value = true;
  roomMessage.value = ''; // 清空之前的消息
};
const closeRoomModal = () => {
  isRoomModalVisible.value = false;
  room.value = { Type: '' }; // 清空输入
};

// 显示和关闭病历模态框
const showRecordModal = () => {
  isRecordModalVisible.value = true;
  recordMessage.value = ''; // 清空之前的消息
};
const closeRecordModal = () => {
  isRecordModalVisible.value = false;
  newRecord.value = { PId: null, NId: null, DId: null, time: null, department: '', notes: '' }; // 清空输入
};

// 添加护士
const addNurse = async () => {
  try {
    const response = await addNurseService(nurse.value);
    nurseMessage.value = '护士添加成功';
    closeNurseModal();  // 关闭模态框
  } catch (error) {
    nurseMessage.value = '添加护士失败: ' + error.response.data.message;
    console.error('添加护士失败:', error);
  }
};

// 添加科室
const addRoom = async () => {
  try {
    const response = await addRoomService(room.value);
    roomMessage.value = '科室添加成功';
    closeRoomModal();  // 关闭模态框
  } catch (error) {
    roomMessage.value = '添加科室失败: ' + error.response.data.message;
    console.error('添加科室失败:', error);
  }
};

// 添加病历
const addMedicalRecord = async () => {
  try {
    await addMedicalRecordService(newRecord.value);
    recordMessage.value = '病历添加成功';
    closeRecordModal();  
  } catch (error) {
    recordMessage.value = '病历添加失败';
    console.error('Error adding medical record:', error);
  }
};

</script>

<style scoped>
/* 样式部分，保持原来的样式或根据需要调整 */
</style>