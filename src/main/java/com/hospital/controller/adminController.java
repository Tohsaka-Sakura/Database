package com.hospital.controller;


import com.hospital.pojo.Admin;
import com.hospital.pojo.Result;
import com.hospital.pojo.doctorrequestregister;
import com.hospital.service.adminService;
import com.hospital.utils.JwtUtil;
import com.hospital.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Validated
public class adminController {

    @Autowired
    private adminService adminservice;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}")String username,
                           @Pattern(regexp = "^\\S{5,16}")String password)
    {
        Admin admin = adminservice.findAdminByUsername(username);



        if(admin == null){
            adminservice.register(username, password);
            return Result.success();
        }
        else{

            return Result.error("useless username");
        }

    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}")String username,
                        @Pattern(regexp = "^\\S{5,16}")String password){
        Admin admin = adminservice.findAdminByUsername(username);

        if(admin == null){
            return Result.error("username or password error");
        }

        if(Md5Util.getMD5String(password).equals(admin.getPassword())){

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", admin.getId());
            claims.put("username", admin.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }

        return Result.error("username or password error");
    }

    @GetMapping("/check")
    public Result checkRequest()
    {
        return Result.success(adminservice.getRegister());
    }

    @PutMapping("/reject")
    public Result rejectRequest(String username) {
        try {
            doctorrequestregister doctorregister = adminservice.getDoctorRegister(username);
            if (doctorregister == null) {
                return Result.error("Doctor request not found");
            }
            adminservice.deleteDoctorRegister(username);
            return Result.success("Request rejected successfully");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/verfiry")
    public Result verifier(String username){
        doctorrequestregister doctorregister = new doctorrequestregister();
        doctorregister = adminservice.getDoctorRegister(username);
        if(doctorregister == null){
            return Result.error("get doctor request register error");
        }
        else {
            try {
                adminservice.deleteDoctorRegister(username);
                adminservice.updateDoctorRegister(doctorregister);
                return Result.success("register success");
            }
            catch (Exception e) {
                return Result.error(e.getMessage());
            }

        }
    }


}
