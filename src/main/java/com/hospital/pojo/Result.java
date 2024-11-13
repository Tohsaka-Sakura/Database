package com.hospital.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    Integer code;
    String message;
    T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(0,"success",data);
    }

    public static <T> Result<T> success() {

        return new Result<>(0,"success",null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>( 1, message,null);
    }

}
