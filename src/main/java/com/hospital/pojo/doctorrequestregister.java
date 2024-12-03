package com.hospital.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class doctorrequestregister {
    String username;
    String password;
    LocalDateTime requireTime;

}
