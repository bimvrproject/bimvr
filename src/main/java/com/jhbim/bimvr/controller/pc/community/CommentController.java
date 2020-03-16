package com.jhbim.bimvr.controller.pc.community;

import com.jhbim.bimvr.dao.entity.pojo.Comment;
import com.jhbim.bimvr.dao.entity.pojo.Reply;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.CommentVo;
import com.jhbim.bimvr.dao.entity.vo.ReplyVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.CommentMapper;
import com.jhbim.bimvr.dao.mapper.ReplyMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/${version}/comment")
public class CommentController {
    @Resource
    CommentMapper commentMapper;
    @Resource
    ReplyMapper replyMapper;
    @Resource
    IdWorker idWorker;
    @Resource
    UserMapper userMapper;

    /**
     * 发布评论
     * @param modelid       模型id
     * @param content       评论内容
     * @param userphone     评论人手机号
     * @return
     */
    @RequestMapping("/addcomment")
    public Result addcomment(String modelid,String content,String userphone){
        if (modelid.isEmpty() || content.isEmpty() || userphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST,"参数解析失败");
        }
        Comment comment = new Comment();
        comment.setId(idWorker.nextId()+""); //主键id
        comment.setComposeId(modelid);      //模型id
        comment.setContent(content);        //评论内容
        comment.setFromUserid(userphone);       //评论用户手机号
        comment.setCreateTime(new Date());  //发布时间
        commentMapper.insertSelective(comment);
        return new Result(ResultStatusCode.OK,"评论成功...");
    }

    /**
     * 回复评论
     * @param commentid 回复目标id
     * @param content  回复内容
     * @param fromuserphone 回复用户id
     * @param touserphone 目标用户id
     * @return
     */
    @RequestMapping("/addreply")
    public Result addreply(String commentid,String content,String fromuserphone,String touserphone){
        User fromuser = userMapper.selectByPrimaryKey(fromuserphone);
        User touser = userMapper.selectByPrimaryKey(touserphone);
        Reply reply = new Reply();
        reply.setId(idWorker.nextId()+""); //主键id
        reply.setCommentId(commentid); //回复目标id
        reply.setContent(content);      //回复内容
        reply.setFromUserid(fromuser.getUserId());    //回复用户id
        reply.setToUserid(touser.getUserId());        //目标用户id
        reply.setCreateTime(new Date());        //回复评论时间
        replyMapper.insertSelective(reply);
        return new Result(ResultStatusCode.OK,"回复评论成功...");
    }

    /**
     * 根据模型id展示该模型的被评论的内容
     * @param composeId  模型id
     * @return
     */
    @RequestMapping("/findBymodelid")
    public Result findBymodelid(String composeId,String userphone){
        User u = ShiroUtil.getUser();
        List<CommentVo> list = new ArrayList<>();
        //父级评论表的内容存储
        List<Comment> comments = commentMapper.findBymodelid(composeId);
        for (Comment c : comments) {
            CommentVo commentVo = new CommentVo();
            User user = userMapper.selectByPrimaryKey(c.getFromUserid());
            commentVo.setId(c.getId());
            commentVo.setPicture(user.getPricture());
            commentVo.setName(user.getUserName());
            commentVo.setTime(c.getCreateTime());
            commentVo.setComment(c.getContent());
            //子级评论表的存储
            List<Reply> replies = replyMapper.findByreplycommentid(c.getId());
            List<ReplyVo> replyVoList  = new ArrayList<>();
            for (Reply r : replies) {
                ReplyVo replyVo = new ReplyVo();
                User replyuser = userMapper.findByuserid(r.getToUserid());
                replyVo.setId(r.getId());
                replyVo.setPicture(replyuser.getPricture());
                replyVo.setTousername(replyuser.getUserName());
                replyVo.setUserphone(replyuser.getPhone());
                replyVo.setTime(r.getCreateTime());
                replyVo.setContent(r.getContent());
                replyVo.setAccountnum(r.getAccountnum());
                if(r.getToUserid().equals(u.getUserId())){
                    replyVo.setState(1);
                }else{
                    replyVo.setState(0);
                }
                replyVoList.add(replyVo);
            }
            commentVo.setReplyVos(replyVoList);
            if(c.getFromUserid().equals(userphone)){
                commentVo.setState(1);
            }else{
                commentVo.setState(0);
            }
            commentVo.setAccountnum(c.getAccountnum());
            commentVo.setUserphone(c.getFromUserid());
            list.add(commentVo);
        }
        return new Result(ResultStatusCode.OK,list);
    }

    /**
     * 删除父级评论和子级评论
     * @param commentid  父级评论id
     * @return
     */
    @RequestMapping("/deletecomment")
    public Result deletecomment(String commentid){
        int i = commentMapper.deleteByPrimaryKey(commentid);
        if(i>0){
             int j =replyMapper.deleteByCommentid(commentid);
             if(j>0){
                 return new Result(ResultStatusCode.OK,"删除评论成功...");
             }
        }
        return new Result(ResultStatusCode.FAIL,"删除失败...");
    }

}
