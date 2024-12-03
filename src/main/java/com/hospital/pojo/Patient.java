package com.hospital.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Patient {

    public Integer id;
    public String name;
    public String gender;
    public String username;
    private String password;

    public String contactNumber;

}
