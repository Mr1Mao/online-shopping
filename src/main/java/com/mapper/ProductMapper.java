package com.mapper;

import com.entity.Cart;
import com.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //放回商品列表接口
    public List<Product> getProList(int currIndex, int pageSize);
    //放回商品信息接口
    public Product getProInfo(int id);
    //返回商品数量接口
    public Integer getCount();
    //添加购物车物品信息的接口
    public int insertCart(Cart cart);
    //查看是是否重复添加购物车信息接口
    public Integer isItemUnique(Integer pro_id,Integer id);
    //更新购物车信息
    public int upDateCart(Integer num ,Boolean isCheck,Integer id,Integer pro_id);
}
