package com.jhbim.bimvr.controller.pc.community;


import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.UserRankVo;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;

import com.jhbim.bimvr.utils.IdUtil;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelPayMapper modelPayMapper;

    @Resource
    ScShoppingMapper scShoppingMapper;

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
        for (int k =0;k<lists.size();k++){
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
     * @param modelid 模型id
     * @return
     */
    @PostMapping("/dianzan")
    public Result dianzan(String workId,Integer genre,Integer status,String modelid){
        User user = ShiroUtil.getUser();
        Zan select = zanMapper.select(workId, genre, user.getPhone());
        //Zan表如何为空 直接增加信息即可,反之根据条件修改即可
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
                System.out.println("进了没");
                //给用户点赞
                if(genre == 1){
                    User user1 = userMapper.selectByPrimaryKey(workId);
                    int a = userMapper.updateAccount(user1.getAccount()+1,workId);
                    if(a>0){
                        return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                    }
                    return new Result(ResultStatusCode.FAIL,"点赞失败");
                }
                //模型点赞
                if(genre == 2){
                    Model m = modelMapper.selectbymodelid(modelid);
                    Model model = new Model();
                    model.setAccount(m.getAccount()+1);
                    model.setModelId(modelid);
                    int i = modelMapper.updateAccount(model);
                    if(i>0){
                        ScShopping scShopping = new ScShopping();
                        scShopping.setModelId(m.getModelId());
                        scShopping.setThumbsnum(m.getAccount()+1);
                        scShoppingMapper.updatethumbsnum(scShopping);
                        System.out.println("123123132");
                        return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                    }
                    return new Result(ResultStatusCode.FAIL,"点赞失败");
                }
                //评论点赞
                if(genre == 3){

                }
            }
        }else{
            //给用户点赞、模型点赞
            if(genre == 1 && status ==1 || genre == 2 && status ==1){
                if(select.getStatus() == 0){
                    //给用户点赞
                    if(genre == 1){
                        Zan zan = new Zan();
                        zan.setId(select.getId());
                        zan.setWorkId(workId);
                        zan.setGenre(genre);
                        zan.setUserId(user.getPhone());
                        zan.setStatus(status);
                        zan.setCreateTime(new Date());
                        int i = zanMapper.updateByPrimaryKey(zan);
                        if(i>0) {
                            User user1 = userMapper.selectByPrimaryKey(workId);
                            int a = userMapper.updateAccount(user1.getAccount() + 1, workId);
                            if (a > 0) {
                                return new Result(ResultStatusCode.SUCCESS, "点赞成功");
                            }
                        }
                        return new Result(ResultStatusCode.FAIL,"点赞失败");
                    }
                    //模型点赞
                    if(genre == 2){
                        Zan zan = new Zan();
                        zan.setId(select.getId());
                        zan.setWorkId(workId);
                        zan.setGenre(genre);
                        zan.setUserId(user.getPhone());
                        zan.setStatus(status);
                        zan.setCreateTime(new Date());
                        int i = zanMapper.updateByPrimaryKey(zan);
                        if(i>0){
                            Model m = modelMapper.selectbymodelid(modelid);
                            Model model = new Model();
                            model.setAccount(m.getAccount()+1);
                            System.out.println();
                            model.setModelId(modelid);
                            int j = modelMapper.updateAccount(model);
                            if(j>0){
                                ScShopping scShopping = new ScShopping();
                                scShopping.setModelId(m.getModelId());
                                scShopping.setThumbsnum(m.getAccount()+1);
                                scShoppingMapper.updatethumbsnum(scShopping);
                                System.out.println();
                                return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                            }
                            return new Result(ResultStatusCode.FAIL,"点赞失败");
                        }
                    }
                }
            }
        }
        //用户取消点赞
        if (genre == 1 && status == 0){
            Zan unzan = new Zan();
            unzan.setId(select.getId());
            unzan.setWorkId(workId);
            unzan.setGenre(genre);
            unzan.setUserId(user.getPhone());
            unzan.setStatus(status);
            unzan.setCreateTime(new Date());
            int i = zanMapper.updateByPrimaryKey(unzan);
            if (i>0){
                //给用户取消点赞
                if(genre == 1 && select.getStatus() == 1){
                    User user1 = userMapper.selectByPrimaryKey(workId);
                    userMapper.updateAccount(user1.getAccount()-1,workId);
                    return new Result(ResultStatusCode.SUCCESS,"取消点赞");
                }
                return new Result(ResultStatusCode.FAIL,"您已取消点赞...");
            }
        }
        //模型取消点赞
        if (genre == 2 && status == 0){
            Zan unzan = new Zan();
            unzan.setId(select.getId());
            unzan.setWorkId(workId);
            unzan.setGenre(genre);
            unzan.setUserId(user.getPhone());
            unzan.setStatus(status);
            unzan.setCreateTime(new Date());
            int j = zanMapper.updateByPrimaryKey(unzan);
            if(j>0) {
                if (genre == 2 && select.getStatus() == 1) {
                    Model m = modelMapper.selectbymodelid(modelid);
                    Model model = new Model();
                    model.setAccount(m.getAccount() - 1);
                    model.setModelId(modelid);
                    int i = modelMapper.updateAccount(model);
                    if (i > 0) {
                        ScShopping scShopping = new ScShopping();
                        scShopping.setModelId(m.getModelId());
                        scShopping.setThumbsnum(m.getAccount() - 1);
                        scShoppingMapper.updatethumbsnum(scShopping);
                        return new Result(ResultStatusCode.SUCCESS, "取消点赞");
                    }
                }
            }
            return new Result(ResultStatusCode.FAIL,"您已取消点赞...");
        }
        return new Result(ResultStatusCode.FAIL,"您已点赞...");
    }

    /**
     * 模型排行
     * @return
     */
    @GetMapping("/getModeRank")
    public Result getModeRank(){
        User user = ShiroUtil.getUser();
        List<Model> listRank=modelMapper.listRank();
        //获取用户购买状态
        List<String> list = modelMapper.modelList();
        List<ModelPay> listPay = modelPayMapper.payList(list,user.getPhone());
        for (int i=0;i<listRank.size();i++) {
            for (int j=0;j<listPay.size();j++) {
                if (listRank.get(i).getModelId().equals(listPay.get(j).getModelId())){
                    listRank.get(i).setPayStatus(1);
                }
            }
        }
        for (int i = 0; i <listRank.size();i++) {
            listRank.get(i).setId(i+1);
            if (listRank.get(i).getPayStatus() == null){
                listRank.get(i).setPayStatus(0);
            }
        }
        return new Result(ResultStatusCode.SUCCESS,listRank);
    }
}

