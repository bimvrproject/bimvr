package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupClusterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupCluster record);

    int insertSelective(GroupCluster record);

    GroupCluster selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupCluster record);

    int updateByPrimaryKey(GroupCluster record);

    //根据所对应的群号查群和是否推荐并查
    GroupCluster findByGroupNo(String groupno);
    //根据群号和群昵称查询
    List<GroupCluster> findBygroupnoandgroupname(@Param("groupno") String groupno, @Param("groupname") String groupname);
    //根据群组查询
    List<GroupCluster> groupcluster(List<String> groupid);
    //根据所对应的群号查群
    GroupCluster findbygroupid(String groupno);
    //修改群昵称
    int updategroupname(@Param("groupno") String groupno, @Param("groupname") String groupname);
    //修改群简介
    int updategroupbrief(@Param("groupno") String groupno, @Param("brief") String brief);
    //修改备注
    int updategroupremark(@Param("groupno") String groupno, @Param("remark") String remark);
    //修改群组是否推荐
    int updategroupisrecommend(@Param("groupno") String groupno, @Param("isrecommend") Integer isrecommend);
    //修改查找群组时是否展示即时聊天
    int updategrouptype(@Param("groupno") String groupno, @Param("type") Integer type);
    //修改群头像
    int updategrouppicture(GroupCluster groupCluster);
}