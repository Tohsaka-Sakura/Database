package com.hospital.controller;


import com.hospital.pojo.*;
import com.hospital.pojo.Record;
import com.hospital.service.doctorService;
import com.hospital.service.nurseService;
import com.hospital.service.roomService;
import com.hospital.utils.JwtUtil;
import com.hospital.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
@Validated
public class doctorController {

    @Autowired
    private doctorService service;


    @Autowired
    private nurseService nurseService;

    @Autowired
    private roomService roomService;


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
    public Result<Doctor> doctorInfo(@RequestHeader("Authorization") String token) {
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
            return Result.success("success");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/addNurse")
    public Result addNurse(@RequestBody Nurse nurse) {
        try {
            // 调用护士服务的方法添加护士
            nurseService.addNurse(nurse);
            return Result.success("护士添加成功");
        } catch (Exception e) {
            return Result.error("添加护士失败: " + e.getMessage());
        }
    }

    @PostMapping("/addRoom")
    public Result addRoom(@RequestBody Room room) {
        try {
            // 调用护士服务的方法添加护士
            roomService.addRoom(room);
            return Result.success("科室添加成功");
        } catch (Exception e) {
            return Result.error("科室护士失败: " + e.getMessage());
        }
    }

    @PostMapping("/addMedicalRecord")
    public Result addMedicalRecord(@RequestBody Record medicalRecord) {
        try {
            // 在这里可以添加护士和科室的信息
            service.addMedicalRecord(medicalRecord);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/allDoctorInfo")
    public Result allDoctorInfo() {
        return Result.success(service.getAllDoctor());
    }


    @GetMapping("/allRecord")
    public Result<List<Detail>> allRecord(@RequestHeader("Authorization") String token) {
        try {
            Map<String, Object> claims = JwtUtil.verifyToken(token);
            String username = (String) claims.get("username");
            Doctor doctor = service.findDoctorByUsername(username);
            if (doctor != null) {
                List<Detail> details = service.getAllDetail(doctor.id);
                return Result.success(details);
                //return Result.error(doctor.id.toString());
            } else {
                //return Result.error(username);
                return Result.error("Doctor not found: user "+username);
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
