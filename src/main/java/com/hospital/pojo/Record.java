package com.hospital.pojo;

import lombok.Data;

@Data
public class Record {
    public String PatientID;
    public Integer NurseId;
    public Integer DoctorID;
    private String department;
    private String notes;
}