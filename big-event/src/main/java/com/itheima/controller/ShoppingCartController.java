package com.itheima.controller;

import com.itheima.pojo.Commodity;
import com.itheima.pojo.Result;
import com.itheima.pojo.ShoppingCart;
import com.itheima.pojo.ShoppingCartItemDTO;
import com.itheima.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/addShoppingCart")
    public Result addShoppingCart(@RequestParam int commoditynum,@RequestParam String commodityid,@RequestParam String addressid){
        try{
            shoppingCartService.addShoppingCart(commoditynum,commodityid,addressid);
            return Result.success("");
        }catch (Exception e){
            System.out.println(e);
            return Result.error("加入购物车失败");
        }
    }
    @GetMapping("/getShoppingCartList")
    public Result getShoppingCartList(){
        List<ShoppingCartItemDTO> list = shoppingCartService.getShoppingCartList();
        if(list.size()!=0){
//            System.out.println(list);
            return Result.success(list);
        }else{
            return Result.error("商品列表为空");
        }
    }
    @GetMapping("/getShoppingCartById")
    public Result getShoppingCartById(@RequestParam String id){
        ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartService.getShoppingCartById(id);
//        System.out.println("这个数据是"+shoppingCartItemDTO);
        return Result.success(shoppingCartItemDTO);
    }
    @GetMapping("/deleteShoppingCart")
    public Result deleteShoppingCart(@RequestParam String id){
        String s = shoppingCartService.deleteShoppingCart(id);

        return Result.success(s);
    }
    @PatchMapping("/updateShoppingCart")
    public Result updateShoppingCart(@RequestBody ShoppingCartItemDTO shoppingCartItemDTO){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartItemDTO.getId());
        shoppingCart.setAddressid(shoppingCartItemDTO.getAddressid());
        shoppingCart.setCommodityid(shoppingCartItemDTO.getCommodityid());
        shoppingCart.setUserid(shoppingCartItemDTO.getUserid());
        shoppingCart.setCommoditynum(shoppingCartItemDTO.getCommoditynum());
//        System.out.println(shoppingCart);
        shoppingCartService.updateShoppingCart(shoppingCart);
        return Result.success("更新成功");
    }
}
