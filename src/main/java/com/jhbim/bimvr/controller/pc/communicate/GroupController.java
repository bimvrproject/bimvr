package com.jhbim.bimvr.controller.pc.communicate;



import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.GroupMessageType;
import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.dao.mapper.GroupMessageTypeMapper;
import com.jhbim.bimvr.dao.mapper.GroupRecordMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
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
import java.util.List;

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
    @Resource
    GroupMessageTypeMapper groupMessageTypeMapper;
    @Resource
    UserMapper userMapper;
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
        groupCluster.setBrief("久仰大名，快来加入我们吧");
        groupCluster.setRemark("");
        groupCluster.setIsrecommend(1);
        int i = groupClusterMapper.insertSelective(groupCluster);
        if (i == 1 ){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //group_message_type保存记录
            GroupMessageType groupMessageType = new GroupMessageType();
            groupMessageType.setId(idWorker.nextId()+"");
            groupMessageType.setGroupno(groupCluster.getGroupno());
            groupMessageType.setToUser(user.getPhone());
            groupMessageType.setToTime(sdf.format(new Date()));
            groupMessageType.setFromTime(sdf.format(new Date()));
            groupMessageTypeMapper.insertSelective(groupMessageType);
            //group_record保存记录
            GroupRecord groupRecord=new GroupRecord();
            groupRecord.setId(idWorker.nextId()+"");
            groupRecord.setGroupid(groupCluster.getGroupno());
            groupRecord.setRoleId(1);
            groupRecord.setLevel(0);
            groupRecord.setUserphone(user.getPhone());
            groupRecord.setIslike(1);
            groupRecord.setMessage("");
            groupRecord.setGrtime(sdf.format(new Date()));
            groupRecordMapper.insertSelective(groupRecord);
            return new Result(ResultStatusCode.SUCCESS,groupCluster);
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

    /**
     * 请求进群记录
     * @param groupid 群号
     * @param message 内容
     * @return
     */
    @RequestMapping("/Ingroupof")
    public Result Ingroupof(String groupid,String message){
        if(groupid.isEmpty() || message.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user = ShiroUtil.getUser();
        GroupRecord groupRecord = new GroupRecord();
        groupRecord.setId(idWorker.nextId()+"");
        groupRecord.setGroupid(groupid);
        groupRecord.setRoleId(user.getRoleId());
        groupRecord.setUserphone(user.getPhone());
        groupRecord.setLevel(2);
        groupRecord.setIslike(0);
        groupRecord.setMessage(message);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        groupRecord.setGrtime(sdf.format(new Date()));
        int i= groupRecordMapper.insertSelective(groupRecord);
        if(i==1){
            return new Result(ResultStatusCode.OK,"请求发送成功");
        }
        return new Result(ResultStatusCode.FAIL,"请求发送失败");
    }

    /**
     * 查修改群简介看群信息
     * @param groupid
     * @return
     */
    @RequestMapping("/groupinformation")
    public Result groupinformation(String groupid){
        return new Result(ResultStatusCode.OK,groupClusterMapper.findByGroupNo(groupid));
    }

    /**
     * 修改群昵称
     * @param groupno 群号
     * @param groupname 昵称
     * @return
     */
    @RequestMapping("/updategroupname")
    public Result updategroupname(String groupno, String groupname){
        if(groupno.isEmpty() || groupname.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int i = groupClusterMapper.updategroupname(groupno,groupname);
        if(i==1){
            return new Result(ResultStatusCode.OK,"昵称修改成功");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 修改群简介
     * @param groupno 群号
     * @param brief 简介
     * @return
     */
    @RequestMapping("/updategroupbrief")
    public Result updategroupbrief(String groupno,String brief){
        if (groupno.isEmpty() || brief.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int i = groupClusterMapper.updategroupbrief(groupno,brief);
        if(i == 1){
            return new Result(ResultStatusCode.OK,"群简介修改成功");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 修改备注
     * @param groupno  群号
     * @param remark 备注
     * @return
     */
    @RequestMapping("/updategroupremark")
    public Result updategroupremark(String groupno,String remark){
        if(groupno.isEmpty() || remark.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int i = groupClusterMapper.updategroupremark(groupno,remark);
        if(i == 1){
            return new Result(ResultStatusCode.OK,"备注修改成功");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 修改群组是否推荐
     * @param groupno 群号
     * @param isrecommend 是否推荐 0不推荐 1推荐
     * @return
     */
    @RequestMapping("/updategroupisrecommend")
    public Result updategroupisrecommend(String groupno,Integer isrecommend){
        if(groupno.isEmpty() || isrecommend == null){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int i = groupClusterMapper.updategroupisrecommend(groupno,isrecommend);
        if(i == 1){
            return new Result(ResultStatusCode.OK,"操作成功");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 修改查找群组时是否展示即时聊天
     * @param groupno 群号
     * @param type  修改查找群组时是否展示即时聊天  1是显示0不显示
     * @return
     */
    @RequestMapping("/updategrouptype")
    public Result updategrouptype(String groupno,Integer type){
        if (groupno.isEmpty() || type == null){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        int i = groupClusterMapper.updategrouptype(groupno,type);
        if(i == 1){
            return new Result(ResultStatusCode.OK,"操作成功");
        }
        return new Result(ResultStatusCode.FAIL);
    }

    /**
     * 群主和管理员查看有申请进群记录
     * @param groupno
     * @return
     */
    @RequestMapping("/findbygroupnoid")
    public Result findbygroupnoid(String groupno){
        List<GroupRecord> recordList = groupRecordMapper.findbygroupnoid(groupno,0);
        for (GroupRecord g : recordList) {
            User user = userMapper.selectByPrimaryKey(g.getUserphone());
        }
        return new Result(ResultStatusCode.OK,recordList);
    }
}
