package com.hospital.mapper;


import com.hospital.pojo.Patient;
import com.hospital.pojo.PreviousRecord;
import com.hospital.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface patientMapper {


    @Select("select * from patient where username =#{username}")
    Patient findByUserName(String username);

    @Select("select * from patient where id =#{id}")
    Patient findByID(Integer id);

    @Insert("insert into patient(username,password)" +
            " values(#{username}, #{password})")
    void register(String username, String password);

    @Update("UPDATE patient SET name = #{name}, gender = #{gender}, contact_number = #{contactNumber} WHERE username = #{username}")
    void updatePatientInfo(Patient patient);

    @Select("select * from record where patientID =#{ID}")
    List<Record> findRecord(String ID);

    @Insert("INSERT INTO PreviousRecord (hospital, doctor, note, date) " +
            "VALUES (#{hospital}, #{doctor}, #{note}, #{date})")
    void addprevious(PreviousRecord previousRecord);


}
