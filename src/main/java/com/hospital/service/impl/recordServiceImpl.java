package com.hospital.service.impl;

import com.hospital.mapper.recordMapper;
import com.hospital.pojo.Record;
import com.hospital.service.recordService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class recordServiceImpl implements recordService {

    @Autowired
    private recordMapper recordMap;

    @Override
    public void addMedicalRecord(Record medicalRecord) {
        recordMap.addMedicalRecord(medicalRecord);
    }
}
