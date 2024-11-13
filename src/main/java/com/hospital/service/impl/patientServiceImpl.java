package com.hospital.service.impl;

import com.hospital.mapper.patientMapper;
import com.hospital.pojo.Patient;
import com.hospital.service.patientService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class patientServiceImpl implements patientService {


    @Autowired
    private patientMapper patientMapper;

    @Override
    public Patient findByUserName(String username) {
        return patientMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {

        String md5Password = Md5Util.getMD5String(password);

        patientMapper.register(username,md5Password);
    }


}
