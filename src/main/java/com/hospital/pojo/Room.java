package com.hospital.pojo;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Room {
    private Integer id;
    private String Type;
}
