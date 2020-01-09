package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.GroupMessageVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/${version}/groupmessage")
public class GroupMessageController {
    @Resource
    GroupClusterMapper groupClusterMapper;
    @Resource
    IdWorker idWorker;
    @Resource
    GroupMsgMapper groupMsgMapper;
    @Resource
    GroupMessageTypeMapper groupMessageTypeMapper;
    @Resource
    UserMapper userMapper;
    /**
     * 推荐群根据热度显示
     * @return
     */
    @RequestMapping("/getAllheat")
    public Result getAllheat(){
        List<GroupCluster> groupClusterList = new ArrayList<>();
        List<GroupMessageType> groupMessages = groupMessageTypeMapper.getAllheat();
        for (GroupMessageType g :  groupMessages) {
            GroupCluster groupCluster = groupClusterMapper.findByGroupNo(g.getGroupno());
            groupClusterList.add(groupCluster);
        }
        return new Result(ResultStatusCode.OK,groupClusterList);
    }

    /**
     * 增加群聊天记录
     * @param groupId  群号
     * @param content   内容
     * @return
     */
    @RequestMapping("/AddGroupMessage")
    public Result AddGroupMessage(String groupId,String content){
        if(groupId.isEmpty() || content.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user = ShiroUtil.getUser();
        GroupMsg groupMsg = new GroupMsg();
        groupMsg.setGroupno(groupId);
        groupMsg.setMsgId(idWorker.nextId()+"");
        groupMsg.setMsg(content);
        groupMsg.setToTime(new Date());
        groupMsg.setFromTime(new Date());
        int i =groupMsgMapper.insertSelective(groupMsg);
        if(i==1){
            GroupMessageType groupMessageType = new GroupMessageType();
            groupMessageType.setId(idWorker.nextId()+"");
            groupMessageType.setGroupno(groupId);
            groupMessageType.setMsgId(groupMsg.getMsgId());
            groupMessageType.setToUser(user.getPhone());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            groupMessageType.setToTime(sdf.format(new Date()));
            groupMessageType.setFromTime(sdf.format(new Date()));
            groupMessageTypeMapper.insertSelective(groupMessageType);
            return new Result(ResultStatusCode.SUCCESS,"数据存储成功...");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 根据群id查询里面的内容
     * @param groupno 群id
     * @return
     */
    @RequestMapping("/readthecontent")
    public Result readthecontent(String groupno){
        User user = ShiroUtil.getUser();
        if(groupno.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        List<GroupMessageVo> groupMsgs = new ArrayList<>();
        List<GroupMessageType> groupMessageTypeList = groupMessageTypeMapper.findbygroupno(groupno);
        for (GroupMessageType g : groupMessageTypeList) {
            if(g.getMsgId()!=null){
                User u = userMapper.selectByPrimaryKey(g.getToUser());
                GroupMessageVo groupMessageVo = new GroupMessageVo();
                GroupMsg groupMsg = groupMsgMapper.selectByPrimaryKey(g.getMsgId());
                groupMessageVo.setMessage(groupMsg.getMsg());
                groupMessageVo.setUsername(u.getUserName());
                groupMessageVo.setPicture(u.getPricture());
                groupMsgs.add(groupMessageVo);
            }
        }
        return new Result(ResultStatusCode.OK,groupMsgs);
    }

    /**
     * 点击返回 获取所有的群组号 进行详细查询
     * @param groupid 群组号
     * @return
     */
    @RequestMapping("/findgroupno")
    public Result findgroupno(String [] groupid){
        User user = ShiroUtil.getUser();
        for (int i=0;i<groupid.length;i++){
            List<GroupMessageType> groupMessageTypeList = groupMessageTypeMapper.findbyuserphone(user.getPhone(),groupid[i]);
            for (GroupMessageType messageType : groupMessageTypeList){
                System.out.println(messageType.getGroupno()+"--"+messageType.getToTime()+"--"+messageType.getToUser()+"--"+messageType.getFromTime());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                groupMessageTypeMapper.updatefromtime(sdf.format(new Date()),messageType.getGroupno(),user.getPhone(),messageType.getToTime());
            }
        }
        return new Result(ResultStatusCode.OK,"操作成功");
    }
}
