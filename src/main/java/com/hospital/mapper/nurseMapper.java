package com.hospital.mapper;

import com.hospital.pojo.Nurse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface nurseMapper {

    @Insert("INSERT INTO nurses(name, room, department) VALUES(#{name}, #{room}, #{department})")
    void addNurse(Nurse nurse);
}