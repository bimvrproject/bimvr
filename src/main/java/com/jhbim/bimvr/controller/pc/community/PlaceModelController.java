package com.jhbim.bimvr.controller.pc.community;

import com.jhbim.bimvr.dao.entity.pojo.Model;
import com.jhbim.bimvr.dao.entity.pojo.PlaceModel;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.CommentMapper;
import com.jhbim.bimvr.dao.mapper.ModelMapper;
import com.jhbim.bimvr.dao.mapper.PlaceModelMapper;
import com.jhbim.bimvr.dao.mapper.ScShoppingMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdUtil;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/${version}/placemodel")
public class PlaceModelController {
    //创建地块的数量
   private static int count = 6;
    @Resource
    PlaceModelMapper placeModelMapper;
    @Resource
    IdWorker idWorker;
    @Resource
    ScShoppingMapper scShoppingMapper;
    @Resource
    ModelMapper modelMapper;
    @Resource
    CommentMapper commentMapper;
    /**
     * 创建地块
     * @param mainlandname  大陆名称
     * @param userphone 用户手机号
     * @param x x轴
     * @param y y轴
     * @param z z轴
     * @return
     */
    @RequestMapping("/addplacemodel")
    public Result addplacemodel(String mainlandname, String userphone, int x, int y, int z){
        int up = placeModelMapper.count(userphone);
        if(up == count){
            return new Result(ResultStatusCode.FAIL,"创建地块已到达上限,无法操作...");
        }
        //字母数字随机
        char c='A';
        c=(char)(c+(int)(Math.random()*26));
        String random = IdUtil.getRandomStr(6);
        String num =String.valueOf(c)+"-"+random;
        PlaceModel placeModel = new PlaceModel();
        placeModel.setId(idWorker.nextId()+""); //主键id
        placeModel.setMainlandname(mainlandname);   //大陆名称
        placeModel.setUsephone(userphone);      //登录人手机号
        placeModel.setPlotname(num+"地块");        //字母加数字随机
        placeModel.setX(x);              //x轴
        placeModel.setY(y);             //y轴
        placeModel.setZ(z);            //z轴
        placeModel.setCreatetime(new Date());   //创建时间
        placeModelMapper.insertSelective(placeModel);

        return new Result(ResultStatusCode.OK,placeModel);
    }

    /**
     * 展示本人的地块和查询地块
     * @param plotname 地块名称
     * @param type 0 是查询自己的 1 是根据地块查询
     * @param userphone 用户手机号
     * @return
     */
    @RequestMapping("/findbyplotname")
    public Result findbyplotname(String plotname,int type,String userphone ){
        if(type==0){
            return new Result(ResultStatusCode.OK,placeModelMapper.selectByUserphone(userphone));
        }
        if(type==1){
            PlaceModel placeModel = new PlaceModel();
            placeModel.setPlotname(plotname);
            placeModel.setHeat(1);
            placeModelMapper.updateheat(placeModel);
            return new Result(ResultStatusCode.OK,placeModelMapper.selectByplotname(plotname));
        }
       return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 根据id删除地块
     * @param id
     * @return
     */
    @RequestMapping("/deletemodel")
    public Result deletemodel(String id){
        PlaceModel placeModel = placeModelMapper.selectByPrimaryKey(id);
        if(placeModel.getModelid()!=null){
            return new Result(ResultStatusCode.FAIL,"无法删除...");
        }
        placeModelMapper.deleteByPrimaryKey(id);
        return new Result(ResultStatusCode.OK,"删除成功...");
    }

    /**
     * 根据热度查询
     * @return
     */
    @RequestMapping("/getAllHeat")
    public Result getAllHeat(){
        return new Result(ResultStatusCode.OK,placeModelMapper.getAllHeat());
    }

    /**
     *  地块拖拽模型和地块更换模型
     * @param id id编号
     * @param modelid 模型id
     * @return
     */
    @RequestMapping("/updatemodel")
    public Result updatemodel(String id,String modelid){
        User user = ShiroUtil.getUser();
        if(id.isEmpty() || modelid.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST,"参数解析失败...");
        }
        BigDecimal shopping_price = scShoppingMapper.selectmodelid(modelid);
        PlaceModel placeModel = new PlaceModel();
        placeModel.setId(id);
        placeModel.setModelid(modelid);
        placeModel.setPrice(shopping_price);
        int i = placeModelMapper.updatemodel(placeModel);
        if(i>0){
            PlaceModel placeModel1 = placeModelMapper.selectmodelid(modelid);
            Model model = new Model();
            model.setModelId(modelid);      //模型id
            model.setModelName(placeModel1.getMainlandname()+placeModel1.getPlotname());       //模型名称 大陆+地块名称
            model.setUserId(user.getPhone());       //用户手机号
            model.setThumbnail("https://cn.bing.com/th?id=OIP.aEDqlO75im7TYzuNTf1NlQHaE8&pid=Api&rs=1");  //缩略图
            modelMapper.insertSelective(model);
            return new Result(ResultStatusCode.OK,"操作成功...");
        }
        return new Result(ResultStatusCode.FAIL,"操作失败...");
    }

    /**
     * 根据id和地块模型查询
     * @param id id
     * @param plotname 地块名称
     * @return
     */
    @RequestMapping("/findByidandplotname")
    public Result findByidandplotname(String id,String plotname){
        if(id.isEmpty() || plotname.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST,"参数解析失败");
        }
        PlaceModel p = new PlaceModel();
        p.setId(id);
        p.setPlotname(plotname);
        PlaceModel placeModel = placeModelMapper.findByidandplotname(p);
        return new Result(ResultStatusCode.OK,placeModel);
    }

    /**
     * 进去某个地块 显示模型相关的数据 以及模型的点赞数量和评论数量
     * @param id id编号
     * @param mainlandname 大陆名称
     * @param plotname  地块名称
     * @return
     */
    @RequestMapping("/findByidandmainlandnameandplotname")
    public Result findByidandmainlandnameandplotname(String id,String mainlandname,String plotname){
        Map<String,Object> map = new HashMap<>();
        PlaceModel placeModel = new PlaceModel();
        placeModel.setId(id);
        placeModel.setMainlandname(mainlandname);
        placeModel.setPlotname(plotname);
        PlaceModel p = placeModelMapper.findByidandmainlandnameandplotname(placeModel);
        Model model = new Model();
        model.setModelId(p.getModelid());
        model.setModelName(p.getMainlandname()+p.getPlotname());
        System.out.println(p.getMainlandname()+p.getPlotname());
        int account = modelMapper.accountcount(model); //模型点赞数
        int comment = commentMapper.composeidnum(p.getModelid()); //模型评论数
        map.put("accountnum",account);
        map.put("content",p);
        map.put("commentnum",comment);
        return new Result(ResultStatusCode.OK,map);
    }
}
