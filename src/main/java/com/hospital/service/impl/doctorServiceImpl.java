package com.hospital.service.impl;

import com.hospital.mapper.doctorMapper;
import com.hospital.pojo.Detail;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Record;
import com.hospital.service.doctorService;
import com.hospital.service.patientService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class doctorServiceImpl implements doctorService {

    @Autowired
    private doctorMapper doctorMap;

    @Autowired
    private patientService patientServe;

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

    @Override
    public List<Record> findRecordByUsername(Integer ID){
        return doctorMap.findRecord(ID);
    }

    @Override
    public Patient findPatientByUsername(String username){
        return doctorMap.findPatient(username);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorMap.findAllDoctors();
    }

    @Override
    public List<Detail> getAllDetail(Integer DoctorID){
        List<Record> records = findRecordByUsername(DoctorID);
        List<Detail> details = new ArrayList<>();

        for (Record record : records) {
            String p = record.PatientID;
            Patient patient = doctorMap.findPatient(p);

            Detail detail = new Detail(
                    patient.name,
                    record.NurseId,
                    DoctorID,
                    patient.contactNumber,
                    patient.gender
            );

            details.add(detail);
        }

        return details;
    }

    @Override
    public void addMedicalRecord(Record medicalRecord) {
        doctorMap.addMedicalRecord(medicalRecord);
    }

}
