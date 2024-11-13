import request from '@/utils/request.js'

export const userRegisterService = (registerData) => {

    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }


    return request.post('/admin/register',params);

}

export const userLoginService = (loginData)=>{

    const params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key]);
    }

    return request.post('/admin/login',params)
}

//获取用户详细信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

//修改个人信息
export const userInfoUpdateService = (userInfoData)=>{
   return request.put('/user/update',userInfoData)
}

//修改头像
export const userAvatarUpdateService = (avatarUrl)=>{
    const params = new URLSearchParams();
    params.append('avatarUrl',avatarUrl)
    return request.patch('/user/updateAvatar',params)
}