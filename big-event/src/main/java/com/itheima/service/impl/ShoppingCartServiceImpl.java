package com.itheima.service.impl;

import com.itheima.mapper.ShoppingCartMapper;
import com.itheima.pojo.Commodity;
import com.itheima.pojo.ShoppingCart;
import com.itheima.pojo.ShoppingCartItemDTO;
import com.itheima.service.ShoppingCartService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;
    @Override
    public void addShoppingCart(int commoditynum,String commodityid,String addressid) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        if(commoditynum==0){
            commoditynum=1;
        }
        shoppingCartMapper.addShoppingCart(id,userId,commodityid,addressid,commoditynum,"0");
    }

    @Override
    public List<ShoppingCartItemDTO> getShoppingCartList() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");

        return shoppingCartMapper.getShoppingCartList(userId);
    }

    @Override
    public String deleteShoppingCart(String id) {
        shoppingCartMapper.deleteShoppingCart(id);
        return null;
    }

    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartMapper.updateShoppingCart(shoppingCart.getId(),shoppingCart.getCommoditynum(),shoppingCart.getAddressid());
    }

    @Override
    public ShoppingCartItemDTO getShoppingCartById(String id) {
//        System.out.println(id);
        return shoppingCartMapper.getShoppingCartById(id);
    }
}
