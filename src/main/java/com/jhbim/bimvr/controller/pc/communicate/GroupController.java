package com.jhbim.bimvr.controller.pc.communicate;



import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.dao.mapper.GroupRecordMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.PhoneRandom;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Resource
    GroupRecordMapper groupRecordMapper;
    @Resource
    IdWorker idWorker;
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
        groupCluster.setUsergroupId(groupType);
        groupCluster.setCreatetime(new Date());
        groupCluster.setType(1);
        groupCluster.setPicture("http://36.112.65.110:8080/project/res_picture/0.png");
        groupCluster.setBrief("");
        groupCluster.setRemark("");
        groupCluster.setIsrecommend(0);
        int i = groupClusterMapper.insertSelective(groupCluster);
        if (i == 1 ){
            GroupRecord groupRecord=new GroupRecord();
            groupRecord.setId(idWorker.nextId()+"");
            groupRecord.setGroupid(groupCluster.getGroupno());
            groupRecord.setRoleId(1);
            groupRecord.setLevel(0);
            groupRecord.setUserphone(user.getPhone());
            groupRecord.setIslike(1);
            groupRecord.setMessage("");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            groupRecord.setGrtime(sdf.format(new Date()));
            groupRecordMapper.insertSelective(groupRecord);
            return new Result(ResultStatusCode.SUCCESS,"创建群成功");
        }
        return new Result(ResultStatusCode.FAIL,"创建群失败");
    }

    /**
     * 根据群号和群昵称查询
     * @param groupnoandname
     * @return
     */
    @RequestMapping("/findBygroupnoandgroupname")
    public Result findBygroupnoandgroupname(String groupnoandname){
        if(groupnoandname.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        return new Result(ResultStatusCode.OK,groupClusterMapper.findBygroupnoandgroupname(groupnoandname,groupnoandname));
    }


}
