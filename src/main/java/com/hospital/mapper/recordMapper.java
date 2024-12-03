package com.hospital.mapper;

import com.hospital.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface recordMapper {

    @Insert("INSERT INTO Record (PatientID, DoctorID, NurseID, Department, notes) " +
            "VALUES (#{Pid}, #{Did}, #{Nid}, #{department}, #{notes})")
    void addMedicalRecord(Record medicalRecord);
}