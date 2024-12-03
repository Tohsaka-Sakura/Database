package com.hospital.service;

import com.hospital.pojo.Doctor;
import com.hospital.pojo.Detail;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Record;

import javax.print.Doc;
import java.util.List;

public interface doctorService {

    Doctor findDoctorByUsername(String username);

    Patient findPatientByUsername(String username);

    Doctor findDoctorRegisterByUsername(String username);

    void register(String username,String password);

    void updateDoctorInfo(Doctor doctor);

    List<Detail> getAllDetail(Integer DoctorID);

    List<Record> findRecordByUsername(Integer ID);

    List<Doctor> getAllDoctor();

    void addMedicalRecord(Record medicalRecord);
}
