package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupRecord record);

    int insertSelective(GroupRecord record);

    GroupRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupRecord record);

    int updateByPrimaryKey(GroupRecord record);

    //查看本人是否在所对应的群组里
    GroupRecord fingByGroupIdandIslike(@Param("groupid") String groupid,@Param("userphone") String userphone);
    //我加入的群
     List<GroupRecord> findByusergroup(@Param("userphone") String userphone,@Param("islike") String islike);
     //修改是否同意进群
     int updateislike(@Param("groupid") String [] groupid,@Param("userphone") String userphone,@Param("islike") Integer islike);
    //退出群组
    int deletegroupid(@Param("groupid") String groupid,@Param("userphone") String userphone);
    //解散群组
    int deletegroup(String groupid);
    //转让群组
    int updategrouplevel(@Param("groupid") String groupid,@Param("userphone") String userphone,@Param("level") Integer level);
    //获取该群的所有人员
    List<GroupRecord> getgropuserAll(@Param("groupid") String groupid,@Param("islike") Integer islike);
    //查询群管理员的个数
    int getgroup(@Param("groupid") String groupid,@Param("level") Integer level);
    //根据群号查询
    List<GroupRecord> findbygroupnoid(@Param("groupid") String groupid,@Param("islike") Integer islike);
    //同意或忽略状态(进群群主或管理员)
    int updategroupislike(@Param("userphone") String[] userphone,@Param("groupid")String groupid,@Param("islike") Integer islike);
    //阻止重复增加进群记录
    GroupRecord getnotexistgroupuser(@Param("groupid") String groupid,@Param("userphone") String userphone,@Param("islike") Integer islike);

}