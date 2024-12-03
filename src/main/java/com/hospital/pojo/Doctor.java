package com.hospital.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Doctor {
    private Integer id;
    private String username;
    private String name;
    @JsonIgnore
    private String password;

    private String phone;
    private String department;

    @Email
    private String email;
}
