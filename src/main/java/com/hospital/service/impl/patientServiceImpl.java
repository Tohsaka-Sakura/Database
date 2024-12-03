package com.hospital.service.impl;

import com.hospital.mapper.patientMapper;
import com.hospital.pojo.Patient;
import com.hospital.pojo.PreviousRecord;
import com.hospital.pojo.Record;
import com.hospital.service.patientService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class patientServiceImpl implements patientService {


    @Autowired
    private patientMapper patientMap;

    @Override
    public Patient findByUserName(String username) {
        return patientMap.findByUserName(username);
    }

    @Override
    public Patient findByID(Integer pid) {
        return patientMap.findByID(pid);
    }


    @Override
    public void register(String username, String password) {

        String md5Password = Md5Util.getMD5String(password);

        patientMap.register(username,md5Password);
    }

    @Override
    public void updatePatientInfo(Patient patient) {
        patientMap.updatePatientInfo(patient);
    }

    @Override
    public List<Record> findRecordByUsername(String ID){
        return patientMap.findRecord(ID);
    }

    @Override
    public void addprevious(PreviousRecord pre) {
        patientMap.addprevious(pre);
    }

}
