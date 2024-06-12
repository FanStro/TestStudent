package com.itheima.service;

import com.itheima.pojo.Result;
import com.itheima.pojo.ShoppingCartItemDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface PayService {
    Result aliPayOrderSuccess();

    String alipay(ShoppingCartItemDTO shoppingCartItemDTO, HttpServletResponse response) throws Exception;
}

