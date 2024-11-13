//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus';
import {useTokenStore} from '@/stores/token.ts'
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({baseURL})



instance.interceptors.request.use(
    (config) =>{
        const tokenStore = useTokenStore();

        if(tokenStore.token){
            config.headers.Authorization = tokenStore.token
        }

        return config;
    },
    (err)=>{

        Promise.reject(err)
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        if(result.data.code == 0){
            return result.data;
        }
        
        //alert(result.data.message?result.data.message:"Service error")

        ElMessage.error(result.data.message?result.data.message:"Service error")

        return Promise.reject(result.data)
    },
    err=>{
        alert('服务异常');
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;