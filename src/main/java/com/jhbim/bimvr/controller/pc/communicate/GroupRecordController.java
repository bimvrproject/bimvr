package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.GroupLevelVo;
import com.jhbim.bimvr.dao.entity.vo.GroupUserVo;
import com.jhbim.bimvr.dao.entity.vo.GroupVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Resource
    IdWorker idWorker;
    @Resource
    GroupMsgMapper groupMsgMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
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
     * 获取该群的所有人员
     * @param groupid 群号
     * @return
     */
    @RequestMapping("/getgropuserAll")
    public Result getgropuserAll(String groupid){
        //得到群员的手机号
        List<GroupRecord> groupRecordList = groupRecordMapper.getgropuserAll(groupid,1);
        List<GroupUserVo> groupUserVos = new ArrayList<>();
        for (GroupRecord g : groupRecordList) {
            //得到用户的信息
            List<User> userList = userMapper.getlistAll(g.getUserphone());
            for (User user: userList) {
                Role role = roleMapper.selectByPrimaryKey(user.getRoleId());
                GroupUserVo groupUserVo = new GroupUserVo();
                groupUserVo.setGroupid(groupid);
                groupUserVo.setUsername(user.getUserName());
                groupUserVo.setPicture(user.getPricture());
                groupUserVo.setCompanyname(user.getCompanyname());
                groupUserVo.setImage(role.getImage());
                groupUserVo.setPosotion(user.getPosotion());
                groupUserVo.setLevel(g.getLevel());
                groupUserVos.add(groupUserVo);
            }
        }
        User user = ShiroUtil.getUser();
        GroupRecord groupRecord = groupRecordMapper.fingByGroupIdandIslike(groupid,user.getPhone());
        GroupLevelVo groupLevelVo = new GroupLevelVo();
        groupLevelVo.setList(groupUserVos);
        groupLevelVo.setLevel(groupRecord.getLevel());
        return new Result(ResultStatusCode.OK,groupLevelVo);
    }


    /**
     * 设置管理员
     * @param groupid   群号
     * @param userphone 用户手机号
     * @return
     */
    @RequestMapping("/getadministrator")
    public Result getadministrator(String groupid,String userphone){
        if(groupid.isEmpty() || userphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int count = groupRecordMapper.getgroup(groupid,1);
        System.out.println(count);
        if(count==3){
            return new Result(ResultStatusCode.FAIL,"设置管理员已上限...");
        }
        groupRecordMapper.updategrouplevel(groupid,userphone,1);
        return new Result(ResultStatusCode.OK,"设置成功...");
    }

    /**
     * 取消管理员
     * @param groupid
     * @param userphone
     * @return
     */
    @RequestMapping("/canceladministrator")
    public Result canceladministrator(String groupid,String userphone){
        if(groupid.isEmpty() || userphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        groupRecordMapper.updategrouplevel(groupid,userphone,2);
        return new Result(ResultStatusCode.OK,"取消成功...");
    }

    /**
     * 解散群组和退出群组
     * @param groupid 群号
     * @return
     */
    @RequestMapping("/difference")
    public Result difference(String groupid){
        if(groupid.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user = ShiroUtil.getUser();
        GroupRecord groupRecord = groupRecordMapper.fingByGroupIdandIslike(groupid,user.getPhone());
        if(groupRecord.getLevel()==2){      //群员和管理员是退出群组
            System.out.println("退出群组");
            //删除进群记录
            int i = groupRecordMapper.deletegroupid(groupid,user.getPhone());
            if(i==1){
                //查询用户在删除群组的发言id并删除
                List<GroupMessageType> groupMessageTypeList = groupMessageTypeMapper.findbyuserphone(user.getPhone(),groupid);
                for (GroupMessageType g : groupMessageTypeList) {
                    //删除发言的内容
                    groupMsgMapper.deleteByPrimaryKey(g.getMsgId());
                    //删除群组存储记录类型表
                    groupMessageTypeMapper.deletemsgid(user.getPhone(),groupid);
                }
            }
            return new Result(ResultStatusCode.OK,"退群成功");
        }else if(groupRecord.getLevel()==0){   //群主是解散群和转让群主
            System.out.println("解散群组");
            //解散群组包括删除进群记录表、群组存储记录类型表、群组存储记录表
            groupRecordMapper.deletegroup(groupid);
            groupMessageTypeMapper.deletegrouptype(groupid);
            groupMsgMapper.deletegroupmsg(groupid);
            return new Result(ResultStatusCode.OK,"群组解散成功");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 转让群组
     * @param  groupid  群号
     * @param userphone 转让人手机号
     * @return
     */
    @RequestMapping("/thetransfergroup")
    public Result thetransfergroup(String groupid,String userphone){
        User user = ShiroUtil.getUser();
        if(groupid.isEmpty() || userphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        //群员升群主
        groupRecordMapper.updategrouplevel(groupid,userphone,0);
        //群主降群员
        groupRecordMapper.updategrouplevel(groupid,user.getPhone(),2);
        return new Result(ResultStatusCode.OK,"群组转让成功");
    }

    /**
     * 删除群成员
     * @return
     */
    @RequestMapping("/deltegroupuser")
    public Result deltegroupuser(String groupid,String userphone){
        if(groupid.isEmpty() || userphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int i = groupRecordMapper.deletegroupid(groupid,userphone);
        if(i==1){
            //查询用户在删除群组的发言id并删除
            List<GroupMessageType> MessageTypeList = groupMessageTypeMapper.findbyuserphone(userphone,groupid);
            for (GroupMessageType g : MessageTypeList) {
                //删除发言的内容
                groupMsgMapper.deleteByPrimaryKey(g.getMsgId());
                //删除群组存储记录类型表
                groupMessageTypeMapper.deletemsgid(userphone,groupid);
            }
        }
        return new Result(ResultStatusCode.OK);
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
//                System.out.println(user.getPhone()+"--"+messageType.getGroupno()+"--"+messageType.getFromTime()+"--"+messageType.getToUser());
                List<GroupMessageType> messageTypeList = groupMessageTypeMapper.getusercount(user.getPhone(),messageType.getGroupno(),messageType.getFromTime());
                groupVo.setCount(messageTypeList.size());
            }
            GroupRecord groupRecord = groupRecordMapper.fingByGroupIdandIslike(groupCluster.getGroupno(),user.getPhone());
            groupVo.setGroupno(groupCluster.getGroupno());
            groupVo.setGroupname(groupCluster.getGroupname());
            groupVo.setUsergroupId(groupCluster.getUsergroupId());
            groupVo.setPicture(groupCluster.getPicture());
            groupVo.setLevel(groupRecord.getLevel());
            groupVoList.add(groupVo);
        }
        map.put("data",groupVoList);
        map.put("size",groupVoList.size());
        return new Result(ResultStatusCode.OK,map);
    }

    /**
     * 被邀请进群记录
     * @return
     */
    @RequestMapping("/bvinvitedgroup")
    public Result bvinvitedgroup(){
        User user = ShiroUtil.getUser();
        List<GroupCluster> recordList = new ArrayList<>();
        List<GroupRecord> groupRecordList = groupRecordMapper.findByusergroup(user.getPhone(),"0");
        for (GroupRecord groupRecord : groupRecordList){
            GroupCluster groupCluster = groupClusterMapper.findbygroupid(groupRecord.getGroupid());
            recordList.add(groupCluster);
        }
        return new Result(ResultStatusCode.OK,recordList);
    }


    /**
     * 被邀请进群是否同意或忽略进群
     * @param groupid   群号
     * @param islike  1 同意 2 忽略
     * @return
     */
    @RequestMapping("/agreedtogroup")
    public Result agreedtogroup(String [] groupid,Integer islike){
        User user = ShiroUtil.getUser();
        int i = groupRecordMapper.updateislike(groupid,user.getPhone(),islike);
        if(i == 0){
            return new Result(ResultStatusCode.FAIL,"操作失败");
        }
        for (int j =0;j<groupid.length;j++){
            GroupMessageType groupMessageType = new GroupMessageType();
            groupMessageType.setId(idWorker.nextId()+"");
            groupMessageType.setGroupno(groupid[j]);
            groupMessageType.setToUser(user.getPhone());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            groupMessageType.setToTime(simpleDateFormat.format(new Date()));
            groupMessageType.setFromTime(simpleDateFormat.format(new Date()));
            groupMessageTypeMapper.insertSelective(groupMessageType);
        }
        return new Result(ResultStatusCode.SUCCESS,"操作成功");
    }
}
