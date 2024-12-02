package com.hospital.mapper;

import com.hospital.pojo.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface roomMapper {

    @Insert("INSERT INTO Room (Type) VALUES(#{Type})")
    void addRoom(Room room);
}