package com.hospital.controller;


import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Result;
import com.hospital.pojo.Record;
import com.hospital.service.doctorService;
import com.hospital.service.recordService;
import com.hospital.utils.JwtUtil;
import com.hospital.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@Validated
public class doctorController {

    @Autowired
    private doctorService service;

    @Autowired
    private recordService medicalRecordService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}")String username,
                           @Pattern(regexp = "^\\S{5,16}")String password)
    {
        Doctor doctor = service.findDoctorByUsername(username);
        if(doctor == null){


            Doctor doctorRe = service.findDoctorRegisterByUsername(username);
            if(doctorRe == null)
            {
                service.register(username, password);
                return Result.success();

            }
            else
            {
                return Result.error("repeat register, please wait for admin to verify.");
            }

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

    @GetMapping("/doctorInfo")
    public Result<Doctor> doctorInfo(@RequestHeader(name = "Authorization") String token) {
        try {
            Map<String, Object> claims = JwtUtil.verifyToken(token);
            String username = (String) claims.get("username");
            Doctor doctor = service.findDoctorByUsername(username);
            if (doctor != null) {
                return Result.success(doctor);
                //return Result.error(patient.getUsername());
            } else {
                //return Result.error(username);
                return Result.error("Doctor not found: user "+username);
            }
        } catch (Exception e) {
            return Result.error("Invalid token");
        }
    }

    @PutMapping("/update")
    public Result updateDoctorInfo(@RequestBody Doctor doctor) {
        try {
            service.updateDoctorInfo(doctor);
            return Result.success();
        } catch (Exception e) {
            return Result.error("Update failed");
        }
    }

    @PostMapping("/addMedicalRecord")
    public Result addMedicalRecord(@RequestBody Record medicalRecord) {
        try {
            medicalRecordService.addMedicalRecord(medicalRecord);
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加病历失败");
        }
    }

}
