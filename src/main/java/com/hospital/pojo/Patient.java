package com.hospital.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Patient {

    private Integer id;
    private String name;
    private String gender;
    private String username;
    private String password;

    private String contactNumber;

}
