package com.hospital.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Record {
    private String patientId;
    private LocalDateTime time;
    private String department;
    private String notes;
}