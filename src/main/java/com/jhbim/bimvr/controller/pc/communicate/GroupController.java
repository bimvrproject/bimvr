package com.jhbim.bimvr.controller.pc.communicate;



import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.PhoneRandom;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

/**
 * @author shuihu
 * @create 2020-01-02 15:15
 */

@RestController
@RequestMapping("/${version}/group")
public class GroupController {

    @Autowired
    private GroupClusterMapper groupClusterMapper;

    /**
     * 创建兴趣群
     */
    @GetMapping("/createGroup")
    public Result createGroup(@RequestParam int groupType){
        User user = ShiroUtil.getUser();
        GroupCluster groupCluster = new GroupCluster();
        PhoneRandom phoneRandom = new PhoneRandom();
        groupCluster.setGroupno(phoneRandom.getTel());
        groupCluster.setGroupname("新建群组");
        groupCluster.setRoleId(user.getRoleId());
        groupCluster.setUsergroupId(groupType);
        groupCluster.setUserId(user.getPhone());
        groupCluster.setLevel(0);
        groupCluster.setCreatetime(new Date());
        groupCluster.setType(1);
        groupCluster.setPicture("http://36.112.65.110:8080/project/res_picture/0.png");
        groupCluster.setBrief("");
        groupCluster.setRemark("");
        groupCluster.setIsrecommend(0);
        int i = groupClusterMapper.insertSelective(groupCluster);
        if (i == 1 ){
            return new Result(ResultStatusCode.SUCCESS,"创建群成功");
        }
        return new Result(ResultStatusCode.FAIL,"创建群失败");
    }
}
