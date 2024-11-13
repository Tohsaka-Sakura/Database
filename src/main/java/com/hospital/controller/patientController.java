package com.hospital.controller;


import com.hospital.pojo.Result;
import com.hospital.pojo.Patient;
import com.hospital.service.patientService;
import com.hospital.utils.JwtUtil;
import com.hospital.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patient")
@Validated
public class patientController {

    @Autowired
    private patientService pService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}") String username,@Pattern(regexp = "^\\S{5,16}") String password){

        Patient u = pService.findByUserName(username);

        if(u == null){

            pService.register(username,password);
            return Result.success();
        }
        else{
            return Result.error("useless username");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}") String username,String password){
        Patient u = pService.findByUserName(username);
        if(u == null){
            return Result.error("username or password error");
        }

        if(Md5Util.getMD5String(password).equals(u.getPassword())){

            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);
            String id = claims.get("id").toString();

            redisTemplate.opsForValue().set(id,token);
            return Result.success(token);
        }

        return Result.error("username or password error");
    }

    @GetMapping("/patientInfo")
    public Result<Patient> PatientIfno(@RequestHeader(name = "Patient") String token){



        return Result.success();

    }






}
