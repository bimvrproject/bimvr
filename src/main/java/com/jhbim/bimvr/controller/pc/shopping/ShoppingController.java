package com.jhbim.bimvr.controller.pc.shopping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.ScShopping;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.ShoppingVo;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.dao.mapper.ScShoppingMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${version}/shopping")
public class ShoppingController {

    @Resource
    ScShoppingMapper scShoppingMapper;
    @Resource
    ProjectMapper projectMapper;

    /**
     * 商城界面条件查询
     * @param pageNum   页数
     * @param onemenu   一级菜单名称
     * @param twomenu   二级菜单名称
     * @param type  type='' 按照上架时间降序排序
     *               type='1' 按照点赞数降序排序
     *               type='2' 按照点赞数升序排序
     * @return
     */
    @RequestMapping("/getlist")
    public Result getList(Integer pageNum,String onemenu,String twomenu,String type){
        Map<String,Object> map=new HashMap<>();
        //默认一页6张
        Integer pageSize=6;
        Page<ScShopping> page = PageHelper.startPage(pageNum,pageSize);
        ScShopping scShopping = new ScShopping();
        //默认是按照上架时间降序
        if(type.equals("")){
            System.out.println("空的");
            if(twomenu.equals("全部")){
                scShopping.setOnemenu(onemenu);
                List<ScShopping> scShoppingList = scShoppingMapper.getlisttimerdesc(scShopping);
                List<ShoppingVo> shoppingVos = new ArrayList<>();
                for (ScShopping s : scShoppingList) {
                    Project project=projectMapper.selectByPrimaryKey(s.getModelId());
                    ShoppingVo shoppingVo = new ShoppingVo();
                    shoppingVo.setThumbnail(project.getProjectModelAddr());
                    shoppingVo.setPrice(s.getPrice());
                    shoppingVo.setModelid(project.getId());
                    shoppingVo.setThumbsnum(s.getThumbsnum());
                    shoppingVos.add(shoppingVo);
                }
                map.put("data",shoppingVos);
                map.put("pages",page.getPages());
                map.put("total",page.getTotal());
                return new Result(ResultStatusCode.OK,map);
            }
            scShopping.setOnemenu(onemenu);
            scShopping.setTwomenu(twomenu);
            List<ScShopping> scShoppingList = scShoppingMapper.getlisttimer(scShopping);
            List<ShoppingVo> shoppingVos = new ArrayList<>();
            for (ScShopping s : scShoppingList) {
                Project project=projectMapper.selectByPrimaryKey(s.getModelId());
                ShoppingVo shoppingVo = new ShoppingVo();
                shoppingVo.setThumbnail(project.getProjectModelAddr());
                shoppingVo.setPrice(s.getPrice());
                shoppingVo.setModelid(project.getId());
                shoppingVo.setThumbsnum(s.getThumbsnum());
                shoppingVos.add(shoppingVo);
            }
            map.put("data",shoppingVos);
            map.put("pages",page.getPages());
            map.put("total",page.getTotal());
            return new Result(ResultStatusCode.OK,map);
        }
        //按照点赞数降序
        if(type.equals("1")){
            if(twomenu.equals("全部")){
                scShopping.setOnemenu(onemenu);
                List<ScShopping> scShoppingList = scShoppingMapper.getlistonemenuthumbsnumdesc(scShopping);
                List<ShoppingVo> shoppingVos = new ArrayList<>();
                for (ScShopping s : scShoppingList) {
                    Project project=projectMapper.selectByPrimaryKey(s.getModelId());
                    ShoppingVo shoppingVo = new ShoppingVo();
                    shoppingVo.setThumbnail(project.getProjectModelAddr());
                    shoppingVo.setPrice(s.getPrice());
                    shoppingVo.setModelid(project.getId());
                    shoppingVo.setThumbsnum(s.getThumbsnum());
                    shoppingVos.add(shoppingVo);
                }
                map.put("data",shoppingVos);
                map.put("pages",page.getPages());
                map.put("total",page.getTotal());
                return new Result(ResultStatusCode.OK,map);
            }
            scShopping.setOnemenu(onemenu);
            scShopping.setTwomenu(twomenu);
            List<ScShopping> scShoppingList = scShoppingMapper.getlistthumbsnumdesc(scShopping);
            List<ShoppingVo> shoppingVos = new ArrayList<>();
            for (ScShopping s : scShoppingList) {
                Project project=projectMapper.selectByPrimaryKey(s.getModelId());
                ShoppingVo shoppingVo = new ShoppingVo();
                shoppingVo.setThumbnail(project.getProjectModelAddr());
                shoppingVo.setPrice(s.getPrice());
                shoppingVo.setModelid(project.getId());
                shoppingVo.setThumbsnum(s.getThumbsnum());
                shoppingVos.add(shoppingVo);
            }
            map.put("data",shoppingVos);
            map.put("pages",page.getPages());
            map.put("total",page.getTotal());
            return new Result(ResultStatusCode.OK,map);
        }
        //按照点赞数升序
        if(type.equals("2")){
            if(twomenu.equals("全部")){
                scShopping.setOnemenu(onemenu);
                List<ScShopping> scShoppingList = scShoppingMapper.getlistonemenuthumbsnum(scShopping);
                List<ShoppingVo> shoppingVos = new ArrayList<>();
                for (ScShopping s : scShoppingList) {
                    Project project=projectMapper.selectByPrimaryKey(s.getModelId());
                    ShoppingVo shoppingVo = new ShoppingVo();
                    shoppingVo.setThumbnail(project.getProjectModelAddr());
                    shoppingVo.setPrice(s.getPrice());
                    shoppingVo.setModelid(project.getId());
                    shoppingVo.setThumbsnum(s.getThumbsnum());
                    shoppingVos.add(shoppingVo);
                }
                map.put("data",shoppingVos);
                map.put("pages",page.getPages());
                map.put("total",page.getTotal());
                return new Result(ResultStatusCode.OK,map);
            }
            scShopping.setOnemenu(onemenu);
            scShopping.setTwomenu(twomenu);
            List<ScShopping> scShoppingList = scShoppingMapper.getlistthumbsnum(scShopping);
            List<ShoppingVo> shoppingVos = new ArrayList<>();
            for (ScShopping s : scShoppingList) {
                Project project=projectMapper.selectByPrimaryKey(s.getModelId());
                ShoppingVo shoppingVo = new ShoppingVo();
                shoppingVo.setThumbnail(project.getProjectModelAddr());
                shoppingVo.setPrice(s.getPrice());
                shoppingVo.setModelid(project.getId());
                shoppingVo.setThumbsnum(s.getThumbsnum());
                shoppingVos.add(shoppingVo);
            }
            map.put("data",shoppingVos);
            map.put("pages",page.getPages());
            map.put("total",page.getTotal());
            return new Result(ResultStatusCode.OK,map);
        }
       return new Result(ResultStatusCode.FAIL);



    }
}
