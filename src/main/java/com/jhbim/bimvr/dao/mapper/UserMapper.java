package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String phone);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updatePwd(User user);
    //修改用户登录状态
    int updatestate(User user);
    // 修改昵称
    int updatausername(@Param("userName") String userName, @Param("phone") String phone);
    // 修改所属公司
    int updatacompanyname(@Param("companyname") String companyname, @Param("phone") String phone);
    // 修改职位
    int updataposition(@Param("posotion") String position,@Param("phone") String phone);
    //修改备注
    int updataremarks(@Param("remarks") String remarks,@Param("phone") String phone);
    //根据用户手机号或昵称查询
    List<User> findByuserphoneorusername(@Param("phone") String phone,@Param("userName") String userName);

    //用户列表
    List<User> userList(List<String> list);

    //用户排序
    List<User> userSort(List<String> list);
}