<template>
  <div class="admin-dashboard">
    <h2>医生注册申请</h2>
    <div v-if="requests.length === 0">没有待处理的申请。</div>
    <div v-else>
      <div v-for="request in requests" :key="request.username" class="request-box">
        <p>用户名: {{ request.username }}</p>
        <button @click="approveRequest(request.username)" class="approve-button">通过</button>
        <button @click="rejectRequest(request.username)" class="reject-button">拒绝</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getDoctorRequests, approveDoctorRequest, rejectDoctorRequest } from '@/api/admin.ts';

const requests = ref({
    username: '',
    password: '',
  });

const fetchRequests = async () => {
  try {
    const response = await getDoctorRequests();
    requests.value = response.data;
  } catch (error) {
    console.error('Error fetching doctor requests:', error);
  }
};

const approveRequest = async (username) => {
  try {
    await approveDoctorRequest(username);
    alert('申请已通过');
    fetchRequests();
  } catch (error) {
    console.error('Error approving request:', error);
  }
};

const rejectRequest = async (username) => {
  try {
    await rejectDoctorRequest(username);
    alert('申请已拒绝');
    fetchRequests();
  } catch (error) {
    console.error('Error rejecting request:', error);
  }
};

onMounted(fetchRequests);
</script>

<style scoped>
.admin-dashboard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  text-align: center;
}

.request-box {
  background-color: #f0f0f0;
  border: 2px solid #ccc;
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
  color: #333;
}

.approve-button,
.reject-button {
  padding: 10px 20px;
  margin: 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.approve-button {
  background-color: #4caf50;
  color: white;
}

.approve-button:hover {
  background-color: #45a049;
}

.reject-button {
  background-color: #f44336;
  color: white;
}

.reject-button:hover {
  background-color: #e53935;
}
</style>
