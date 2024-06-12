package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

/**
 * @program: big-event
 * @description: ArticleService
 * @author: 高玉好
 * @create: 2024-05-21 15:33
 **/
public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void delArticle(Integer articleId);
}
