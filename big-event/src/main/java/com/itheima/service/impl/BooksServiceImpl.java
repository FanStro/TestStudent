package com.itheima.service.impl;

import com.itheima.mapper.BooksMapper;
import com.itheima.pojo.Commodity;
import com.itheima.service.BooksService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksMapper booksMapper;

    @Override
    public List<Commodity> getBooksList() {
        return booksMapper.getBooksList();
    }

    @Override
    public void insertBooks(Commodity commodity) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer updatebyid =(Integer) map.get("id");
        String ids = updatebyid.toString();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        String title = commodity.getTitle();
        Float price = commodity.getPrice();
        String author = commodity.getAuthor();
        int nums = commodity.getCommoditynums();
        String urlimage = commodity.getUrlimage();
        int state = commodity.getState();
        String bookdetails = commodity.getBookdetails();
        booksMapper.insertBooks(id,title,author,price,urlimage,state,ids,nums,bookdetails);
    }

    @Override
    public Commodity getBookNums(String id) {
        return booksMapper.getBookNums(id);
    }

    @Override
    public void updateBooks(Commodity commodity) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer updatebyid =(Integer) map.get("id");
        String ids = updatebyid.toString();
        String id = commodity.getId();

        String title = commodity.getTitle();
        Float price = commodity.getPrice();
        String author = commodity.getAuthor();
        int nums = commodity.getCommoditynums();
        String urlimage = commodity.getUrlimage();
        int state = commodity.getState();
        booksMapper.updateBooks(id,title,author,price,urlimage,ids,nums,state);
    }

    @Override
    public List<Commodity> getBookMessageList() {
        return booksMapper.getBookMessageList();
    }

    @Override
    public Commodity getBookDetail(String bookId) {
        return booksMapper.getBookDetail(bookId);
    }

    @Override
    public String deleteBook(Commodity commodity) {
        String id = commodity.getId();
        try{
            booksMapper.deleteBook(id);
            return "删除成功";
        }catch (Exception e){
            return "删除失败";
        }
    }
}
