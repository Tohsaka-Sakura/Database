package com.hospital.service;

import com.hospital.pojo.Admin;
import jakarta.validation.constraints.Pattern;

public interface adminService {

    void register( String username, String password);

    Admin findAdminByUsername(String username);
}
