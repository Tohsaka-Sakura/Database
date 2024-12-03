package com.hospital.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PreviousRecord {
    private String hospital;
    private String doctor;
    private String note;
    private LocalDateTime date;
}
