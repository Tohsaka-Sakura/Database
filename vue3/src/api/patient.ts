import request from '@/utils/request.js'

export const patientInfoListService = ()=>{

    return request.get('/doctor/doctorInfo')
}