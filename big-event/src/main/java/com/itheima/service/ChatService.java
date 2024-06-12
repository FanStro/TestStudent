package com.itheima.service;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.itheima.pojo.Result;
import com.itheima.pojo.gptQAndA;

import java.util.List;

public interface ChatService {
    GenerationResult chatAi(String problem) throws NoApiKeyException, InputRequiredException;

    List<gptQAndA> getChatHistoryData();

    Result insertData(String generationResult);

    void removeHistoryData(String dataId);

    void deleteHistoryData();
}