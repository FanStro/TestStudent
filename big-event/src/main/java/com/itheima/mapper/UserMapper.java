package com.itheima.mapper;

import com.itheima.pojo.Address;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: big-event
 * @description: UserMapper
 * @author: 高玉好
 * @create: 2024-05-21 08:47
 **/
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);
    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{password},now(),now())")
    void add(String username, String password);
    @Update("update user set username=#{username},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);
    @Update("update user set password=#{md5String},update_time=now() where id = #{id}")
    void updatePwd(String md5String, Integer id);

    @Select("select * from address")
    List<Address> getAddressInfoStore();

    @Insert("insert into address(id,name,phone,address) values(#{id},#{name},#{phone},#{address})")
    void addAddressInfo(String id, String name, String phone, String address);

    @Update("update address set name = #{name},phone=#{phone},address=#{address} where id=#{id}")
    void updateAddressInfo(String id, String name, String phone, String address);

    @Delete("delete from address where id = #{addressId}")
    void deleteAddressInfo(String addressId);
}
