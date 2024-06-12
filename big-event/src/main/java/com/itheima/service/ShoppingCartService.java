package com.itheima.service;

import com.itheima.pojo.Commodity;
import com.itheima.pojo.ShoppingCart;
import com.itheima.pojo.ShoppingCartItemDTO;

import java.util.List;

public interface ShoppingCartService {
    void addShoppingCart(int commoditynum,String commodityid,String addressid);

    List<ShoppingCartItemDTO> getShoppingCartList();

    String deleteShoppingCart(String id);

    void updateShoppingCart(ShoppingCart shoppingCart);

    ShoppingCartItemDTO getShoppingCartById(String id);
}
