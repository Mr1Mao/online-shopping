package com.mapper;

import com.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    //获取地址信息
    public List<Address> getAddressInfo(Integer u_id);
    //获取一条地址
    public Address getAddress(Integer u_id,Integer add_id);
}
