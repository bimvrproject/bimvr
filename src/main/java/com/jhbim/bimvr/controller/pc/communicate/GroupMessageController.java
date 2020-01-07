package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.GroupMessage;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.GroupMessageVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupClusterMapper;
import com.jhbim.bimvr.dao.mapper.GroupMessageMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/${version}/groupmessage")
public class GroupMessageController {
    @Resource
    GroupMessageMapper groupMessageMapper;
    @Resource
    GroupClusterMapper groupClusterMapper;
    @Resource
    IdWorker idWorker;
    @Resource
    UserMapper userMapper;
    /**
     * 推荐群根据热度显示
     * @return
     */
    @RequestMapping("/getAllheat")
    public Result getAllheat(){
        List<GroupCluster> groupClusterList = new ArrayList<>();
        List<GroupMessage> groupMessages = groupMessageMapper.getAllheat();
        for (GroupMessage g :  groupMessages) {
            GroupCluster groupCluster = groupClusterMapper.findByGroupNo(g.getGroupId());
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
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setId(idWorker.nextId()+"");
        groupMessage.setGroupId(groupId);
        groupMessage.setUserId(user.getUserId());
        groupMessage.setRoleId(user.getRoleId());
        groupMessage.setContent(content);
        groupMessage.setCalltime(new Date());
        groupMessage.setType(0);
        int i=groupMessageMapper.insertSelective(groupMessage);
        if(i==1){
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
        List<GroupMessageVo> groupMessageVoList = new ArrayList<>();
        List<GroupMessage> groupMessageList = groupMessageMapper.readthecontent(groupno);
        for (GroupMessage g : groupMessageList) {
            List<User> userList = userMapper.findByuserid(g.getUserId());
            for (User u : userList) {
                GroupMessageVo groupMessageVo = new GroupMessageVo();
                groupMessageVo.setUsername(u.getUserName());
                groupMessageVo.setPicture(u.getPricture());
                groupMessageVo.setMessage(g.getContent());
                groupMessageVoList.add(groupMessageVo);
            }
        }
        return new Result(ResultStatusCode.OK,groupMessageVoList);
    }
}
