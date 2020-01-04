package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.GroupRecordMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/${version}/grouprecord")
public class GroupRecordController {
    @Resource
    GroupRecordMapper groupRecordMapper;

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
}
