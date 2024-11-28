package com.hospital.mapper;


import com.hospital.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface recordMapper {

    @Insert("INSERT INTO medical_records(patient_id, time, department, notes, create_time) " +
            "VALUES(#{patientId}, #{time}, #{department}, #{notes}, now())")
    void addMedicalRecord(Record medicalRecord);
}