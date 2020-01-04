package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import org.apache.ibatis.annotations.Param;

public interface GroupRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupRecord record);

    int insertSelective(GroupRecord record);

    GroupRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupRecord record);

    int updateByPrimaryKey(GroupRecord record);

    //查看本人是否在所对应的群组里
    GroupRecord fingByGroupIdandIslike(@Param("groupid") String groupid,@Param("userphone") String userphone);
}