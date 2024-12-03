package com.hospital.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Patient {

    private Integer id;
    private String name;
    private String gender;
    private String username;
    @JsonIgnore
    private String password;

    private String contactNumber;

}
