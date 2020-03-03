package com.jhbim.bimvr.controller.pc.shopping;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.UpperModelVo;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Resource
    ProjectMapper projectMapper;

    /**
     * 我的模型
     * @return
     */
    @RequestMapping("getuppermodel")
    public Result getuppermodel(){
        User user = ShiroUtil.getUser();
        List<UpperModelVo> upperModelVoList = new ArrayList<>();
        List<ScUserModel> scUserModelList = scUserModelMapper.findbyusernamemodel(user.getPhone());
        for (ScUserModel s : scUserModelList) {
            UpperModelVo upperModelVo = new UpperModelVo();
            Project project = projectMapper.selectByPrimaryKey(s.getModelId());
            upperModelVo.setModelid(project.getId());
            upperModelVo.setThumbs(project.getProjectModelAddr());
            upperModelVo.setUpper(s.getType());
            upperModelVo.setProjectname(project.getProjectName());
            upperModelVoList.add(upperModelVo);
        }
        return new Result(ResultStatusCode.OK,upperModelVoList);
    }
    /**
     * 上架模型
     * @param onemenu 一级菜单
     * @param twomenu 二级菜单
     * @param modelid 模型id
     * @param price 价格
     * @return
     */
    @RequestMapping("/addusermodel")
    public Result addusermodel(String onemenu, String twomenu, String modelid,BigDecimal price){
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

    /**
     * 模型上架或下架
     * @param type   0上架 1下架
     * @param modelid  模型id
     * @return
     */
    @RequestMapping("/lowermodel")
    public Result lowermodel(int type,String modelid){
        User user = ShiroUtil.getUser();
        ScUserModel scUserModel = new ScUserModel();
        scUserModel.setUserphone(user.getPhone());
        scUserModel.setType(type);
        scUserModel.setModelId(modelid);
        int i = scUserModelMapper.updatetype(scUserModel);
        if(i>0){
            scShoppingMapper.modellowerdelete(modelid);
            ScUserModelNum scUserModelNum = new ScUserModelNum();
            scUserModelNum.setModelId(modelid);
            scUserModelNum.setUserphone(user.getPhone());
            scUserModelNumMapper.deletemodellower(scUserModelNum);
            return new Result(ResultStatusCode.OK,"操作成功...");
        }
        return new Result(ResultStatusCode.OK,"操作失败...");
    }
}
