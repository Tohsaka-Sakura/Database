import request from '@/utils/request.js';

// 注册医生
export const doctorRegisterService = (registerData) => {
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key]);
    }
    return request.post('/doctor/register', params);
};

// 医生登录
export const doctorLoginService = (loginData) => {
    const params = new URLSearchParams();
    for (let key in loginData) {
        params.append(key, loginData[key]);
    }
    return request.post('/doctor/login', params);
};

// 获取医生信息
export const getDoctorService = () => {
    return request.get('/doctor/doctorInfo');
};

// 更新医生信息
export const updateDoctorService = (userInfoData) => {
    return request.put('/doctor/update', JSON.stringify(userInfoData), {
        headers: {
            'Content-Type': 'application/json' // 设置 Content-Type 为 application/json
        }
    });
};

// 添加病历
export const addMedicalRecordService = (recordData) => {
    return request.post('/doctor/addMedicalRecord', recordData);
};

// 添加护士
export const addNurseService = (nurseData) => {
    return request.post('/doctor/addNurse', nurseData);
};

// 添加科室
export const addRoomService = (roomData) => {
    return request.post('/doctor/addRoom', roomData);
};

export const getAllDoctorService = () =>{
    return request.get('/doctor/allDoctorInfo');
}