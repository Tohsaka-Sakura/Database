import { defineStore } from "pinia"
import { ref } from "vue"

const useDoctorIndoStore = defineStore('doctorInfo',()=>{

    const info = ref({})

    const setInfo = (newInfo)=>{
        info.value = newInfo
    }

    const removeInfo = () =>{
        info.value = {}
    }

    return {info,setInfo,removeInfo}

},{persist:true})

export default useDoctorIndoStore;