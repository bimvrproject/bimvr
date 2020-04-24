package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ManagementFeedbackUser;

public interface ManagementFeedbackUserMapper {
    int insert(ManagementFeedbackUser record);

    int insertSelective(ManagementFeedbackUser record);

    int updateByphone(ManagementFeedbackUser record);

    ManagementFeedbackUser findByuserphone(String phone);
}