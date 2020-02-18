package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.UserMessageVo;
import com.jhbim.bimvr.dao.entity.vo.UserRankVo;
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
    //修改点赞数
    int updateAccount(@Param("account")Integer account,@Param("phone") String phone);
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
    //修改头像
    int updatepicture(User user);
    //修改密码
    int updatepwd(User user);
    //根据用户手机号或昵称查询
    List<User> findByuserphoneorusername(@Param("phone") String phone,@Param("userName") String userName);

    //用户列表
    List<User> userList(List<String> list);

    //随机查询在线人员
    List<User> randomgetAll();
    //排除共同好友在随机展示
    List<User> randomgetAllisnotself(String phone);
    //根据手机号查询
    List<User> getlistAll(String phone);
    //用户排序
    List<User> userSort(List<String> list);
    //根据用户id查询
    List<User> findByuserid(Integer userid);
    //用户列表
    UserMessageVo userListMessage(String friendPhone);
    UserMessageVo userLists(String friendPhone);

    //用户排行榜
    List<UserRankVo> userRank();
    List<UserRankVo> userRanks();



}