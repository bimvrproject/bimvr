package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserMessage;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.UserMessageMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/${version}/usermessage")
public class UserMessageController {

    @Resource
    IdWorker idWorker;
    @Resource
    UserMessageMapper userMessageMapper;


    /**
     * 存储好友聊天记录
     * @param friendphone  朋友手机号
     * @param information   聊天内容
     * @return
     */
    @RequestMapping("/AddMessage")
    public Result AddMessage(String friendphone,String information){
        if(friendphone.isEmpty() || information.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user = ShiroUtil.getUser();
        UserMessage userMessage = new UserMessage();
        userMessage.setId(idWorker.nextId()+"");
        userMessage.setHairuserPhone(user.getPhone());
        userMessage.setCloseduserPhone(friendphone);
        userMessage.setInformation(information);
        userMessage.setCreatetine(new Date());
        userMessage.setType(0);
        int i=userMessageMapper.insertSelective(userMessage);
        if(i==1){
            return new Result(ResultStatusCode.SUCCESS,"数据存储成功...");
        }
        return new Result(ResultStatusCode.FAIL);
    }
}