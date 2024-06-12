package com.itheima.controller;

import com.alibaba.dashscope.aigc.generation.GenerationOutput;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.itheima.pojo.Result;
import com.itheima.pojo.gptQAndA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.itheima.service.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chatGpt")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public Result chatAi(@RequestParam String problem){

        GenerationResult generationResult;
        try{
            generationResult = chatService.chatAi(problem);
            String s = generationResult.getOutput().getChoices().get(0).getMessage().getContent();
            chatService.insertData(s);
            return Result.success(generationResult);
        }catch (ApiException apiException){

            return Result.error("访问失败"+apiException);
        }catch (NoApiKeyException noApiKeyException){

            return Result.error("访问失败"+noApiKeyException);
        }catch (InputRequiredException inputRequiredException){

            return Result.error("访问失败"+inputRequiredException);
        }


    }
    @GetMapping("/chatHistoryData")
    public Result chatHistoryData(){
        List<gptQAndA> QAndAhistory = chatService.getChatHistoryData();

        return Result.success(QAndAhistory);
    }
    @GetMapping("/removeHistoryData")
    public Result removeHistoryData(@RequestParam String dataId){

        chatService.removeHistoryData(dataId);
        return Result.success("删除成功");
    }

    @GetMapping("/deleteHistoryData")
    public Result deleteHistoryData(){
        chatService.deleteHistoryData();
        return Result.success("删除成功");
    }
}