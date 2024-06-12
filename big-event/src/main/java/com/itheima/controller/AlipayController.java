package com.itheima.controller;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.domain.BusinessParams;
import com.alipay.api.request.AlipayTradePrecreateRequest;

import com.alipay.api.FileItem;
import com.itheima.pojo.Result;
import com.itheima.pojo.ShoppingCartItemDTO;
import com.itheima.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/pay")
    public Result pay(@RequestParam String shoppingCartId) throws AlipayApiException, IOException {
        System.out.println("支付宝");
        ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartService.getShoppingCartById(shoppingCartId);
        // 初始化SDK
        AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());

        // 构造请求参数以调用接口
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        // 设置商户门店编号
        model.setStoreId("NJ_001");

        // 设置业务扩展参数
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088721036503764");
        extendParams.setSpecifiedSellerName("樊福蕰的跨境小铺");
        extendParams.setCardType("S0JP0000");
        model.setExtendParams(extendParams);
//        shoppingCartService.getShoppingCartById(shoppingCartId);
        // 设置订单标题
        model.setSubject(shoppingCartItemDTO.getTitle()+"购买");

        // 设置商户操作员编号
        model.setOperatorId("yx_001");

        // 设置产品码
        model.setProductCode(shoppingCartItemDTO.getCommodityid());

        // 设置订单附加信息
        model.setBody(shoppingCartItemDTO.getTitle());

        // 设置订单包含的商品列表信息
        List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
        GoodsDetail goodsDetail0 = new GoodsDetail();
        goodsDetail0.setGoodsName(shoppingCartItemDTO.getTitle());
        goodsDetail0.setQuantity(1L);
        goodsDetail0.setPrice(Float.toString(shoppingCartItemDTO.getCommoditynum()*shoppingCartItemDTO.getPrice()));
        goodsDetail0.setGoodsId(shoppingCartItemDTO.getId());

        goodsDetail.add(goodsDetail0);
        model.setGoodsDetail(goodsDetail);

        // 设置商户的原始订单号
        model.setMerchantOrderNo(shoppingCartItemDTO.getId());
//
//        // 设置可打折金额
//        model.setDiscountableAmount("80.00");

        // 设置商户订单号
        model.setOutTradeNo(shoppingCartItemDTO.getId());

        // 设置商户传入业务信息
        BusinessParams businessParams = new BusinessParams();
//        businessParams.setMcCreateTradeIp("127.0.0.1");

        model.setBusinessParams(businessParams);

        // 设置卖家支付宝用户ID
        model.setSellerId("2088721036503764");

        // 设置商户机具终端编号
        model.setTerminalId("NJ_T_001");

        request.setBizModel(model);
        // 第三方代调用模式下请设置app_auth_token
        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        return Result.success(response.getBody());
    }

    private static com.alipay.api.AlipayConfig getAlipayConfig() {
        String privateKey  = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVrC8Yq/ouNGGDVVQain1wTDchZzoy0k2z3SKTXMoOOnrKi1qTG9P6AKMCYXywXgNjTIvtIgNOfjt14LgjbUYiE5j1nfGYSrOQk0ulEP3rHWtufHCUoy1lelARw7R3vzGpVnOVcapfuKXAmg9RkdWaDtJ9VCqkKpCQJMShp/RDtU/3qn1X70ARgH1/0Hd1WLqNR2hlU4A7nbUr3PD40Oa7zNOQcjHe8zg/THzGbTpsrtcHl6pxSZMWzHuwX2xuT26oiSjMen0SSGYI3Qt06Cmv/pRcVEJ+Wzo/8wzNQjQfZAgNOb8GEJF6mQJ1eA0VHSmQ6pTlf4M4zHFXCmYhpK99AgMBAAECggEAHk94Wi65w5GoDcjB934EWTGWGJWnG5OrWLl3vxATez4sj8gY16HTE9X9LQZBEhZ36SwfSi6RLNwzfnO71rDSeyQs3dDWlFjMdILf1tXSFkMm9oEM640ALITQokhXy8hUbk6rqaATOL9UEeUhJQu7ji9iQS9CU2yrSgWoLHSu6ucE09D6eS9i6RHVYow+M1XA/oLecSq1U9e7UoX9VhHNSk9cS+trVrPaLepUuUhuAd45rQlGjbNmur67q2vJ1po9lDGzG4lt/pErUireiQiJ2THq3euEpAyUOiCY7ZX4AMGwkHXvf1avsSLrAgUk+fvZwvPw+ZUC+JlDAB9Ze2KKoQKBgQDjkD8zo6Z1uVRQOIfdya8CPALXfVI9HgICSH7gOybIYbbfAdnoX0vrNMR7P01GCX6UnSINNHSg6KnL4S1SFpwP+ys6aYqyPKRaHzSm+WxXq82PrZ/qIYgfb9bADWLONSd6P88x4BUGf9fhmFDdAXFqtNgSvV77R4EGXPo1/6kEMwKBgQCoYDV3fhXxh8NGp7IiPi5XyqKZeEMud1Gla3GC2hL8BAukty8P5nCBAmuP4tb60vtDR1/Ji1Z7Frq2ZzOLZ9MTtnwwfABtoaePKoC4qQXfQOq6nep1YZWvIS84kejbhg6LSr6mqX1WDf4ntmsafYLvfYZRwFC9/0FRfS5m3ChNjwKBgQCLxut7TZcOkpK+X6rcyWgtCC16rWiZT9hODLeaBdtRk+ue2WU+vWJFi/jv9zcAwQ42PLpQwBqvR5ocOCj9VhPwIQgxbeiaiHMvRCG0OlqZE1dFXJFwX75cCPcwqkg5fx7LkWw0J37iqh4xXLy2s481yvU5tYYk9IvFG9E439uyTwKBgAk0kVfCFfMJiByRyIRkv+Xe1zrqv0mIDnerwx3zTDxpADX2KeWFpLr45eaM8VHvPPt1qH/9VTIPkQ+t3ryNZZXbUTjZXrCfuOhz22u9GewVwX+IaKwYNLXJa6p9F60AzETDRl8YPxj4VN/Y8kwVdpYfV7uadw+cJ/cLkADhonZzAoGBAKXJH/FZO85HERYRhU56ylUqe7XcFTWRoJVfW9wkXPRkoDEwLDEk2LT6cEkfF2n4/JCi7WVb9LK2pEIbM9/oaYhLokrv+sfMm687lFJ4cYxI3wdWs5aT2YYz76K8lpFV8MDAVe0fMsfYg88eO74uwLe9I8jkCvjF8ZQNy615iR5O";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqCboZOVYLomFsWUN5dZqqhaW1ESeFXYOOfVFGz8crsvZqorLH0a91RMYeOSHq3DnUg06v3yMsnwEwLLNxRwW5cMMFYI/Kuv1zse+BQ7NKxsnIgqCcsjQ75boEWuZy6ByihRdMU7lczoAdIYPE8dLsTu/bdtTkQTnTjh4zZkGTWx8pzjhQMK48Ku/rg557gOi2NeP/9EjsGQdngQYErISLNM6ynAUKVjDTrCQAigJtEZ1G01ikfKjj1gRQSCxPOEP0ZMBcULSNylkMeNPjGYbjMrUv2JPbhVxxNk2heaAgChkQU+putxodorn4B1q1e42jZZQVE1GENzmpHdM2ABnswIDAQAB";   com.alipay.api.AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("9021000137652356");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }


}

