package com.hospital.pojo;

public class Detail {
    public String PatientName;
    public Integer NurseId;
    public Integer DoctorId;
    public String contactNumber;
    public String gender;

    public Detail(String patientName, Integer nurseId, Integer roomId, String contactNumber, String gender) {
        this.PatientName = patientName;
        this.NurseId = nurseId;
        this.DoctorId = roomId;
        this.contactNumber = contactNumber;
        this.gender = gender;
    }
}
