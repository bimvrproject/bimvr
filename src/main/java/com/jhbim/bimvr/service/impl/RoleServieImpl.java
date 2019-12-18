package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.Role;
import com.jhbim.bimvr.dao.mapper.RoleMapper;
import com.jhbim.bimvr.pub.CheckMsg;
import com.jhbim.bimvr.service.IRoleServie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServieImpl implements IRoleServie {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> getProducts() {
        CheckMsg checkMsg = new CheckMsg();
        List<Role> list = roleMapper.list();
        return list;
    }
}
