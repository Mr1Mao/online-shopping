package com.controller;


import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.mapper.ProductMapper;
import com.response.ProductList;
import com.response.ResProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class ProductInfoController {

    @Autowired
    ProductMapper productMapper;

    //放回商品列表
    @PostMapping("/productList")
    public Map productList(@RequestBody Map pam){
//        Integer a = (Integer) pam.get("currIndex");
//        System.out.println(pam.get("currIndex"));
        Map resMap = new HashMap();
//        List<ProductList>
//        System.out.println(pam.get("pageSize"));
        List<ProductList> res = new ArrayList();
        List<Product> proList = productMapper.getProList((Integer) pam.get("currIndex"),(Integer) pam.get("pageSize"));
        ProductList productList = null;
        for( int i = 0 ; i < proList.size() ; i++) {
            productList = new ProductList();
            productList.setId(proList.get(i).getId());
            productList.setName(proList.get(i).getName());
            productList.setImg(proList.get(i).getTitleImg());
            productList.setPrice(proList.get(i).getPrice());
            res.add(productList);
        }
        Integer pageNum = productMapper.getCount();
        resMap.put("productList",res);
        resMap.put("pageNum",pageNum);
       return resMap;
    }
    //返回商品信息（一）
    @GetMapping("/productInfo")
    public Map getProductInfo(Integer id){
//        System.out.println(id);
        Product product= productMapper.getProInfo(id);
        Map res = new HashMap();
        res.put("id",product.getId());
        res.put("price",product.getPrice());
        res.put("stock",product.getStock());
        res.put("name",product.getName());
        res.put("img",product.getImg().split("\\|"));
        res.put("unit",product.getUnit());
        res.put("modeShop",product.getFreight_type());
        return res;
    }
    //返回商品信息（二）
    @GetMapping("/details")
    public Map getProduct(Integer id){
//        System.out.println(id);
        Product product= productMapper.getProInfo(id);
//        System.out.println(product.getDetailsImg());
        Map res = new HashMap();
        res.put("id",product.getId());
        res.put("name",product.getName());
        res.put("img",product.getDetailsImg().split("\\|"));
        res.put("brand",product.getBrand());
        res.put("location",product.getLocation());
        res.put("color",product.getColor());
        res.put("SKU",product.getSKU());
        res.put("pro_type",product.getType());
//        res.put("other",product.getOther());
        res.put("proNum",product.getProNum());
        res.put("unit",product.getUnit());
        return res;
    }

    //加入购物车
    @GetMapping("/addCart")
    public String addCart(Integer id,HttpSession session,Integer num){
        User user = (User) session.getAttribute("user");
        Product product= productMapper.getProInfo(id);
        Integer isItemUnique = productMapper.isItemUnique(id,user.getU_id());
        System.out.println(isItemUnique);
        if (isItemUnique == 0){
            Cart cart = new Cart();
            cart.setCart_id((int) (user.getU_id()+new Date().getTime()));
            cart.setNum(num);
            cart.setCheck(false);
            cart.setId(user.getU_id());
            cart.setPro_id(product.getId());
            cart.setProduct(product);
            cart.setUser(user);
//            System.out.println(cart);
            productMapper.insertCart(cart);
        }else{
            productMapper.upDateCart(num,null,user.getU_id(),id);
        }
        return "1";

    }

}

