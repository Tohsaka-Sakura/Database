package com.hospital.service;


import com.hospital.pojo.Patient;
import com.hospital.pojo.Record;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;


public interface patientService {
    Patient findByUserName(@Pattern(regexp = "^\\S{5,16}") String username);

    void register(@Pattern(regexp = "^\\S{5,16}") String username, @Pattern(regexp = "^\\S{5,16}") String password);

    void updatePatientInfo(Patient patient);

    Record findRecordByUsername(Integer ID);
}
