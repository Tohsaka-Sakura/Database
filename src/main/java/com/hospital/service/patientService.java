package com.hospital.service;


import com.hospital.pojo.Patient;
import com.hospital.pojo.PreviousRecord;
import com.hospital.pojo.Record;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;
import java.util.List;



public interface patientService {
    Patient findByUserName(@Pattern(regexp = "^\\S{5,16}") String username);

    void register(@Pattern(regexp = "^\\S{5,16}") String username, @Pattern(regexp = "^\\S{5,16}") String password);

    void updatePatientInfo(Patient patient);

    List<Record> findRecordByUsername(String ID);

    Patient findByID(Integer pid);

    void addprevious(PreviousRecord pre);
}
