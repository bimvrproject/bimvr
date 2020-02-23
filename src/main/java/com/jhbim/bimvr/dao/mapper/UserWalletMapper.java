package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.UserWallet;

public interface UserWalletMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserWallet record);

    int insertSelective(UserWallet record);

    UserWallet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserWallet record);

    int updateByPrimaryKey(UserWallet record);
    //加钱
    int updateplusmoney(UserWallet record);
    //减钱
    int updatereducemoney(UserWallet record);

    UserWallet selectByuserphone(String phone);
}