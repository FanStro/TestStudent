package com.itheima.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.itheima.pojo.Result;
import com.itheima.pojo.ShoppingCart;
import com.itheima.pojo.ShoppingCartItemDTO;
import com.itheima.service.PayService;
import com.itheima.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(  propagation= Propagation.SUPPORTS,
        isolation= Isolation.DEFAULT,  timeout=2000,
        readOnly=true, rollbackFor=RuntimeException.class
)

public class PayServieImpl implements PayService {
    //appid
    private final String APP_ID = "9021000137652356"; //改为自己的
    //应用私钥
    private final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVrC8Yq/ouNGGDVVQain1wTDchZzoy0k2z3SKTXMoOOnrKi1qTG9P6AKMCYXywXgNjTIvtIgNOfjt14LgjbUYiE5j1nfGYSrOQk0ulEP3rHWtufHCUoy1lelARw7R3vzGpVnOVcapfuKXAmg9RkdWaDtJ9VCqkKpCQJMShp/RDtU/3qn1X70ARgH1/0Hd1WLqNR2hlU4A7nbUr3PD40Oa7zNOQcjHe8zg/THzGbTpsrtcHl6pxSZMWzHuwX2xuT26oiSjMen0SSGYI3Qt06Cmv/pRcVEJ+Wzo/8wzNQjQfZAgNOb8GEJF6mQJ1eA0VHSmQ6pTlf4M4zHFXCmYhpK99AgMBAAECggEAHk94Wi65w5GoDcjB934EWTGWGJWnG5OrWLl3vxATez4sj8gY16HTE9X9LQZBEhZ36SwfSi6RLNwzfnO71rDSeyQs3dDWlFjMdILf1tXSFkMm9oEM640ALITQokhXy8hUbk6rqaATOL9UEeUhJQu7ji9iQS9CU2yrSgWoLHSu6ucE09D6eS9i6RHVYow+M1XA/oLecSq1U9e7UoX9VhHNSk9cS+trVrPaLepUuUhuAd45rQlGjbNmur67q2vJ1po9lDGzG4lt/pErUireiQiJ2THq3euEpAyUOiCY7ZX4AMGwkHXvf1avsSLrAgUk+fvZwvPw+ZUC+JlDAB9Ze2KKoQKBgQDjkD8zo6Z1uVRQOIfdya8CPALXfVI9HgICSH7gOybIYbbfAdnoX0vrNMR7P01GCX6UnSINNHSg6KnL4S1SFpwP+ys6aYqyPKRaHzSm+WxXq82PrZ/qIYgfb9bADWLONSd6P88x4BUGf9fhmFDdAXFqtNgSvV77R4EGXPo1/6kEMwKBgQCoYDV3fhXxh8NGp7IiPi5XyqKZeEMud1Gla3GC2hL8BAukty8P5nCBAmuP4tb60vtDR1/Ji1Z7Frq2ZzOLZ9MTtnwwfABtoaePKoC4qQXfQOq6nep1YZWvIS84kejbhg6LSr6mqX1WDf4ntmsafYLvfYZRwFC9/0FRfS5m3ChNjwKBgQCLxut7TZcOkpK+X6rcyWgtCC16rWiZT9hODLeaBdtRk+ue2WU+vWJFi/jv9zcAwQ42PLpQwBqvR5ocOCj9VhPwIQgxbeiaiHMvRCG0OlqZE1dFXJFwX75cCPcwqkg5fx7LkWw0J37iqh4xXLy2s481yvU5tYYk9IvFG9E439uyTwKBgAk0kVfCFfMJiByRyIRkv+Xe1zrqv0mIDnerwx3zTDxpADX2KeWFpLr45eaM8VHvPPt1qH/9VTIPkQ+t3ryNZZXbUTjZXrCfuOhz22u9GewVwX+IaKwYNLXJa6p9F60AzETDRl8YPxj4VN/Y8kwVdpYfV7uadw+cJ/cLkADhonZzAoGBAKXJH/FZO85HERYRhU56ylUqe7XcFTWRoJVfW9wkXPRkoDEwLDEk2LT6cEkfF2n4/JCi7WVb9LK2pEIbM9/oaYhLokrv+sfMm687lFJ4cYxI3wdWs5aT2YYz76K8lpFV8MDAVe0fMsfYg88eO74uwLe9I8jkCvjF8ZQNy615iR5O";  //改为自己的
    private final String CHARSET = "UTF-8";  //不需要改
    // 支付宝公钥
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7ZtszBqZ/bfVFZI5uLoMKholMQHP6euVD1LvFPcg3AS9mB24/xuFHb5ZE01MOi2glbF09L4dHgh3slsLRDsZ6JgdeVfBLa+BO8IZB9Ugu3foDlDqgWJiZtdA0IHIdDRiru8y6XnZWFZxzsvzpPsu0vavTtir20BROidcebcOABfu1CIBB7PykGg99M/BD84WECGao19X22zHoQOW1lF8TKcFiQx9VAGA5xraXxkityTYgyMZ5PIrSD5IHTKVw7U5pirjnIbmSg13bf1j08SUvLcZNcIoIdQ/7HMztRo4lYXjDakuTnCB3hd/+rfs9trSV9Iqq9EJJzpGXX8xsi+eHwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do"; //不需要改
    private final String FORMAT = "JSON"; //不需要改
    //签名方式
    private final String SIGN_TYPE = "RSA2";  //不需要改
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://localhost:5173/user/shoppingCart";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:5173/user/shoppingCart";
    @Autowired
    ShoppingCartService shoppingCartService;
    @Override
    public Result aliPayOrderSuccess() {
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setCommoditynum(shoppingCartItemDTO.getCommoditynum());
//        shoppingCart.setId(shoppingCartItemDTO.getId());
//        shoppingCart.setUserid(shoppingCartItemDTO.getUserid());
//        shoppingCart.setCommodityid(shoppingCartItemDTO.getCommodityid());
//        shoppingCart.setAddressid(shoppingCartItemDTO.getAddressid());
//        shoppingCart.setState("1");
//        shoppingCartService.updateShoppingCart(shoppingCart);

        return Result.success();
    }

    @Override
    public String alipay(ShoppingCartItemDTO shoppingCartItemDTO, HttpServletResponse response) throws Exception {
        String result = sendRequestToAlipay(shoppingCartItemDTO, "abcd",response);

        return result;
    }
    private String sendRequestToAlipay(ShoppingCartItemDTO shoppingCartItemDTO, String subject, HttpServletResponse httpResponse) throws AlipayApiException, IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(RETURN_URL);
        alipayRequest.setNotifyUrl(NOTIFY_URL);

        //商品描述（可空）
        String body = "欢迎下单";
        alipayRequest.setBizContent("{\"orderinfo\":\"" + shoppingCartItemDTO + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + shoppingCartItemDTO.getId() + "\","
                + "\"total_amount\":\"" + shoppingCartItemDTO.getCommoditynum()*shoppingCartItemDTO.getPrice() + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //此部分必须要写 否则不会跳转到支付页面
//        httpResponse.setContentType("text/html;charset=" + CHARSET);
//        httpResponse.getWriter().write(result);// 直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();
        return result;
    }

}
