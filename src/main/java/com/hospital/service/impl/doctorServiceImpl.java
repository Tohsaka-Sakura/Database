package com.hospital.service.impl;

import com.hospital.mapper.doctorMapper;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.service.doctorService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class doctorServiceImpl implements doctorService {

    @Autowired
    private doctorMapper doctorMap;

    @Override
    public Doctor findDoctorByUsername(String username) {
        return doctorMap.findDoctorByUsername(username);
    }

    @Override
    public Doctor findDoctorRegisterByUsername(String username) {
        return doctorMap.findDoctorRegisterByUsername(username);
    }

    @Override
    public void register(String username, String password) {

        String md5Pwd = Md5Util.getMD5String(password);

        doctorMap.register(username,md5Pwd);


    }

    @Override
    public void updateDoctorInfo(Doctor doctor) {
        doctorMap.updateDoctorInfo(doctor);
    }
}
