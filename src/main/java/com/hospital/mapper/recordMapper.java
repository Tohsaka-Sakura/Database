package com.hospital.mapper;


import com.hospital.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface recordMapper {

    @Insert("INSERT INTO record(patientId, time, department, notes) " +
            "VALUES(#{patientId}, #{time}, #{department}, #{notes})")
    void addMedicalRecord(Record medicalRecord);
}