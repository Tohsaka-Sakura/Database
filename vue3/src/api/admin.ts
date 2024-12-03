import request from '@/utils/request.js';

// 获取医生注册申请
export const getDoctorRequests = () => {
  return request.get('/admin/check');
};


export const deleteDoctorService = (username) =>{
  const params = new URLSearchParams();
  params.append('username', username);
  return request.put('/admin/deleteDoctor',params);
}

export const getAllPatientService = ()=>{
  return request.get('/admin/getAllPatient');
}

export const deletePatientService = (username) =>{
  const params = new URLSearchParams();
  params.append('username', username);
  return request.put('/admin/deletePatient',params);
}


// 通过医生注册申请
export const approveDoctorRequest = (username) => {
  const params = new URLSearchParams();
  params.append('username', username);
  return request.put('/admin/verfiry', params);
};

// 拒绝医生注册申请
export const rejectDoctorRequest = (username) => {
  const params = new URLSearchParams();
  params.append('username', username);
  return request.put('/admin/reject',params);
};

export const adminLogin = (loginData) =>{
  const params = new URLSearchParams();
  for(let key in loginData){
    params.append(key,loginData[key]);
  }
  return request.post('admin/login',params);
}
