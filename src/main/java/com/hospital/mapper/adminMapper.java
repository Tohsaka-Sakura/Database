package com.hospital.mapper;


import com.hospital.pojo.Admin;
import com.hospital.pojo.Patient;
import com.hospital.pojo.doctorrequestregister;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface adminMapper {

    @Select("select * from admin where username =#{username}")
    Admin findAdminByUsername(String username);

    @Insert("insert into admin(username,password) " +
            " values(#{username},#{password})")
    void register(String username, String password);

    @Select("select * from doctorrequireregister")
    List<doctorrequestregister> getAllDoctorRegister();

    @Delete("DELETE FROM doctorrequireregister where username = #{username}")
    void deleteRequire(String username);

    @Insert("insert into doctor(username,password) " +
    " values(#{username},#{password})")
    void allowRequire(String username, String password);

    @Select("select * from doctorrequireregister where username = #{username}")
    doctorrequestregister findRequestByUsername(String username);

    @Delete("DELETE FROM doctor where username=#{username}")
    void deleteDoctorByUsername(String username);

    @Select("SELECT * FROM patient")
    List<Patient> findAllPatient();

    @Delete("DELETE FROM patient where username = #{username}")
    void deletePatientByUsername(String username);

}
