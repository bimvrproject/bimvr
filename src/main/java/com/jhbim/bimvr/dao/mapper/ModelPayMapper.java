package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ModelPay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelPayMapper {
    int deleteByPrimaryKey(String id);

    int insert(ModelPay record);

    int insertSelective(ModelPay record);

    ModelPay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ModelPay record);

    int updateByPrimaryKey(ModelPay record);

    List<ModelPay> payList(@Param("list") List<String> list,@Param("userId") String userId);
}