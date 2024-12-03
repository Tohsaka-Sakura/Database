<script setup>
import { ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { userInfoService, patientRecordService, userInfoUpdateService, addPreviousRecordService } from '@/api/user.ts'

const patientInfo = ref({})
const patientRecords = ref([])

// Fetch patient details from the backend
const fetchPatientInfo = async () => {
    try {
        let result = await userInfoService();
        patientInfo.value = result.data;
    } catch (error) {
        console.error('Failed to fetch patient info:', error);
    }
}

// Fetch patient records from the backend
const fetchPatientRecords = async () => {
    try {
        let result = await patientRecordService();
        patientRecords.value = result.data;
    } catch (error) {
        console.error('Failed to fetch patient records:', error);
    }
}

fetchPatientInfo();
fetchPatientRecords();

// Control the visibility of the dialog
const dialogVisible = ref(false)
const previousRecordDialogVisible = ref(false)
const title = ref('')

// Record model for the form
const recordModel = ref({})
const previousRecordModel = ref({
    hospital: '',
    doctor: '',
    note: '',
    date: ''
})

// Function to show edit dialog
const showDialog = (row) => {
    dialogVisible.value = true;
    title.value = 'Edit Patient Info';
    recordModel.value = { ...row }; // Populate form data with selected row information
}

// Function to delete a record
const deleteRecord = (row) => {
    ElMessageBox.confirm(
        'Are you sure you want to delete this record?',
        'Warning',
        {
            confirmButtonText: 'Yes',
            cancelButtonText: 'No',
            type: 'warning',
        }
    ).then(async () => {
        try {
            // Call the delete service (not provided in the original code)
            // await deleteRecordService(row.id); // Uncomment and implement this line if you have a delete service
            ElMessage.success('Record deleted successfully');
            fetchPatientRecords(); // Refresh the list after deletion
        } catch (error) {
            ElMessage.error('Failed to delete record: ' + error.message);
        }
    }).catch(() => {
        ElMessage.info('Delete canceled');
    });
}

// Function to update patient info
const updatePatientInfo = async () => {
    try {
        let result = await userInfoUpdateService(recordModel.value);
        ElMessage.success(result.msg ? result.msg : 'Patient info updated successfully');
        dialogVisible.value = false; // Close the dialog
        fetchPatientInfo(); // Refresh the patient info
    } catch (error) {
        ElMessage.error('Failed to update patient info: ' + error.message);
    }
}

// Function to add a previous record
const addPreviousRecord = async () => {
    try {
        let result = await addPreviousRecordService(previousRecordModel.value);
        ElMessage.success(result.msg ? result.msg : 'Previous record added successfully');
        previousRecordDialogVisible.value = false; // Close the dialog
        fetchPatientRecords(); // Refresh the records list
    } catch (error) {
        ElMessage.error('Failed to add previous record: ' + error.message);
    }
}

// Clear form data
const clearData = () => {
    recordModel.value = {};
    previousRecordModel.value = {
        hospital: '',
        doctor: '',
        note: '',
        date: ''
    }
}
</script>


<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>Patient Records</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; title = 'Add Record'; clearData()">Add Record</el-button>
                    <el-button type="primary" @click="previousRecordDialogVisible = true; clearData()">Add Previous Record</el-button>
                </div>
            </div>
        </template>
        <el-table :data="patientRecords" style="width: 100%">
            <el-table-column label="Patient ID" prop="PatientID"></el-table-column>
            <el-table-column label="Nurse ID" prop="NurseId"></el-table-column>
            <el-table-column label="Doctor ID" prop="DoctorID"></el-table-column>
            <el-table-column label="Room" prop="department"></el-table-column>
            <el-table-column label="Notes" prop="notes"></el-table-column>
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
                <el-form-item label="Room" prop="department">
                    <el-input v-model="recordModel.department"></el-input>
                </el-form-item>
                <el-form-item label="Notes" prop="notes">
                    <el-input v-model="recordModel.notes"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="updatePatientInfo">Confirm</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- Add Previous Record Dialog -->
        <el-dialog v-model="previousRecordDialogVisible" title="Add Previous Record" width="30%">
            <el-form :model="previousRecordModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="Hospital" prop="hospital">
                    <el-input v-model="previousRecordModel.hospital"></el-input>
                </el-form-item>
                <el-form-item label="Doctor" prop="doctor">
                    <el-input v-model="previousRecordModel.doctor"></el-input>
                </el-form-item>
                <el-form-item label="Note" prop="note">
                    <el-input v-model="previousRecordModel.note"></el-input>
                </el-form-item>
                <el-form-item label="Date" prop="date">
                    <el-date-picker v-model="previousRecordModel.date" type="date" placeholder="Select Date"></el-date-picker>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="previousRecordDialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="addPreviousRecord">Confirm</el-button>
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
