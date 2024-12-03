package com.hospital.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Recordt {
    private int patientId;
    private int doctorId;

    private String  diseaseName;

    LocalDateTime dateDiagnosis;
    String AttendHospital;

}
