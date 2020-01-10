package com.jhbim.bimvr.controller.pc.community;


import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.UserRankVo;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


/**
 * @author shuihu
 * @create 2020-01-10 13:17
 */
@RestController
//@RequestMapping("/${version}/ranking")
public class RankingController {
    private int number=1;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户排行
     * @return
     */
    @GetMapping("/getUserRank")
    public Result getUserRank(){
        List<UserRankVo> lists = userMapper.userRank();
        List<UserRankVo> list1 = userMapper.userRanks();
        for (int i=0;i<lists.size();i++) {
            for (int j=0;j<list1.size();j++) {
               if (lists.get(i).getUserName().equals(list1.get(j).getUserName())){
                   list1.remove(j);
               }
            }
        }
        lists.addAll(list1);
        lists.stream().forEach(listRank->{
            listRank.setSequence(number++);
        });

        return new Result(ResultStatusCode.SUCCESS,lists);
    }


}

