package com.itheima.pojo;

import lombok.Data;

@Data
public class ShoppingCart {
    private String id;
    private String userid;
    private String commodityid;
    private String addressid;
    private int commoditynum;
    private String state;
}
