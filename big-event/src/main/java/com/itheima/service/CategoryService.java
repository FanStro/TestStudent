package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

/**
 * @program: big-event
 * @description: CategoryService
 * @author: 高玉好
 * @create: 2024-05-21 14:11
 **/
public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);
}
