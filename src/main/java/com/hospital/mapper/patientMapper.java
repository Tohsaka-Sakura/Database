package com.hospital.mapper;


import com.hospital.pojo.Patient;
import com.hospital.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface patientMapper {


    @Select("select * from patient where username =#{username}")
    Patient findByUserName(String username);


    @Insert("insert into patient(username,password)" +
            " values(#{username}, #{password})")
    void register(String username, String password);

    @Update("UPDATE patient SET name = #{name}, gender = #{gender}, contact_number = #{contactNumber}, update_time = now() WHERE username = #{username}")
    void updatePatientInfo(Patient patient);

    @Select("select * from record where patientID =#{ID}")
    Record findRecord(Integer ID);

}
