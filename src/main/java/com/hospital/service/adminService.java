package com.hospital.service;

import com.hospital.pojo.Admin;
import com.hospital.pojo.doctorrequestregister;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public interface adminService {

    void register( String username, String password);

    Admin findAdminByUsername(String username);

    List<doctorrequestregister> getRegister();

    doctorrequestregister getDoctorRegister(String username);

    void deleteDoctorRegister(String username);

    void updateDoctorRegister(doctorrequestregister doctorregister);
}
