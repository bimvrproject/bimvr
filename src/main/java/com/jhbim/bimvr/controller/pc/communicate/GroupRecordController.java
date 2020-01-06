package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.GroupMessage;
import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.GroupMessageVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.dao.mapper.GroupMessageMapper;
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
    @Resource      //进群和所在群记录表
    GroupRecordMapper groupRecordMapper;
    @Resource  //群组表
    GroupClusterMapper groupClusterMapper;
    @Resource       //群聊天记录表
    GroupMessageMapper groupMessageMapper;
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
        //查询所在群记录表
        List<GroupRecord> groupRecordList = groupRecordMapper.findByusergroup(user.getPhone(),"1");
        if(groupRecordList.size()==0){
            return new Result(ResultStatusCode.OK,map);
        }
        //获取群记录里面的群组号装到 list集合里
        for (GroupRecord u : groupRecordList) {
            list.add(u.getGroupid());
        }
        //从list集合得到群组号查询
        List<GroupCluster> groupClusters = groupClusterMapper.groupcluster(list);
        for (GroupCluster g : groupClusters) {
            System.out.println(g.getGroupno()+"---");
            GroupMessageVo groupMessageVo = new GroupMessageVo();
            List<GroupMessage> groupMessageList = groupMessageMapper.getusercount(user.getUserId(),g.getGroupno());
            for (GroupMessage groupMessage: groupMessageList) {
                System.out.println(groupMessage.getContent());
            }
//            groupMessageVo.setClusterList(groupClusters);
//            groupMessageVo.setCount(groupMessageList.size());

            map.put("data",groupMessageVo);
        }


//        groupMessageVo.setClusterList(groupClusters);
//        groupMessageVo.setCount(1);

//        map.put("count",groupClusters.size());
        return new Result(ResultStatusCode.OK,map);
    }
}
