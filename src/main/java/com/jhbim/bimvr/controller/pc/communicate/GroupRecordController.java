package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.GroupVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.dao.mapper.GroupMessageTypeMapper;
import com.jhbim.bimvr.dao.mapper.GroupRecordMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/${version}/grouprecord")
public class GroupRecordController {
    @Resource      //进群和所在群记录表
    GroupRecordMapper groupRecordMapper;
    @Resource  //群组表
    GroupClusterMapper groupClusterMapper;
    @Resource //群聊天记录表
    GroupMessageTypeMapper groupMessageTypeMapper;
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
        //查询我所在群组 获取群组号（条件根据用户手机号和是否已在群组）
        List<GroupRecord> groupRecordList = groupRecordMapper.findByusergroup(user.getPhone(),"1");
        for (GroupRecord g : groupRecordList) {
            list.add(g.getGroupid());
        }
        //存储群组GroupVo类
        List<GroupVo> groupVoList = new ArrayList<>();
        //根据群号查询群组列表
        List<GroupCluster> groupClusterList = groupClusterMapper.groupcluster(list);
        for (GroupCluster groupCluster:groupClusterList) {
            GroupVo groupVo = new GroupVo();
            //查询用户在某个群组最后离开的时间(始终得到最后一条数据)
            List<GroupMessageType> groupMessageTypeList = groupMessageTypeMapper.findbyuserphone(user.getPhone(),groupCluster.getGroupno());
            for (GroupMessageType messageType :groupMessageTypeList) {
                //查询我在某个群组里面的未读消息条数（条件根据群组号和用户手机号以及最后离开的时间）
                System.out.println(user.getPhone()+"--"+messageType.getGroupno()+"--"+messageType.getFromTime()+"--"+messageType.getToUser());
                List<GroupMessageType> messageTypeList = groupMessageTypeMapper.getusercount(user.getPhone(),messageType.getGroupno(),messageType.getFromTime());
                groupVo.setCount(messageTypeList.size());
            }

            groupVo.setGroupno(groupCluster.getGroupno());
            groupVo.setGroupname(groupCluster.getGroupname());
            groupVo.setUsergroupId(groupCluster.getUsergroupId());
            groupVo.setPicture(groupCluster.getPicture());
            groupVoList.add(groupVo);
        }
        map.put("data",groupVoList);
        map.put("size",groupVoList.size());
        return new Result(ResultStatusCode.OK,map);
    }

}
