package com.itheima.mapper;

import com.itheima.pojo.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BooksMapper {
    @Select("select * from commodity where state = 1")
    List<Commodity> getBooksList();

    @Insert("insert into commodity(id,title,author,\n" +
            "price,\n" +
            "urlimage,\n" +
            "updatebyId,\n" +
            "commoditynums,createtime, updatetime,state,bookdetails) values(#{id},#{title},#{author},#{price},#{urlimage},#{updatebyid},#{nums},now(),now(),#{state},#{bookdetails})")
    void insertBooks(String id, String title, String author, Float price, String urlimage,int state, String updatebyid, int nums,String bookdetails);

    @Select("select * from commodity where id = #{id}")
    Commodity getBookNums(String id);

    @Update("update commodity set title=#{title}, author=#{author}, price=#{price}, urlimage=#{urlimage}, updatebyid=#{updatebyid}, commoditynums=#{nums}, updatetime=now(),state = #{state} where id=#{id}")
    void updateBooks(String id, String title, String author, Float price, String urlimage, String updatebyid, int nums,int state);
    @Select("select * from commodity")
    List<Commodity> getBookMessageList();

    @Select("select * from commodity where id=#{bookId}")
    Commodity getBookDetail(String bookId);

    @Delete("delete from commodity where id = #{id}")
    void deleteBook(String id);
}
