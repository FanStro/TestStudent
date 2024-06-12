package com.itheima.config;

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000137652356";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVrC8Yq/ouNGGDVVQain1wTDchZzoy0k2z3SKTXMoOOnrKi1qTG9P6AKMCYXywXgNjTIvtIgNOfjt14LgjbUYiE5j1nfGYSrOQk0ulEP3rHWtufHCUoy1lelARw7R3vzGpVnOVcapfuKXAmg9RkdWaDtJ9VCqkKpCQJMShp/RDtU/3qn1X70ARgH1/0Hd1WLqNR2hlU4A7nbUr3PD40Oa7zNOQcjHe8zg/THzGbTpsrtcHl6pxSZMWzHuwX2xuT26oiSjMen0SSGYI3Qt06Cmv/pRcVEJ+Wzo/8wzNQjQfZAgNOb8GEJF6mQJ1eA0VHSmQ6pTlf4M4zHFXCmYhpK99AgMBAAECggEAHk94Wi65w5GoDcjB934EWTGWGJWnG5OrWLl3vxATez4sj8gY16HTE9X9LQZBEhZ36SwfSi6RLNwzfnO71rDSeyQs3dDWlFjMdILf1tXSFkMm9oEM640ALITQokhXy8hUbk6rqaATOL9UEeUhJQu7ji9iQS9CU2yrSgWoLHSu6ucE09D6eS9i6RHVYow+M1XA/oLecSq1U9e7UoX9VhHNSk9cS+trVrPaLepUuUhuAd45rQlGjbNmur67q2vJ1po9lDGzG4lt/pErUireiQiJ2THq3euEpAyUOiCY7ZX4AMGwkHXvf1avsSLrAgUk+fvZwvPw+ZUC+JlDAB9Ze2KKoQKBgQDjkD8zo6Z1uVRQOIfdya8CPALXfVI9HgICSH7gOybIYbbfAdnoX0vrNMR7P01GCX6UnSINNHSg6KnL4S1SFpwP+ys6aYqyPKRaHzSm+WxXq82PrZ/qIYgfb9bADWLONSd6P88x4BUGf9fhmFDdAXFqtNgSvV77R4EGXPo1/6kEMwKBgQCoYDV3fhXxh8NGp7IiPi5XyqKZeEMud1Gla3GC2hL8BAukty8P5nCBAmuP4tb60vtDR1/Ji1Z7Frq2ZzOLZ9MTtnwwfABtoaePKoC4qQXfQOq6nep1YZWvIS84kejbhg6LSr6mqX1WDf4ntmsafYLvfYZRwFC9/0FRfS5m3ChNjwKBgQCLxut7TZcOkpK+X6rcyWgtCC16rWiZT9hODLeaBdtRk+ue2WU+vWJFi/jv9zcAwQ42PLpQwBqvR5ocOCj9VhPwIQgxbeiaiHMvRCG0OlqZE1dFXJFwX75cCPcwqkg5fx7LkWw0J37iqh4xXLy2s481yvU5tYYk9IvFG9E439uyTwKBgAk0kVfCFfMJiByRyIRkv+Xe1zrqv0mIDnerwx3zTDxpADX2KeWFpLr45eaM8VHvPPt1qH/9VTIPkQ+t3ryNZZXbUTjZXrCfuOhz22u9GewVwX+IaKwYNLXJa6p9F60AzETDRl8YPxj4VN/Y8kwVdpYfV7uadw+cJ/cLkADhonZzAoGBAKXJH/FZO85HERYRhU56ylUqe7XcFTWRoJVfW9wkXPRkoDEwLDEk2LT6cEkfF2n4/JCi7WVb9LK2pEIbM9/oaYhLokrv+sfMm687lFJ4cYxI3wdWs5aT2YYz76K8lpFV8MDAVe0fMsfYg88eO74uwLe9I8jkCvjF8ZQNy615iR5O";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlawvGKv6LjRhg1VUGop9cEw3IWc6MtJNs90ik1zKDjp6yotakxvT+gCjAmF8sF4DY0yL7SIDTn47deC4I21GIhOY9Z3xmEqzkJNLpRD96x1rbnxwlKMtZXpQEcO0d78xqVZzlXGqX7ilwJoPUZHVmg7SfVQqpCqQkCTEoaf0Q7VP96p9V+9AEYB9f9B3dVi6jUdoZVOAO521K9zw+NDmu8zTkHIx3vM4P0x8xm06bK7XB5eqcUmTFsx7sF9sbk9uqIkozHp9EkhmCN0LdOgpr/6UXFRCfls6P/MMzUI0H2QIDTm/BhCRepkCdXgNFR0pkOqU5X+DOMxxVwpmIaSvfQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8888/success";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8082/success";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";
}
