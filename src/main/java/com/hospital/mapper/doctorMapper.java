package com.hospital.mapper;


import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.doctorrequestregister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface doctorMapper {


    @Select("select * from doctor where username =#{username}")
    Doctor findDoctorByUsername(String username);

    @Insert("insert into doctorrequireregister(username,password) " +
    " values(#{username},#{password})")
    void register(String username, String password);

    @Update("UPDATE doctor SET name = #{name}, department = #{department}, phone = #{phone}, email = #{email} WHERE username = #{username}")
    void updateDoctorInfo(Doctor doctor);

    @Select("select * from doctorrequireregister where username = #{username}")
    Doctor findDoctorRegisterByUsername(String username);


}
