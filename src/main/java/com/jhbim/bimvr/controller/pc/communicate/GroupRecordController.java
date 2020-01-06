package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.dao.mapper.GroupRecordMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${version}/grouprecord")
public class GroupRecordController {
    @Resource
    GroupRecordMapper groupRecordMapper;
    @Resource
    GroupClusterMapper groupClusterMapper;
    /**
     * 查看本人是否在所对应的群组里
     * @param groupid 群号
     * @return
     */
    @RequestMapping("/fingByGroupIdandIslike")
    public Result fingByGroupIdandIslike(String groupid){
        User user= ShiroUtil.getUser();
        Map<String,Object> map = new HashMap<>();
        GroupRecord groupRecord = groupRecordMapper.fingByGroupIdandIslike(groupid,user.getPhone());
        if(groupRecord==null){
            map.put("islike",0);
            return new Result(ResultStatusCode.OK,map);
        }
        map.put("islike",groupRecord.getIslike());
        return new Result(ResultStatusCode.OK,map);
    }

    /**
     * 群主、管理员查看请求进群记录
     * @return
     */
    public Result notice(){
        User user = ShiroUtil.getUser();
        //判断登录人在群里面的权限  如果是权限
        return  new Result(ResultStatusCode.OK);
    }

    /**
     * 我加入的群
     * @return
     */
    @RequestMapping("/finbyusergroup")
    public Result finbyusergroup(){
        Map<String,Object> map =new HashMap<>();
        User user = ShiroUtil.getUser();
        List<String> list = new ArrayList<>();
        List<GroupRecord> groupRecordList = groupRecordMapper.findByusergroup(user.getPhone(),"1");
        for (GroupRecord u : groupRecordList) {
            list.add(u.getGroupid());
        }
        List<GroupCluster> groupClusters = groupClusterMapper.groupcluster(list);
        map.put("data",groupClusters);
        map.put("count",groupClusters.size());
        return new Result(ResultStatusCode.OK,groupClusters);
    }
}
