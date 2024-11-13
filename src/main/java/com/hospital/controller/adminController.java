package com.hospital.controller;


import com.hospital.pojo.Admin;
import com.hospital.pojo.Result;
import com.hospital.service.adminService;
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


}
