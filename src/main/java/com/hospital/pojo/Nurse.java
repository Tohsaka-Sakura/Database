package com.hospital.pojo;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Nurse {
    private Integer id;
    private String name;
    private Integer room;
    private String department;
}
