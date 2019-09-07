package com.controller;

import com.entity.Address;
import com.entity.User;
import com.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    @Autowired
    AddressMapper addressMapper;

    //获取地址信息
    @GetMapping("/getAdderss")
    public List<Map> getAdderss(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Address> addressList= addressMapper.getAddressInfo(user.getU_id());
        List<Map> resList = new ArrayList<>();
        Map map = null;
        for (Address address:addressList){
            map = new HashMap();
            map.put("add_id",address.getAdd_id());
            map.put("province",address.getProvince());
            map.put("city",address.getCity());
            map.put("area",address.getRegion());
            map.put("stree",address.getStreet());
            map.put("detailed",address.getDetails());
            map.put("name",address.getName());
            map.put("tell",address.getAdd_phone());
            resList.add(map);

        }
        return resList;
    }
}
