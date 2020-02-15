package com.jhbim.bimvr.controller.pc.shopping;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ModelMapper;
import com.jhbim.bimvr.dao.mapper.ScShoppingMapper;
import com.jhbim.bimvr.dao.mapper.ScUserModelMapper;
import com.jhbim.bimvr.dao.mapper.ScUserModelNumMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/${version}/usermodel")
public class UserModelController {
    //允许模型上架商城的数量
    private static final Integer modelcount=6;
    @Resource
    ScShoppingMapper scShoppingMapper;
    @Resource
    ScUserModelMapper scUserModelMapper;
    @Resource
    ScUserModelNumMapper scUserModelNumMapper;
    @Resource
    ModelMapper modelMapper;
    /**
     * 上架模型
     * @param onemenu 一级菜单
     * @param twomenu 二级菜单
     * @param modelid 模型id
     * @param price 价格
     * @return
     */
    @RequestMapping("/addusermodel")
    public Result addusermodel(String onemenu,String twomenu,String modelid,int price){
        User user = ShiroUtil.getUser();
        int count = scUserModelNumMapper.getcount(user.getPhone());
        if(modelcount == count){
            return new Result(ResultStatusCode.UPPER_MODEL_CEILING);
        }
        int model =  modelMapper.getmodelid(modelid);     //查询模型的点赞数量
        ScShopping scShopping = new ScShopping();
        scShopping.setModelId(modelid);  //模型id
        scShopping.setOnemenu(onemenu);      //一级菜单
        scShopping.setTwomenu(twomenu); //模型id
        scShopping.setPrice(price); //价格
        scShopping.setTimer(new Date());    //时间
        scShopping.setThumbsnum(model);  //点赞数
        scShoppingMapper.insertSelective(scShopping);

        ScUserModel scUserModel = new ScUserModel();
        scUserModel.setUserphone(user.getPhone());  //用户手机号
        scUserModel.setOnemenu(onemenu);    //一级菜单
        scUserModel.setTwomenu(twomenu);    //二级菜单
        scUserModel.setModelId(modelid);    //模型id
        scUserModel.setPrice(price);    //价格
        scUserModel.setType(0); //是否上架 0上架 1下架
        scUserModelMapper.insertSelective(scUserModel);

        ScUserModelNum scUserModelNum = new ScUserModelNum();
        scUserModelNum.setUserphone(user.getPhone());   //用户手机号
        scUserModelNum.setModelId(modelid);     //模型id
        scUserModelNum.setNum(1);       //数量
        scUserModelNum.setTimer(scShopping.getTimer());        //上架时间
        scUserModelNumMapper.insertSelective(scUserModelNum);

        return new Result(ResultStatusCode.OK,"上架成功...");
    }
}
