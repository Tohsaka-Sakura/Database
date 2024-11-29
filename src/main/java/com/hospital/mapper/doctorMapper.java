package com.hospital.mapper;


import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface doctorMapper {


    @Select("select * from doctor where username =#{username}")
    Doctor findDoctorByUsername(String username);

    @Insert("insert into doctor(username,password) " +
    " values(#{username},#{password})")
    void register(String username, String password);

    @Update("UPDATE patient SET name = #{name}, department = #{department}, phone = #{phone}, email = #{email} WHERE username = #{username}")
    void updateDoctorInfo(Doctor doctor);

}
