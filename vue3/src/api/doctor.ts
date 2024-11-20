import request from '@/utils/request.js'

export const doctorRegisterService = (registerData) => {

    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }


    return request.post('/doctor/register',params);

}

export const doctorLoginService = (loginData)=>{

    const params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key]);
    }

    return request.post('/doctor/login',params)
}

export const getDoctorService = () => {
    return request.get('/doctor/doctorInfo');
};

export const updateDoctorService = (userInfoData) => {
    const params = new URLSearchParams();
    for (let key in userInfoData) {
        params.append(key, userInfoData[key]);
    }
    return request.put('/doctor/update', params);
};
