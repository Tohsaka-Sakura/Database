package com.hospital.mapper;


import com.hospital.pojo.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface doctorMapper {


    @Select("select * from doctor where username =#{username}")
    Doctor findDoctorByUsername(String username);

    @Insert("insert into doctor(username,password) " +
    " values(#{username},#{password})")
    void register(String username, String password);
}
