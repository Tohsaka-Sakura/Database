package com.hospital.service;

import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;

import javax.print.Doc;
import java.util.List;

public interface doctorService {

    Doctor findDoctorByUsername(String username);

    Doctor findDoctorRegisterByUsername(String username);

    void register(String username,String password);

    void updateDoctorInfo(Doctor doctor);

    List<Doctor> getAllDoctor();
}
