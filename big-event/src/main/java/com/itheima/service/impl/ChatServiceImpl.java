package com.itheima.service.impl;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.itheima.mapper.ChatMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.gptQAndA;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itheima.service.ChatService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatMapper chatMapper;
    @Override
    public GenerationResult chatAi(String problem) throws NoApiKeyException, InputRequiredException {
        Constants.apiKey="sk-571b0c5b95b24b9cb5b7d9fc1b641de8";
        Generation gen = new Generation();
        MessageManager msgManager = new MessageManager(10);
        Message systemMsg =
                Message.builder().role(Role.SYSTEM.getValue()).content(problem).build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content(problem).build();
        msgManager.add(systemMsg);
        msgManager.add(userMsg);
        QwenParam param =
                QwenParam.builder().model(Generation.Models.QWEN_TURBO).messages(msgManager.get())
                        .resultFormat(QwenParam.ResultFormat.MESSAGE)
                        .topP(0.8)
                        .enableSearch(true)
                        .build();
        GenerationResult result = gen.call(param);

        return result;
    }

    @Override
    public List<gptQAndA> getChatHistoryData() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        return chatMapper.getChatHistoryData(userId);

    }

    @Override
    public Result insertData(String generationResult) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        chatMapper.insertData(id,userId,generationResult);
        return Result.success();
    }

    @Override
    public void removeHistoryData(String dataId) {
        chatMapper.removeHistoryData(dataId);
    }

    @Override
    public void deleteHistoryData() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        chatMapper.deleteHistoryData(userId);
    }

}