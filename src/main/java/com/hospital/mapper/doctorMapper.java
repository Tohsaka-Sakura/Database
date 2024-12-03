package com.hospital.mapper;


import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Record;
import com.hospital.pojo.doctorrequestregister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface doctorMapper {


    @Select("select * from doctor where username =#{username}")
    Doctor findDoctorByUsername(String username);

    @Select("select * from patient where username =#{username}")
    Patient findPatient(String username);

    @Select("select * from record where DoctorID =#{id}")
    List<Record> findRecord(Integer id);

    @Insert("insert into doctorrequireregister(username,password,require_time) " +
    " values(#{username},#{password},now())")
    void register(String username, String password);

    @Update("UPDATE doctor SET name = #{name}, department = #{department}, phone = #{phone}, email = #{email} WHERE username = #{username}")
    void updateDoctorInfo(Doctor doctor);

    @Select("select * from doctorrequireregister where username = #{username}")
    Doctor findDoctorRegisterByUsername(String username);

    @Select("SELECT * from doctor")
    List<Doctor> findAllDoctors();

    @Insert("INSERT INTO Record (PatientID, DoctorID, NurseID, Department, notes) " +
            "VALUES (#{PatientID}, #{DoctorID}, #{NurseID}, #{department}, #{notes})")
    void addMedicalRecord(Record medicalRecord);
}
