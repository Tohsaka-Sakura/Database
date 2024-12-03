package com.hospital.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Record {
    private Integer Pid;
    private Integer Nid;
    private Integer Did;
    private String department;
    private String notes;
}