package com.itheima.service;

import com.itheima.pojo.Commodity;

import java.util.List;

public interface BooksService {
    List<Commodity> getBooksList();

    void insertBooks(Commodity commodity);

    Commodity getBookNums(String id);

    void updateBooks(Commodity commodity);

    List<Commodity> getBookMessageList();

    Commodity getBookDetail(String bookId);

    String deleteBook(Commodity commodity);
}
