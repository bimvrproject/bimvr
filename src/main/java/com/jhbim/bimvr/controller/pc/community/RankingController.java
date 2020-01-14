package com.jhbim.bimvr.controller.pc.community;


import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.Zan;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.UserRankVo;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.dao.mapper.ZanMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;

import com.jhbim.bimvr.utils.IdUtil;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author shuihu
 * @create 2020-01-10 13:17
 */
@RestController
@RequestMapping("/${version}/ranking")
public class RankingController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ZanMapper zanMapper;

    /**
     * 获取用户排行
     * @return
     */
    @GetMapping("/getUserRank")
    public Result getUserRank(){
        User user = ShiroUtil.getUser();
        List<UserRankVo> lists = userMapper.userRank();
        List<UserRankVo> list1 = userMapper.userRanks();
        List<String> zanList = new ArrayList<>();
        list1.stream().forEach(zanlists->{
            zanList.add(zanlists.getPhone());
        });
        for (int i=0;i<lists.size();i++) {
            for (int j=0;j<list1.size();j++) {
               if (lists.get(i).getUserName().equals(list1.get(j).getUserName())){
                   list1.remove(j);
               }
            }
        }
        lists.addAll(list1);
        //获取点赞状态
        List<Zan> zanList1 = zanMapper.list(zanList, 1, user.getPhone());
        for (int i=0;i<lists.size();i++) {
            for (int j=0;j<zanList1.size();j++) {
                if (lists.get(i).getPhone().equals(zanList1.get(j).getWorkId())){
                    if (zanList1.get(j).getStatus()==1){
                        lists.get(i).setStatus(1);
                    }
                }
            }
        }
        for (int k =1;k<lists.size();k++){
            lists.get(k).setSequence(k+1);
            if (lists.get(k).getStatus()==null){
                lists.get(k).setStatus(0);
            }
        }
        return new Result(ResultStatusCode.SUCCESS,lists);
    }

    /**
     * 点赞/取消点赞
     * @param workId 对应的作品或评论的id或者用户
     * @param genre 点赞类型  1用户点赞  2 模型点赞  3 评论点赞
     * @param status 点赞状态  0--取消赞   1--有效赞
     * @return
     */
    @PostMapping("/dianzan")
    public Result dianzan(String workId,Integer genre,Integer status){
        User user = ShiroUtil.getUser();
        Zan select = zanMapper.select(workId, genre, user.getPhone());

        if (StringUtils.isEmpty(select)){
            Zan zan = new Zan();
            zan.setId(IdUtil.getIncreaseIdByNanoTime());
            zan.setWorkId(workId);
            zan.setGenre(genre);
            zan.setUserId(user.getPhone());
            zan.setStatus(status);
            zan.setCreateTime(new Date());
            int insert = zanMapper.insert(zan);
            if (insert>0){
                User user1 = userMapper.selectByPrimaryKey(workId);
                userMapper.updateAccount(user1.getAccount()+1,workId);
                return new Result(ResultStatusCode.SUCCESS,"点赞成功");
            }
        }else {
            Zan unzan = new Zan();
            unzan.setId(select.getId());
            unzan.setWorkId(workId);
            unzan.setGenre(genre);
            unzan.setUserId(user.getPhone());
            unzan.setStatus(status);
            unzan.setCreateTime(new Date());
            int i = zanMapper.updateByPrimaryKey(unzan);
            if (i>0){
                User user1 = userMapper.selectByPrimaryKey(workId);
                if (status == 1){
                    userMapper.updateAccount(user1.getAccount()+1,workId);
                }else {
                    userMapper.updateAccount(user1.getAccount()-1,workId);
                }
                return new Result(ResultStatusCode.SUCCESS,"修改成功");
            }
        }
        return new Result(ResultStatusCode.FAIL,"状态修改异常");
    }
}

