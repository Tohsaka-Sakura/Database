package com.hospital.service;

import com.hospital.pojo.Admin;
import com.hospital.pojo.Record;
import jakarta.validation.constraints.Pattern;

public interface adminService {

    void register( String username, String password);

    Admin findAdminByUsername(String username);

}
