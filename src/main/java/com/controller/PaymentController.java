package com.controller;

import com.entity.Address;
import com.entity.Cart;
import com.entity.Orders;
import com.entity.User;
import com.mapper.AddressMapper;
import com.mapper.CartMapper;
import com.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PaymentController {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    AddressMapper addressMapper;

    //用户购买操作
    @PostMapping("/payment")
    public String payment(HttpSession session, @RequestBody Map map){
        User user = (User) session.getAttribute("user");
        List<Map> productList= (List<Map>) map.get("product");
        List<Orders> oredrsList= cartMapper.getOrdersInfo(user.getU_id(),'0');
        int address = (int) map.get("address");
        double totalPrice = 0.00;
        BigDecimal b  =  null;
        Map map1 = null;
        List<Map> updareList = new ArrayList<>();
        for (Map item:productList){
            for(Orders order:oredrsList){
                //如果 查询出来的订单ID与前台发的ID相等则进行操作
                if(item.get("order_id").equals(order.getOrder_id())){
//                    System.out.println(order.getProduct().getName() + "===>"+ item.get("isCheck"));
                    map1 = new HashMap();
                    map1.put("status",1);
                    map1.put("leaving_message",item.get("leaving_message"));
                    map1.put("address",address);
                    map1.put("order_id",item.get("order_id"));
                    //判断是否勾选运费险
                    if ((boolean)item.get("isCheck")){
                        b= new BigDecimal((double)order.getProduct().getPrice()*order.getNum()+(double) order.getProduct().getFreight()+(double) order.getProduct().getFreight_insurance());
                        totalPrice += b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    }else{
                        b= new BigDecimal((double)order.getProduct().getPrice()*order.getNum()+ (double)order.getProduct().getFreight());
                        totalPrice += b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    updareList.add(map1);
                }
            }
        }

        System.out.println(totalPrice);
        //判断账户余额是否充足
        if(user.getProperty()>=totalPrice){
            //余额充足可以购买
            int  updatepProperty = loginMapper.property(totalPrice,user.getU_id());
            int  res =  cartMapper.updateOrder(updareList);
            if(updatepProperty>0 && res>0){ //如果交易成功则将交易的信息放入session
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Address address1 = addressMapper.getAddress(user.getU_id(),address);
                Map orderInfo = new HashMap();
                orderInfo.put("totalPrice",totalPrice);
                orderInfo.put("ordersId",new Date().getTime()+user.getU_id());
                orderInfo.put("transactionTime",df.format(new Date()));
                orderInfo.put("address",address1);
                session.setAttribute("orderInfo",orderInfo);
                return "购买成功";
            }else{
                return "购买失败";
            }
        }else{
            //余额不足购买失败
            return "余额不足";
        }

    }

    @GetMapping("/success")
    public Map payment(HttpSession session){
       Map orderInfo= (Map) session.getAttribute("orderInfo");

        return orderInfo;
    }
}
