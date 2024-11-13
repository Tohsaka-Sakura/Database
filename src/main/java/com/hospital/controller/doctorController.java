package com.hospital.controller;


import com.hospital.pojo.Doctor;
import com.hospital.pojo.Result;
import com.hospital.service.doctorService;
import com.hospital.utils.JwtUtil;
import com.hospital.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@Validated
public class doctorController {

    @Autowired
    private doctorService service;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}")String username,
                           @Pattern(regexp = "^\\S{5,16}")String password)
    {
        Doctor doctor = service.findDoctorByUsername(username);
        if(doctor == null){
            service.register(username, password);
            return Result.success();
        }
        else{
            return Result.error("useless username");
        }

    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}")String username,
                        @Pattern(regexp = "^\\S{5,16}")String password){
        Doctor doctor = service.findDoctorByUsername(username);
        if(doctor == null){
            return Result.error("username or password error");
        }

        if(Md5Util.getMD5String(password).equals(doctor.getPassword())){

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", doctor.getId());
            claims.put("username", doctor.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }

        return Result.error("username or password error");
    }


}
