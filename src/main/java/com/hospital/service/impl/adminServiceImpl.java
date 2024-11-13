package com.hospital.service.impl;

import com.hospital.mapper.adminMapper;
import com.hospital.pojo.Admin;
import com.hospital.service.adminService;
import com.hospital.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class adminServiceImpl implements adminService {

    @Autowired
    private adminMapper adminMap;

    @Override
    public Admin findAdminByUsername(String username) {
        return adminMap.findAdminByUsername(username);
    }

    @Override
    public void register(String username, String password) {

        String md5Pwd = Md5Util.getMD5String(password);

        adminMap.register(username,md5Pwd);


    }
}
