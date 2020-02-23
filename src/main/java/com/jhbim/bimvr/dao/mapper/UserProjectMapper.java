package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.UserProject;

import java.util.List;

public interface UserProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserProject record);

    int insertSelective(UserProject record);

    UserProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserProject record);

    int updateByPrimaryKey(UserProject record);
    /**
     * 根据用户id查询项目id
     * @param id
     * @return
     */
    List<UserProject> selectuserid(Integer id);

    /**
     * 查询该用户创建项目个数
     * @param userid
     * @return
     */
    int selectUserProjectnum(Integer userid);

    //根据项目id查询
    UserProject findbyprojectid(String projectid);
}