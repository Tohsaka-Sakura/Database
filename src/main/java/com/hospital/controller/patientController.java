package com.hospital.controller;


import com.hospital.pojo.PreviousRecord;
import com.hospital.pojo.Result;
import com.hospital.pojo.Record;
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
import java.util.List;


@RestController
@Validated
@RequestMapping("/patient")
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
            return Result.error("invalid username");
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
    public Result<Patient> patientInfo(@RequestHeader(name = "Authorization") String token) {
        try {
            Map<String, Object> claims = JwtUtil.verifyToken(token);
            String username = (String) claims.get("username");
            Patient patient = pService.findByUserName(username);
            if (patient != null) {
                return Result.success(patient);
                //return Result.error(patient.getUsername());
            } else {
                //return Result.error(username);
                return Result.error("Patient not found: user "+username);
            }
        } catch (Exception e) {
            return Result.error("Invalid token");
        }
    }

    @GetMapping("/record")
    public Result<List<Record>> patientRecord(@RequestHeader(name = "Authorization") String token) {
        try {
            Map<String, Object> claims = JwtUtil.verifyToken(token);
            String username = (String) claims.get("username");
            List<Record> record = pService.findRecordByUsername(username);
            if (record != null) {
                return Result.success(record);
                //return Result.error(patient.getUsername());
            } else {
                //return Result.error(username);
                return Result.error("Record not found: user "+username);
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result updatePatientInfo(@RequestBody Patient patient) {
        try {
            pService.updatePatientInfo(patient);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/addPreviousRecord")
    public Result addPrevious(@RequestBody PreviousRecord pre) {
        try {
            pService.addprevious(pre);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
