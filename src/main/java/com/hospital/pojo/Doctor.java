package com.hospital.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Doctor {
    public Integer id;
    public String username;
    public String name;
    @JsonIgnore
    private String password;

    public String phone;
    public String department;

    @Email
    public String email;
}
