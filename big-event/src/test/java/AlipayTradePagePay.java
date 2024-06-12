
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.ExtUserInfo;
import com.alipay.api.domain.InvoiceKeyInfo;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.domain.InvoiceInfo;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.domain.SubMerchant;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlipayTradePagePay {

    public static void main(String[] args) throws AlipayApiException {
        // 初始化SDK
        AlipayClient alipayClient = new DefaultAlipayClient(getAlipayConfig());

        // 构造请求参数以调用接口
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        // 设置商户门店编号
        model.setStoreId("NJ_001");

        // 设置订单绝对超时时间
        model.setTimeExpire("2016-12-31 10:05:01");

        // 设置业务扩展参数
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088511833207846");
        extendParams.setSpecifiedSellerName("樊福蕰的跨境小铺");
        extendParams.setCardType("S0JP0000");
        model.setExtendParams(extendParams);


        // 设置订单标题
        model.setSubject("Iphone6 16G");

        // 设置请求来源地址
        model.setRequestFromUrl("https://");

        // 设置产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        // 设置PC扫码支付的方式
        model.setQrPayMode("1");

        // 设置商户自定义二维码宽度
        model.setQrcodeWidth(100L);

        // 设置请求后页面的集成方式
        model.setIntegrationType("PCWEB");

        // 设置订单包含的商品列表信息
        List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
        GoodsDetail goodsDetail0 = new GoodsDetail();
        goodsDetail0.setGoodsName("ipad");
        goodsDetail0.setAlipayGoodsId("20010001");
        goodsDetail0.setQuantity(1L);
        goodsDetail0.setPrice("2000");
        goodsDetail0.setGoodsId("apple-01");
        goodsDetail0.setGoodsCategory("34543238");
        goodsDetail0.setCategoriesTree("124868003|126232002|126252004");
        goodsDetail0.setShowUrl("http://www.alipay.com/xxx.jpg");
        goodsDetail.add(goodsDetail0);
        model.setGoodsDetail(goodsDetail);

        // 设置商户的原始订单号
        model.setMerchantOrderNo("20161008001");

        // 设置二级商户信息
        SubMerchant subMerchant = new SubMerchant();
        subMerchant.setMerchantId("2088000603999128");
        subMerchant.setMerchantType("alipay");
        model.setSubMerchant(subMerchant);

        // 设置开票信息
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        InvoiceKeyInfo keyInfo = new InvoiceKeyInfo();
        keyInfo.setTaxNum("1464888883494");
        keyInfo.setIsSupportInvoice(true);
        keyInfo.setInvoiceMerchantName("ABC|003");
        invoiceInfo.setKeyInfo(keyInfo);
        invoiceInfo.setDetails("[{\"code\":\"100294400\",\"name\":\"服饰\",\"num\":\"2\",\"sumPrice\":\"200.00\",\"taxRate\":\"6%\"}]");
        model.setInvoiceInfo(invoiceInfo);

        // 设置商户订单号
        model.setOutTradeNo("20150320010101001");

        // 设置外部指定买家
        ExtUserInfo extUserInfo = new ExtUserInfo();
        extUserInfo.setCertType("IDENTITY_CARD");
        extUserInfo.setCertNo("362334768769238881");
        extUserInfo.setName("李明");
        extUserInfo.setMobile("16587658765");
        extUserInfo.setMinAge("18");
        extUserInfo.setNeedCheckInfo("F");
        model.setExtUserInfo(extUserInfo);

        // 设置订单总金额
        model.setTotalAmount("88.88");

        // 设置商户传入业务信息
        model.setBusinessParams("{\"mc_create_trade_ip\":\"127.0.0.1\"}");

        // 设置优惠参数
        model.setPromoParams("{\"storeIdType\":\"1\"}");

        request.setBizModel(model);
        // 第三方代调用模式下请设置app_auth_token
        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

        AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "POST");
        // 如果需要返回GET请求，请使用
        // AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }

    private static AlipayConfig getAlipayConfig() {
        String privateKey  = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVrC8Yq/ouNGGDVVQain1wTDchZzoy0k2z3SKTXMoOOnrKi1qTG9P6AKMCYXywXgNjTIvtIgNOfjt14LgjbUYiE5j1nfGYSrOQk0ulEP3rHWtufHCUoy1lelARw7R3vzGpVnOVcapfuKXAmg9RkdWaDtJ9VCqkKpCQJMShp/RDtU/3qn1X70ARgH1/0Hd1WLqNR2hlU4A7nbUr3PD40Oa7zNOQcjHe8zg/THzGbTpsrtcHl6pxSZMWzHuwX2xuT26oiSjMen0SSGYI3Qt06Cmv/pRcVEJ+Wzo/8wzNQjQfZAgNOb8GEJF6mQJ1eA0VHSmQ6pTlf4M4zHFXCmYhpK99AgMBAAECggEAHk94Wi65w5GoDcjB934EWTGWGJWnG5OrWLl3vxATez4sj8gY16HTE9X9LQZBEhZ36SwfSi6RLNwzfnO71rDSeyQs3dDWlFjMdILf1tXSFkMm9oEM640ALITQokhXy8hUbk6rqaATOL9UEeUhJQu7ji9iQS9CU2yrSgWoLHSu6ucE09D6eS9i6RHVYow+M1XA/oLecSq1U9e7UoX9VhHNSk9cS+trVrPaLepUuUhuAd45rQlGjbNmur67q2vJ1po9lDGzG4lt/pErUireiQiJ2THq3euEpAyUOiCY7ZX4AMGwkHXvf1avsSLrAgUk+fvZwvPw+ZUC+JlDAB9Ze2KKoQKBgQDjkD8zo6Z1uVRQOIfdya8CPALXfVI9HgICSH7gOybIYbbfAdnoX0vrNMR7P01GCX6UnSINNHSg6KnL4S1SFpwP+ys6aYqyPKRaHzSm+WxXq82PrZ/qIYgfb9bADWLONSd6P88x4BUGf9fhmFDdAXFqtNgSvV77R4EGXPo1/6kEMwKBgQCoYDV3fhXxh8NGp7IiPi5XyqKZeEMud1Gla3GC2hL8BAukty8P5nCBAmuP4tb60vtDR1/Ji1Z7Frq2ZzOLZ9MTtnwwfABtoaePKoC4qQXfQOq6nep1YZWvIS84kejbhg6LSr6mqX1WDf4ntmsafYLvfYZRwFC9/0FRfS5m3ChNjwKBgQCLxut7TZcOkpK+X6rcyWgtCC16rWiZT9hODLeaBdtRk+ue2WU+vWJFi/jv9zcAwQ42PLpQwBqvR5ocOCj9VhPwIQgxbeiaiHMvRCG0OlqZE1dFXJFwX75cCPcwqkg5fx7LkWw0J37iqh4xXLy2s481yvU5tYYk9IvFG9E439uyTwKBgAk0kVfCFfMJiByRyIRkv+Xe1zrqv0mIDnerwx3zTDxpADX2KeWFpLr45eaM8VHvPPt1qH/9VTIPkQ+t3ryNZZXbUTjZXrCfuOhz22u9GewVwX+IaKwYNLXJa6p9F60AzETDRl8YPxj4VN/Y8kwVdpYfV7uadw+cJ/cLkADhonZzAoGBAKXJH/FZO85HERYRhU56ylUqe7XcFTWRoJVfW9wkXPRkoDEwLDEk2LT6cEkfF2n4/JCi7WVb9LK2pEIbM9/oaYhLokrv+sfMm687lFJ4cYxI3wdWs5aT2YYz76K8lpFV8MDAVe0fMsfYg88eO74uwLe9I8jkCvjF8ZQNy615iR5O";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlawvGKv6LjRhg1VUGop9cEw3IWc6MtJNs90ik1zKDjp6yotakxvT+gCjAmF8sF4DY0yL7SIDTn47deC4I21GIhOY9Z3xmEqzkJNLpRD96x1rbnxwlKMtZXpQEcO0d78xqVZzlXGqX7ilwJoPUZHVmg7SfVQqpCqQkCTEoaf0Q7VP96p9V+9AEYB9f9B3dVi6jUdoZVOAO521K9zw+NDmu8zTkHIx3vM4P0x8xm06bK7XB5eqcUmTFsx7sF9sbk9uqIkozHp9EkhmCN0LdOgpr/6UXFRCfls6P/MMzUI0H2QIDTm/BhCRepkCdXgNFR0pkOqU5X+DOMxxVwpmIaSvfQIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        alipayConfig.setAppId("9021000137652356");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }
}