package com.jhbim.bimvr.service;

import com.jhbim.bimvr.dao.entity.pojo.Role;

import java.util.List;

public interface IRoleServie {
    /**
     * 根据产品ID查询产品详情
     * @param roleId
     * @return
     */
    Role selectByPrimaryKey(Integer roleId);
    /**
     * 获取所有会员列表
     * @return
     */
    List<Role> getProducts();
}
