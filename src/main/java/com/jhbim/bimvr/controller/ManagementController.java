package com.jhbim.bimvr.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.ManagementUserVo;
import com.jhbim.bimvr.dao.entity.vo.ManagementVo;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.FTPUtils;
import com.jhbim.bimvr.utils.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Management")
public class ManagementController {
    @Value("${host}")
    private volatile String HOST;

    @Value("${prot}")
    private volatile Integer PROT;

    @Value("${username}")
    private volatile String USERNAME;

    @Value("${password}")
    private volatile String PASSWROD;
    @Resource
    RvtMapper rvtMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    MemberEndMapper memberEndMapper;
    @Resource
    ManagementFeedbackUserMapper feedbackUserMapper;
    @Resource
    IdWorker idWorker;

   //模型信息
    /**
     * 分页查询
     * @param offset 页 码
     * @param limit 每页条数
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(@RequestParam(defaultValue = "1" )Integer offset , @RequestParam(defaultValue = "10") Integer limit,String name){
        Map<String,Object> map = new HashMap<>();
        List<ManagementVo> managementVoList =new ArrayList();
        Page<Rvt> rvtPage = PageHelper.startPage(offset,limit);
        List<Rvt> rvtList = rvtMapper.findAll(name);
        for (Rvt rvt :rvtList) {
            ManagementVo managementVo = new ManagementVo();
            User user = userMapper.findByuserid(rvt.getUserId());
            Role role = roleMapper.selectByPrimaryKey(user.getRoleId());
            String phone = user.getPhone();
            String phoneNumber = phone.substring(0, 3) + "****" + phone.substring(7);
            managementVo.setPhone(phoneNumber);
            managementVo.setRolename(role.getRoleName());
            if(rvt.getType() == 0){
                managementVo.setType("未处理");
            }else if(rvt.getType() == 1){
                managementVo.setType("已处理");
            }
            Project project = projectMapper.selectByPrimaryKey(rvt.getProjectId());
            managementVo.setProjectid(project.getId());
            managementVo.setProjectname(project.getProjectName());
            String rvtname =  rvt.getUrl().substring( rvt.getUrl().lastIndexOf("/")+1);
            managementVo.setRvtname(rvtname);
            managementVoList.add(managementVo);
        }
        map.put("data",managementVoList);
        map.put("pages",rvtPage.getPages());
        map.put("total",rvtPage.getTotal());
        return new Result(ResultStatusCode.OK,map);
    }

    /**
     * 下载文件
     * @param projectid 项目id
     * @return
     */
    @RequestMapping("/downlaod")
    public Result downlaod(String projectid){
        return new Result(ResultStatusCode.OK,rvtMapper.findByprojectid(projectid));
    }

    /**
     * 上传文件夹
     * @param id  项目id
     * @param address 路径
     * @return
     */
    @RequestMapping("/uploadfolder")
    public Result uploadfolder(String id,String address){
        if(id.isEmpty() || address.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        Project project = projectMapper.selectByPrimaryKey(id);
        //FTP文件夹路径
        String Ftpaddress = "/TomcatRoot/project/"+project.getId()+"/Paks/";
        FTPUtils ftp=new FTPUtils(HOST,PROT,USERNAME,PASSWROD);
        ftp.ftpLogin();
        //上传文件夹
        boolean uploadFlag = ftp.uploadDirectory(address, Ftpaddress); //如果是admin/那么传的就是所有文件，如果只是/那么就是传文件夹
        if(uploadFlag){
            Rvt rvt = new Rvt();
            rvt.setProjectId(id);
            rvt.setType(1);
            rvtMapper.updatervttype(rvt);
        }else{
            return new Result(ResultStatusCode.OK,"上传失败...");
        }
        ftp.ftpLogOut();
        return new Result(ResultStatusCode.OK,"上传成功...");
    }

    //用户信息

    /**
     * 查询全部的会员名称
     * @return
     */
    @RequestMapping("/findByroelAll")
    public Result findByroelAll(){
        return new Result(ResultStatusCode.OK,roleMapper.list());
    }
    /**
     * 根据手机手机号和会员等级查询
     * @param pageNum 条数
     * @param pageSize 页面
     * @param phone 手机号
     * @param roleid 会员等级id
     * @return
     */
    @RequestMapping("/userAll")
    public Result userAll(Integer pageNum, Integer pageSize,String phone,Integer roleid){
        Map<String,Object> map = new HashMap<>();
        Page<User> userPage = PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.listAll(phone,roleid);
        List<ManagementUserVo> managementUserVoArrayList = new ArrayList<>();
        for (User u : list) {
            ManagementUserVo managementUserVo = new ManagementUserVo();
            Role role = roleMapper.selectByPrimaryKey(u.getRoleId());
            MemberEnd memberEnd = memberEndMapper.selectbyuserid(u.getRoleId());
            ManagementFeedbackUser managementFeedbackUser = feedbackUserMapper.findByuserphone(u.getPhone());

            managementUserVo.setPhone(u.getPhone());
            managementUserVo.setRolename(role.getRoleName());
            managementUserVo.setMemberend(memberEnd.getEndtime());
            if(managementFeedbackUser == null){
                managementUserVo.setFeedbackcontene("暂无");
            }else{
                managementUserVo.setFeedbackcontene(managementFeedbackUser.getFeedbackContent());
            }
            managementUserVoArrayList.add(managementUserVo);
        }
        map.put("data",managementUserVoArrayList);
        map.put("pages",userPage.getPages());
        map.put("total",userPage.getTotal());
        return new Result(ResultStatusCode.OK,map);
    }

    /**
     * 查看用户反馈信息
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/findByuserphone")
    public Result findByuserphone(String phone){
        return new Result(ResultStatusCode.OK,feedbackUserMapper.findByuserphone(phone));
    }

    /**
     * 用户反馈信息
     * @param phone 用户手机号
     * @param feedbackcontent  反馈信息
     * @return
     */
    @RequestMapping("/Userfeedback")
        public Result Userfeedback(String phone,String feedbackcontent){
        ManagementFeedbackUser m = feedbackUserMapper.findByuserphone(phone);
        if(m !=null){
            return new Result(ResultStatusCode.FAIL,"已提交信息请选择修改内容...");
        }
        ManagementFeedbackUser user = new ManagementFeedbackUser();
        user.setId(idWorker.nextId()+"");
        user.setUserphone(phone);
        user.setFeedbackContent(feedbackcontent);
        int i = feedbackUserMapper.insert(user);
        if(i==1){
            return new Result(ResultStatusCode.OK,"提交成功...");
        }
        return new Result(ResultStatusCode.FAIL,"提交失败....");
    }

    /**
     * 修改反馈信息
     * @param phone 手机号
     * @param feedbackcontent 反馈内容
     * @return
     */
    @RequestMapping("/updateByuserphone")
    public Result updateByuserphone(String phone,String feedbackcontent){
        ManagementFeedbackUser user = new ManagementFeedbackUser();
        user.setUserphone(phone);
        user.setFeedbackContent(feedbackcontent);
        int i = feedbackUserMapper.updateByphone(user);
        if(i == 1){
            return new Result(ResultStatusCode.OK,"修改成功...");
        }
        return new Result(ResultStatusCode.FAIL,"修改失败...");
    }
}
