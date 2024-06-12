package com.itheima.mapper;

import com.itheima.pojo.Commodity;
import com.itheima.pojo.ShoppingCartItemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    @Insert("insert into shoppingcart(id,userid,commodityid,addressid,commoditynum,state) values(#{id},#{userId},#{commodityid},#{addressid},#{commoditynum},#{state})")
    void addShoppingCart(String id, Integer userId, String commodityid, String addressid, int commoditynum,String state);

    @Select("SELECT sc.*, cd.* " +
            "FROM shoppingcart sc " +
            "LEFT JOIN commodity cd ON sc.commodityid = cd.id " +
            "WHERE sc.userid = #{userId} and sc.state='0'")
    List<ShoppingCartItemDTO> getShoppingCartList(Integer userId);

    @Select("SELECT sc.*, cd.* " +
            "FROM shoppingcart sc " +
            "LEFT JOIN commodity cd ON sc.commodityid = cd.id " +
            "WHERE sc.id=#{id}")
    ShoppingCartItemDTO getShoppingCartById(String id);
    @Delete("delete from shoppingcart where id = #{id}")
    void deleteShoppingCart(String id);

    @Update("update shoppingcart set commoditynum = #{commoditynum} ,addressid = #{addressid} where id=#{id}")
    void updateShoppingCart(String id, int commoditynum, String addressid);
}
