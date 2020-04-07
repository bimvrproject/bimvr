package com.jhbim.bimvr.controller.pc.community;


import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.DatingVo;
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
import java.math.BigDecimal;
import java.util.*;


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
    @Resource
    CommentMapper commentMapper;
    @Resource
    ReplyMapper replyMapper;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    PlaceModelMapper placeModelMapper;
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
     * @return
     */
    @PostMapping("/dianzan")
    public Result dianzan(String workId,Integer genre,Integer status){
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
                    Model m = modelMapper.selectbymodelid(workId);
                    Model model = new Model();
                    model.setAccount(m.getAccount()+1);
                    model.setModelId(workId);
                    int i = modelMapper.updateAccount(model);
                    if(i>0){
                        ScShopping scShopping = new ScShopping();
                        scShopping.setModelId(m.getModelId());
                        scShopping.setThumbsnum(m.getAccount()+1);
                        scShoppingMapper.updatethumbsnum(scShopping);
                        return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                    }
                }
                //评论点赞
                if(genre == 3){
                    Comment c = commentMapper.selectByPrimaryKey(workId);
                    if(c != null){
                        Comment comment = new Comment();
                        comment.setAccountnum(c.getAccountnum()+1);
                        comment.setId(workId);
                        int i = commentMapper.updateaccountnum(comment);
                        if(i>0){
                            return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                        }
                        return new Result(ResultStatusCode.FAIL,"点赞失败");
                    }
                    Reply reply = replyMapper.selectByPrimaryKey(workId);
                    if(reply != null){
                        Reply r = new Reply();
                        r.setAccountnum(reply.getAccountnum()+1);
                        r.setId(workId);
                        int i = replyMapper.updateaccountnum(r);
                        if(i>0){
                            return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                        }
                        return new Result(ResultStatusCode.FAIL,"点赞失败");
                    }
                }
            }
        }else{
            //给用户点赞、模型点赞
            if(genre == 1 && status ==1 || genre == 2 && status ==1 || genre == 3 && status ==1){
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
                            Model m = modelMapper.selectbymodelid(workId);
                            Model model = new Model();
                            model.setAccount(m.getAccount()+1);
                            System.out.println();
                            model.setModelId(workId);
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
                    //评论点赞
                    if(genre == 3){
                        Zan zan = new Zan();
                        zan.setId(select.getId());
                        zan.setWorkId(workId);
                        zan.setGenre(genre);
                        zan.setUserId(user.getPhone());
                        zan.setStatus(status);
                        zan.setCreateTime(new Date());
                        int i = zanMapper.updateByPrimaryKey(zan);
                        if(i>0){
                            Comment c = commentMapper.selectByPrimaryKey(workId);
                            if(c != null){
                                Comment comment = new Comment();
                                comment.setAccountnum(c.getAccountnum()+1);
                                comment.setId(workId);
                                int j = commentMapper.updateaccountnum(comment);
                                System.out.println();
                                if(j>0){
                                    return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                                }
                                return new Result(ResultStatusCode.FAIL,"点赞失败");
                            }
                            Reply reply = replyMapper.selectByPrimaryKey(workId);
                            if(reply != null){
                                Reply r = new Reply();
                                r.setAccountnum(reply.getAccountnum()+1);
                                r.setId(workId);
                                int j = replyMapper.updateaccountnum(r);
                                System.out.println();
                                if(j>0){
                                    return new Result(ResultStatusCode.SUCCESS,"点赞成功");
                                }
                                return new Result(ResultStatusCode.FAIL,"点赞失败");
                            }
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
                    Model m = modelMapper.selectbymodelid(workId);
                    Model model = new Model();
                    model.setAccount(m.getAccount() - 1);
                    model.setModelId(workId);
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
        //评论点赞取消
        if (genre == 3 && status == 0){
            Zan unzan = new Zan();
            unzan.setId(select.getId());
            unzan.setWorkId(workId);
            unzan.setGenre(genre);
            unzan.setUserId(user.getPhone());
            unzan.setStatus(status);
            unzan.setCreateTime(new Date());
            int j = zanMapper.updateByPrimaryKey(unzan);
            if(j>0){
                if (genre == 3 && select.getStatus() == 1) {
                    Comment c = commentMapper.selectByPrimaryKey(workId);
                    if(c != null){
                        Comment comment = new Comment();
                        comment.setAccountnum(c.getAccountnum()-1);
                        comment.setId(workId);
                        int i = commentMapper.updateaccountnum(comment);
                        if(i>0){
                            return new Result(ResultStatusCode.SUCCESS,"取消点赞");
                        }
                        return new Result(ResultStatusCode.SUCCESS,"取消点赞失败");
                    }
                    Reply reply = replyMapper.selectByPrimaryKey(workId);
                    if(reply != null){
                        Reply r = new Reply();
                        r.setAccountnum(reply.getAccountnum()-1);
                        r.setId(workId);
                        int i = replyMapper.updateaccountnum(r);
                        if(i>0){
                            return new Result(ResultStatusCode.SUCCESS,"取消点赞");
                        }
                        return new Result(ResultStatusCode.SUCCESS,"取消点赞失败");
                    }
                }
            }
            return new Result(ResultStatusCode.FAIL,"您已取消点赞");
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
            BigDecimal shopping_price = scShoppingMapper.selectmodelid(listRank.get(i).getModelId());
            listRank.get(i).setId(i+1);
            listRank.get(i).setPrice(shopping_price);
            if (listRank.get(i).getPayStatus() == null){
                listRank.get(i).setPayStatus(0);
            }
        }
        return new Result(ResultStatusCode.SUCCESS,listRank);
    }

    /**
     * 24小时刷新大厅数据 并在最后随机追加数据
     * @return
     */
    @GetMapping("/thumbuptheheathour")
    public Result thumbuptheheathour(){
        List<Zan> list = zanMapper.thumbup(2);
        List contentlist = new ArrayList();
        //存储24小时之内点赞的模型
        List<String> stringList = new ArrayList<>();
        for (Zan z : list) {
            Map<String,Object> map = new HashMap<>();
                List<PlaceModel> placeModelList1 = placeModelMapper.findBymodelidAll(z.getWorkId());
                for (PlaceModel placeModel:placeModelList1) {
                    Project project = projectMapper.selectByPrimaryKey(placeModel.getModelid());
                    User user = userMapper.selectByPrimaryKey(placeModel.getUsephone());
                    int accountnum = modelMapper.getmodelid(placeModel.getModelid());
                    DatingVo d = new DatingVo();
                    d.setId(z.getWorkId());
                    d.setUsername(user.getUserName());
                    d.setPicture(user.getPricture());
                    d.setProimg(project.getProjectModelAddr());
                    d.setAccount(accountnum);
                    d.setPlotname(placeModel.getPlotname());
                    contentlist.add(d);
                    stringList.add(z.getWorkId());
                }
        }
        List<PlaceModel> placeModelList = placeModelMapper.randthumbup(stringList);
        for (PlaceModel pm : placeModelList) {
            if(pm.getModelid() != null && pm.getModelid().length() != 0 && pm.getModelid() != ""){
                Map<String,Object> map = new HashMap<>();
                User user = userMapper.selectByPrimaryKey(pm.getUsephone());
                Project project = projectMapper.selectByPrimaryKey(pm.getModelid());
                int accountnum = modelMapper.getmodelid(pm.getModelid());
                map.put("id",pm.getModelid());
                map.put("username",user.getUserName());
                map.put("picture",user.getPricture());
                map.put("proimg",project.getProjectModelAddr());
                map.put("plotname",pm.getPlotname());
                map.put("account",accountnum);
                contentlist.add(map);
            }
        }
        return new Result(ResultStatusCode.OK,contentlist);
    }
}

