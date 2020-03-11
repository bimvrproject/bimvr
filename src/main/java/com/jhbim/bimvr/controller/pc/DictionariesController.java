package com.jhbim.bimvr.controller.pc;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhbim.bimvr.dao.entity.pojo.Dictionaries;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.DictionariesMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/${version}/dictionaries")
public class DictionariesController {
    @Resource
    DictionariesMapper dictionariesMapper;

    /**
     * 查询父级权限
     * @return
     */
    @RequestMapping("/Byparentidisnull")
    public Result Byparentidisnull(){
        return new Result(ResultStatusCode.OK,dictionariesMapper.selectByparentid());
    }

    /**
     * 查询子权限
     * @return
     */
    @RequestMapping("/Byparentid")
    public Result Byparentid(Integer parentid){
        User user = ShiroUtil.getUser();
        return new Result(ResultStatusCode.OK,dictionariesMapper.selectByparentidchildren(parentid,user.getPhone()));
    }

    /**
     * 分页查询
     * @param parentid  子级id
     * @param pageNum 页码
     * @return
     */
    @RequestMapping("/findByPaging")
    public Result findByPaging(Integer parentid, Integer pageNum){
        User user = ShiroUtil.getUser();
        //默认一页6张
        Integer pageSize=6;
        Page<Dictionaries> page= PageHelper.offsetPage(pageNum,pageSize);
        List<Dictionaries> list=dictionariesMapper.selectByparentidchildren(parentid,user.getPhone());
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("pages",page.getPages());
        map.put("total",page.getTotal());
        return new Result(ResultStatusCode.SUCCESS,map);
    }
}
