package com.controller;

import com.entity.Address;
import com.entity.Cart;
import com.entity.Orders;
import com.entity.User;
import com.google.gson.Gson;
import com.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
public class CartController {

    @Autowired
    CartMapper cartMapper;

    //获取购物车信息
    @GetMapping("/getCartInfo")
    public List<Map>  getCartInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Cart> cartInfo = cartMapper.getCartInfo(user.getU_id());
        Map map = null;
        Map operations = null;
        List<Map> res = new ArrayList<>();
        for(Cart item : cartInfo){
            map =  new HashMap();
            operations = new HashMap();
            operations.put("remove","/deleteItem?cart_id="+item.getCart_id());
            map.put("cart_id",item.getCart_id());
            map.put("pro_id",item.getPro_id());
            map.put("product_name",item.getProduct().getName());
//            System.out.println(item);
//            System.out.println(item.getCart_id());
            map.put("unit_price",item.getProduct().getPrice());
            map.put("nums",item.getNum());
            map.put("selected",item.isCheck());
            map.put("img",item.getProduct().getTitleImg());
            map.put("operations",operations);
            if(item.isCheck()){
                map.put("isCheck",1);
            }else{
                map.put("isCheck",0);
            }
            map.put("product",item.getProduct());
            res.add(map);
        }
       return res;
    }
    //删除购物车物品
    @GetMapping("/deleteItem")
    public void  deleteItem(Integer cart_id){
        cartMapper.deleteItem(cart_id);
    }
    //批量删除购物车物品
    @PostMapping("/deleteItems")
    public void  deleteItem(@RequestBody Map body,HttpSession session){
        List<Integer> cartList= (List<Integer>) body.get("cart_id");
        cartMapper.batchDelete(cartList);
    }
    //添加订单接口
    @PostMapping("/addOrder")
    public String  addOrder(@RequestBody Map body,HttpSession session){
        if(body == null){
            return "0";
        }
        Orders orders =null;
        List<String> ordersList= (List<String>) body.get("ordersList");
        List<Orders> addList = new ArrayList<>();
        List<Integer> cartList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = null;
        for (Object order : ordersList){
            if(order != null){
                orders = new Orders();
                map = (Map<String, Object>) order;
                cartList.add((Integer) map.get("cart_id"));
                orders.setOrder_id((Integer) (map.get("cart_id")));
                orders.setNum((Integer) map.get("num"));
                orders.setPro_id((Integer) map.get("pro_id"));
                orders.setAdd_id(1);                //临时
                orders.setId(user.getU_id());
                orders.setStatus('0');
                addList.add(orders);
//                System.out.println(orders);
            }
        }
        cartMapper.addOrder(addList);
        cartMapper.batchDelete(cartList);
        session.setAttribute("cartList",cartList);
        return "1";
    }

    //获取订单信息
    @GetMapping("/getOrderInfo")
    public List<Map> getOrderInfo(HttpSession session){
          List<Integer> cartList = (List<Integer>) session.getAttribute("cartList");
          User user = (User) session.getAttribute("user");
          List<Orders> oredrsList= cartMapper.getOrdersInfo(user.getU_id(),'0');
          List<Map> items = new ArrayList<>();
          Map map = null;
        if(cartList==null || oredrsList==null){             //可以改进
            return new ArrayList<>(2);
        }
          Map additionalMap = null;
          for (Integer item:cartList){
             for(Orders order:oredrsList){
                 if(item.equals(order.getOrder_id())){
                     map = new HashMap();
                     additionalMap = new HashMap();
                     additionalMap.put("freight_type",order.getProduct().getFreight_type());
                     additionalMap.put("freight_price",order.getProduct().getFreight());
                     additionalMap.put("Transport_insurance",order.getProduct().getFreight_insurance());
                     additionalMap.put("isSelect",false);
                     map.put("order_id",order.getOrder_id());
                     map.put("items_name",order.getProduct().getName());
                     map.put("items_img",order.getProduct().getTitleImg());
                     map.put("unit_price",order.getProduct().getPrice());
                     map.put("nums",order.getNum());
                     map.put("additional_price",additionalMap);
                     map.put("discounts",order.getProduct().getDiscount());
                     map.put("total_prices",order.getNum() * order.getProduct().getPrice());
                     items.add(map);
                 }
             }
          }
//        System.out.println("items==>"+items);
        return items;
    }


    @GetMapping("/getAllOrderInfo")
    public List<Map> getALLOrderInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Orders> oredrsList= cartMapper.getOrdersInfo(user.getU_id(),'0');
        List<Map> items = new ArrayList<>();
        Map map = null;
//        System.out.print("进入了所有");
        if(oredrsList==null){             //可以改进
            return new ArrayList<>();
        }
        Map additionalMap = null;
            for(Orders order:oredrsList){
                    map = new HashMap();
                    additionalMap = new HashMap();
                    additionalMap.put("freight_type",order.getProduct().getFreight_type());
                    additionalMap.put("freight_price",order.getProduct().getFreight());
                    additionalMap.put("Transport_insurance",order.getProduct().getFreight_insurance());
                    additionalMap.put("isSelect",false);
                    map.put("order_id",order.getOrder_id());
                    map.put("items_name",order.getProduct().getName());
                    map.put("items_img",order.getProduct().getTitleImg());
                    map.put("unit_price",order.getProduct().getPrice());
                    map.put("nums",order.getNum());
                    map.put("additional_price",additionalMap);
                    map.put("discounts",order.getProduct().getDiscount());
                    map.put("total_prices",order.getNum() * order.getProduct().getPrice());
                    items.add(map);
                }
//        System.out.println("items==>"+items);
        return items;
    }
//    @GetMapping("/getOneOrderInfo")
//    public Map getOneOrderInfo(){
//        Map map = new HashMap();
//     return
//    }

        //立即购买接口//暂时未做
    @PostMapping("/insertOrder")
    public void insertOrder(@RequestBody Map body){
        Orders orders = new Orders();

    }
}
