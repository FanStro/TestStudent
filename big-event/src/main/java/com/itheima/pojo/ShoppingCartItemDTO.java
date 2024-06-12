package com.itheima.pojo;

import lombok.Data;

@Data
public class ShoppingCartItemDTO {
    private String id;
    private String userid;
    private String commodityid;
    private String addressid;
    private int commoditynum;
    private String name;
    private String phone;
    private String address;
    private String title;
    private float price;
    private String urlimage;
    private String state;
}
