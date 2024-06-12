package com.itheima.controller;

import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.itheima.pojo.Commodity;
import com.itheima.pojo.Result;
import com.itheima.service.BooksService;
import com.itheima.service.ChatService;
import net.sf.jsqlparser.statement.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private ChatService chatService;

    @GetMapping("/booksList")
    public Result getBooksList(){
        List<Commodity> books = booksService.getBooksList();
        if(books.size()!=0){
            return Result.success(books);
        }else{
            return Result.error("商品列表为空");
        }
    }
    @GetMapping("/getBookMessageList")
    public Result getBookMessageList(){
        List<Commodity> books = booksService.getBookMessageList();
        if(books.size()!=0){
            return Result.success(books);
        }else{
            return Result.error("商品列表为空");
        }
    }


    @PatchMapping("/insertBooks")
    public Result insertBooks(@RequestBody Commodity commodity){
        String problem = "帮我给这本书写一个100字简介,书名是："+commodity.getTitle();
        try{
            GenerationResult generationResult = chatService.chatAi(problem);
            String s = generationResult.getOutput().getChoices().get(0).getMessage().getContent();
            commodity.setBookdetails(s);
        }catch (Exception e){
            System.out.println(e);
        }

        booksService.insertBooks(commodity);

        return Result.success("插入成功");
    }
    @PatchMapping("/deleteBook")
    public Result deleteBook(@RequestBody Commodity commodity){

        String s = booksService.deleteBook(commodity);

        return Result.success(s);
    }
    @PatchMapping("/updateBooks")
    public Result updateBooks(@RequestBody Commodity commodity){
        booksService.updateBooks(commodity);
        return Result.success("更新成功");
    }
    @GetMapping("/getBookDetail")
    public Result getBookDetail(@RequestParam String bookId){
        Commodity commodity = booksService.getBookDetail(bookId);
        return Result.success(commodity);
    }


    @PatchMapping("/addToShoppingCart")
    public Result addToShoppingCart(@RequestParam String id,@RequestParam int number){
        Commodity commodity = booksService.getBookNums(id);
        int nums = commodity.getCommoditynums();
        if(nums==0||nums<number){
            return Result.error("商品数量不够，请联系管理员");
        }else {
            commodity.setCommoditynums(nums-number);
            booksService.updateBooks(commodity);
            return Result.success(nums);
        }

    }
}
