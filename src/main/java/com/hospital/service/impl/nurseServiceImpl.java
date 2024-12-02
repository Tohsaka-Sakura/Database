package com.hospital.service.impl;

import com.hospital.mapper.nurseMapper;
import com.hospital.pojo.Nurse;
import com.hospital.service.nurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class nurseServiceImpl implements nurseService {

    @Autowired
    private com.hospital.mapper.nurseMapper nurseMap;

    @Override
    public void addNurse(Nurse nurse) {
        // 这里加入护士的保存逻辑
        nurseMap.addNurse(nurse);
    }
}