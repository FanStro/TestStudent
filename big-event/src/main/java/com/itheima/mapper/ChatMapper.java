package com.itheima.mapper;

import com.itheima.pojo.gptQAndA;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {

    @Select("select * from userandhistory where userId=#{userId}")
    List<gptQAndA> getChatHistoryData(Integer userId);

    @Insert("insert into userandhistory(id,userId,userchardata) values(#{id},#{userId},#{userchardata})")
    void insertData(String id ,Integer userId,String userchardata);
    @Delete("delete from userandhistory where id=#{charId}")
    void removeHistoryData(String chatId);

    @Delete("delete from userandhistory where userId = #{userId}")
    void deleteHistoryData(int userId);
}