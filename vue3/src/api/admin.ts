import request from '@/utils/request.js';

// 获取医生注册申请
export const getDoctorRequests = () => {
  return request.get('/admin/check');
};

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
  return request.delete('/admin/reject', { data: params });
};
