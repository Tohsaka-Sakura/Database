package com.hospital.service.impl;

import com.hospital.mapper.adminMapper;
import com.hospital.pojo.Admin;
import com.hospital.pojo.Patient;
import com.hospital.pojo.doctorrequestregister;
import com.hospital.service.adminService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class adminServiceImpl implements adminService {

    @Autowired
    private adminMapper adminMap;

    @Override
    public Admin findAdminByUsername(String username) {
        return adminMap.findAdminByUsername(username);
    }

    @Override
    public List<doctorrequestregister> getRegister() {
        return adminMap.getAllDoctorRegister();
    }

    @Override
    public doctorrequestregister getDoctorRegister(String username) {
        return adminMap.findRequestByUsername(username);
    }

    @Override
    public void deleteDoctorRegister(String username) {
        adminMap.deleteRequire(username);
    }

    @Override
    public void updateDoctorRegister(doctorrequestregister doctorregister) {
        adminMap.allowRequire(doctorregister.getUsername(),doctorregister.getPassword());
    }

    @Override
    public void register(String username, String password) {

        String md5Pwd = Md5Util.getMD5String(password);

        adminMap.register(username,md5Pwd);


    }

    @Override
    public void deleteDoctorByUsername(String username) {
        adminMap.deleteDoctorByUsername(username);
    }

    @Override
    public List<Patient> getAllPatient() {
        return adminMap.findAllPatient();
    }

    @Override
    public void deletePatientByUsername(String username) {
        adminMap.deletePatientByUsername(username);
    }
}
