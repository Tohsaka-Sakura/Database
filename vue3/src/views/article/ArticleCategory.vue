<script setup>
import { ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import { useTokenStore } from '@/stores/token.ts'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getDetailService, addMedicalRecordService, addNurseService, addRoomService, getAllDoctorService } from '@/api/doctor.ts'

const patientInfo = ref([])

// Fetch patient details from the backend
const fetchPatientInfo = async () => {
    try {
        let result = await getDetailService();
        patientInfo.value = result.data;
    } catch (error) {
        console.error('Failed to fetch patient info:', error);
    }
}

fetchPatientInfo();

// Control the visibility of the dialog
const dialogVisible = ref(false)
const title = ref('')

// Record model for the form
const recordModel = ref({})

// Nurse model for the form
const nurseModel = ref({
    nurseName: '',
    nurseId: ''
})

// Room model for the form
const roomModel = ref({
    roomName: '',
    roomId: ''
})

// Function to show edit dialog
const showDialog = (row) => {
    dialogVisible.value = true;
    title.value = 'Edit Patient Info';
    // Populate form data with selected row information
}

// Function to delete a record
const deleteRecord = (row) => {
    // Implement delete logic here
}

// Function to add a new medical record
const addRecord = async () => {
    try {
        let result = await addMedicalRecordService(recordModel.value);
        ElMessage.success(result.msg ? result.msg : 'Record added successfully');
        dialogVisible.value = false; // Close the dialog
    } catch (error) {
        ElMessage.error('Failed to add record: ' + error.message);
    }
}

// Function to add a new nurse
const addNurse = async () => {
    try {
        let result = await addNurseService(nurseModel.value);
        ElMessage.success(result.msg ? result.msg : 'Nurse added successfully');
        nurseDialogVisible.value = false; // Close the dialog
    } catch (error) {
        ElMessage.error('Failed to add nurse: ' + error.message);
    }
}

// Function to add a new room
const addRoom = async () => {
    try {
        let result = await addRoomService(roomModel.value);
        ElMessage.success(result.msg ? result.msg : 'Room added successfully');
        roomDialogVisible.value = false; // Close the dialog
    } catch (error) {
        ElMessage.error('Failed to add room: ' + error.message);
    }
}

// Control the visibility of the nurse dialog
const nurseDialogVisible = ref(false)

// Control the visibility of the room dialog
const roomDialogVisible = ref(false)

// Clear form data
const clearData = () => {
    recordModel.value = {};
    nurseModel.value = {
        nurseName: '',
        nurseId: ''
    };
    roomModel.value = {
        roomName: '',
        roomId: ''
    };
}
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Relative Records Detail</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = 'Add Record'; clearData()">Add Record</el-button>
                    <el-button type="primary" @click="nurseDialogVisible = true; clearData()">Add Nurse</el-button>
                    <el-button type="primary" @click="roomDialogVisible = true; clearData()">Add Room</el-button>
                </div>
            </div>
        </template>
        <el-table :data="patientInfo" style="width: 100%">
            <el-table-column label="Patient Name" prop="PatientName"></el-table-column>
            <el-table-column label="Nurse ID" prop="NurseId"></el-table-column>
            <el-table-column label="Doctor ID" prop="DoctorId"></el-table-column>
            <el-table-column label="Contact Number" prop="contactNumber"></el-table-column>
            <el-table-column label="Gender" prop="gender"></el-table-column>
            <el-table-column label="Actions" width="150">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteRecord(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="No data available" />
            </template>
        </el-table>

        <!-- Add/Edit Patient Dialog -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="recordModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="Patient ID" prop="PatientID">
                    <el-input v-model="recordModel.PatientID"></el-input>
                </el-form-item>
                <el-form-item label="Nurse ID" prop="NurseId">
                    <el-input v-model="recordModel.NurseId" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Doctor ID" prop="DoctorID">
                    <el-input v-model="recordModel.DoctorID" type="number"></el-input>
                </el-form-item>
                <el-form-item label="Department" prop="department">
                    <el-input v-model="recordModel.department"></el-input>
                </el-form-item>
                <el-form-item label="Notes" prop="notes">
                    <el-input v-model="recordModel.notes"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="addRecord">Confirm</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- Add Nurse Dialog -->
        <el-dialog v-model="nurseDialogVisible" title="Add Nurse" width="30%">
            <el-form :model="nurseModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="Nurse Name" prop="nurseName">
                    <el-input v-model="nurseModel.nurseName"></el-input>
                </el-form-item>
                <el-form-item label="Nurse ID" prop="nurseId">
                    <el-input v-model="nurseModel.nurseId" type="number"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="nurseDialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="addNurse">Confirm</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- Add Room Dialog -->
        <el-dialog v-model="roomDialogVisible" title="Add Room" width="30%">
            <el-form :model="roomModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="Room Name" prop="roomName">
                    <el-input v-model="roomModel.roomName"></el-input>
                </el-form-item>
                <el-form-item label="Room ID" prop="roomId">
                    <el-input v-model="roomModel.roomId" type="number"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="roomDialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="addRoom">Confirm</el-button>
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
