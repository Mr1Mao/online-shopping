package com.mapper;

import com.entity.Cart;
import com.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    //获取购物车信息接口
    public List<Cart> getCartInfo(Integer u_id);
    //删除购物车物品
    public int  deleteItem(Integer cart_id);
    //批量删除购物车物品
    public int batchDelete(List<Integer> cart_id);
    //添加订单表
    public int addOrder(@Param("orders") List<Orders> orders);
    //获取订单信息
    public List<Orders> getOrdersInfo(Integer id,char status);
    //更新订单信息
    public int updateOrder(@Param(value = "list")List<Map> ord_id);
    //添加单条订单信息
    public int insertOrder(Orders orders);

}
