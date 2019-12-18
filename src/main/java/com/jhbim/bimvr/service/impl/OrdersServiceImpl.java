package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.MemberDetails;
import com.jhbim.bimvr.dao.mapper.MemberDetailsMapper;
import com.jhbim.bimvr.service.IOrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrdersServiceImpl implements IOrdersService {

    @Resource
    MemberDetailsMapper memberDetailsMapper;
    @Override
    public Boolean saveOrder(MemberDetails orders) {
        boolean flag=false;
        try{
            memberDetailsMapper.insert(orders);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateOrderStatus(Integer id) {
        boolean flag=false;
        try{
            memberDetailsMapper.updateByPrimaryKeyByid(String.valueOf(id));
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public MemberDetails getOrderById(Integer orderId) {
        return memberDetailsMapper.selectByPrimaryKey(orderId);
    }
}
