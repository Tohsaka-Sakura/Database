package com.hospital.mapper;


import com.hospital.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface adminMapper {

    @Select("select * from admin where username =#{username}")
    Admin findAdminByUsername(String username);

    @Insert("insert into admin(username,password) " +
            " values(#{username},#{password})")
    void register(String username, String password);

}
