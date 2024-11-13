package com.hospital.service;

import com.hospital.pojo.Doctor;

import javax.print.Doc;

public interface doctorService {

    Doctor findDoctorByUsername(String username);

    void register(String username,String password);
}
