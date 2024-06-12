package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: big-event
 * @description: ArticleMapper
 * @author: 高玉好
 * @create: 2024-05-21 15:33
 **/
@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);
    @Delete("delete from article where id=#{articleId}")
    void delArticle(Integer articleId);
}
