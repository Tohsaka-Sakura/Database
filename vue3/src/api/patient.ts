import request from '@/utils/request.js'

export const patientInfoListService = ()=>{

    return request.get('/doctor/doctorInfo')
}

export const patientInfoService = () => {
    return request.get('/patient/patientInfo');
};

export const patientRegisterService = (registerData) => {
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key]);
    }
    return request.post('/patient/register', params);
};

export const patientLoginService = (loginData) => {
    const params = new URLSearchParams();
    for (let key in loginData) {
        params.append(key, loginData[key]);
    }
    return request.post('/patient/login', params);
};