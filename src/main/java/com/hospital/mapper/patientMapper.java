package com.hospital.mapper;


import com.hospital.pojo.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface patientMapper {


    @Select("select * from patient where username =#{username}")
    Patient findByUserName(String username);


    @Insert("insert into patient(username,password,create_time,update_time)" +
    " values(#{username}, #{password}, now(), now())")
    void register(String username, String password);


}
