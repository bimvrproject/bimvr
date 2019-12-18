package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.MemberDetails;

/**
 * 订单操作 service
 */
public interface IOrdersService {
    /**
     * 新增订单
     * @param orders
     * @return
     */
    Boolean saveOrder(MemberDetails orders);

    /**
     * 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
     * @param id
     * @return
     */
    Boolean updateOrderStatus(Integer id);

    /**
     * 获取订单
     * @param orderId
     * @return
     */
    MemberDetails getOrderById(Integer orderId);

}
