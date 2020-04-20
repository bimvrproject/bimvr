package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/${version}/project")
public class ProjectController {
    public final static Logger log = LoggerFactory.getLogger(ProjectController.class);
    @Resource
    ProjectMapper projectMapper;
    @Resource
    UserProjectMapper userProjectMapper;
    @Resource
    IdWorker idWorker;
    @Resource
    RvtMapper rvtMapper;
    @Resource
    ProjectGroupMapper projectGroupMapper;
    @Resource
    UserMapper userMapper;

    @Value("${host}")
    private volatile String HOST;

    @Value("${prot}")
    private volatile Integer PROT;

    @Value("${username}")
    private volatile String USERNAME;

    @Value("${password}")
    private volatile String PASSWROD;
    /**
     * 查询登录人所有的项目
     * @return
     */
    @RequestMapping("/getUserphone")
    public Result getUserphone(){
        User user= ShiroUtil.getUser();
        List<UserProject> userProjectList=userProjectMapper.selectuserid(user.getUserId());
        List<Project> projectList=new ArrayList<>();
        for (UserProject userProject:userProjectList) {
            Project project=projectMapper.selectByPrimaryKey(userProject.getProjectId());
            projectList.add(project);
        }
        return new Result(ResultStatusCode.OK,projectList);
    }

    public void CreateProject(String address,String createtime,String endtime){
        User user= ShiroUtil.getUser();
        String projectid=idWorker.nextId()+"";
        //保存项目
        Project project=new Project();
        project.setId(projectid);       //项目id
        project.setProjectName("新建项目");   //项目名称
        project.setProjectModelAddr(null);     //项目的缩略图http://36.112.65.110:8080/project/res_picture/0.png
        project.setCompletion(0);       //是否完工  1完工 0未完工
        project.setProjectStatus(3);        //项目状态 1已经完成 2正在进行中 3试用阶段
        project.setProjectAddress(address);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(createtime !="" || endtime != ""){
            try {
                project.setCreateTime(sdf.parse(createtime));   //开始时间
                project.setEndTime(sdf.parse(endtime));     //结束时间
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(HOST, PROT);
            ftpClient.login(USERNAME, PASSWROD);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                log.info("FTP server refused connection");
                ftpClient.disconnect();
            }
            // 切换到根目录
            ftpClient.changeWorkingDirectory("/TomcatRoot/project/");
            String path = projectid+"/Rvt/";
            String[] pah = path.split("/");
            // 分层创建目录 Rvt
            for (String pa : pah) {
                ftpClient.makeDirectory(pa);
                // 切到到对应目录
                ftpClient.changeWorkingDirectory(pa);
            }

            // 切换到根目录
            ftpClient.changeWorkingDirectory("/TomcatRoot/project/");
            String path1 = projectid+"/Price/";
            String[] pah1 = path1.split("/");
            // 分层创建目录 Price
            for (String pa : pah1) {
                ftpClient.makeDirectory(pa);
                // 切到到对应目录
                ftpClient.changeWorkingDirectory(pa);
            }

            // 切换到根目录
            ftpClient.changeWorkingDirectory("/TomcatRoot/project/");
            String path2 =projectid+"/Drawing/";
            String[] pah2 = path2.split("/");
            // 分层创建目录 Drawing
            for (String pa : pah2) {
                ftpClient.makeDirectory(pa);
                // 切到到对应目录
                ftpClient.changeWorkingDirectory(pa);
            }

            // 切换到根目录
            ftpClient.changeWorkingDirectory("/TomcatRoot/project/");
            String path3 =projectid+"/Paks/";
            String[] pah3 = path3.split("/");
            // 分层创建目录 Drawing
            for (String pa : pah3) {
                ftpClient.makeDirectory(pa);
                // 切到到对应目录
                ftpClient.changeWorkingDirectory(pa);
            }

            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                log.info("FTP服务器关闭连接!");
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        project.setRvtaddress("/TomcatRoot/project/"+projectid+"/Rvt");
        project.setPriceaddress("/TomcatRoot/project/"+projectid+"/Price");
        project.setDrawingaddress("/TomcatRoot/project/"+projectid+"/Drawing");
        projectMapper.insertSelective(project);
        //用户项目关系表
        UserProject userProject=new UserProject();
        userProject.setProjectId(projectid);
        userProject.setUserId(user.getUserId());
        userProject.setNum(1);
        userProject.setCreatetime(new Date());
        userProjectMapper.insertSelective(userProject);
    }
    /**
     * 创建项目
     * @return
     */
    @RequestMapping("/Addproject")
    public Result Addproject(String address,String createtime,String endtime){
        User user= ShiroUtil.getUser();
        if(user.getRoleId()==4){
            return new Result(ResultStatusCode.ORDINARY);
        }
        int up = userProjectMapper.selectUserProjectnum(user.getUserId());
        if(user.getRoleId()==1){    //超级会员
            if(up<6){
                CreateProject(address,createtime,endtime);
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }else if(user.getRoleId()==2){  //特权会员
            if(up<3){
                CreateProject(address,createtime,endtime);
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }else if(user.getRoleId()==3){  //专享会员
            if(up<1){
                CreateProject(address,createtime,endtime);
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }
        return new Result(ResultStatusCode.OK,"创建成功");
    }

    /**
     * 根据项目id编辑内容
     * @param projectname 项目名称
     * @param address   地址
     * @param content   备注
     * @param createtime    开始时间
     * @param endtime       结束时间
     * @param id        项目编号
     * @return
     */
    @RequestMapping("/UpdateProjectById")
    public Result UpdateProjectById(String projectname,String address,String content,String createtime,String endtime,String id){
        Project project = new Project();
        project.setId(id);
        project.setProjectName(projectname);
        project.setProjectAddress(address);
        project.setProjectContent(content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            project.setCreateTime(sdf.parse(createtime));
            project.setEndTime(sdf.parse(endtime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projectMapper.updateByPrimaryKeySelective(project);
        return new Result(ResultStatusCode.OK,"编辑成功");
    }

    /**
     * 存储rvt模型到数据库
     * @param projectid  项目id
     * @param name  模型名称
     * @return
     */
    @RequestMapping("/addrvt")
    public Result addrvt(String projectid,String name){
        if(projectid.isEmpty() || name.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user = ShiroUtil.getUser();
        Rvt rvt = new Rvt();
        rvt.setModelId(1);      //类型1表示建筑模型 2表示管道模型
        rvt.setProjectId(projectid);    //项目id
        rvt.setUserId(user.getUserId());    //用户id
        rvt.setCreatetime(new Date());  //上传时间
        rvt.setUrl("/project/"+projectid+"/Rvt/"+name); //存储地址
        rvtMapper.insertSelective(rvt);
        return new Result(ResultStatusCode.OK,"模型数据存储成功...");
    }

    /**
     * 根据项目id查询该项目的内容
     * @param projectid 项目id
     * @return
     */
    @RequestMapping("/findbyprojectid")
    public Result findbyprojectid(String projectid){
        Map<String,Object> map = new HashMap<>();
        Project project = projectMapper.selectByPrimaryKey(projectid);
        if(project==null){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        List<ProjectGroup> projectGroup = projectGroupMapper.findByprojectid(projectid);
        if(projectGroup.isEmpty()){
            map.put("projectid",project.getId());
            map.put("projectName",project.getProjectName());
            map.put("projectModelAddr",project.getProjectModelAddr());
            map.put("projectAddress",project.getProjectAddress());
            map.put("projectContent",project.getProjectContent());
            map.put("createTime",project.getCreateTime());
            map.put("endTime",project.getEndTime());
            map.put("grouptype",null);
            return new Result(ResultStatusCode.OK,map);
        }
        List list = new ArrayList<Map<String, Object>>();
        for (ProjectGroup group : projectGroup){
            Map<String,Object> map1=new HashMap<>();
            map1.put("projectid",project.getId());
            map1.put("projectName",project.getProjectName());
            map1.put("projectModelAddr",project.getProjectModelAddr());
            map1.put("projectAddress",project.getProjectAddress());
            map1.put("projectContent",project.getProjectContent());
            map1.put("createTime",project.getCreateTime());
            map1.put("endTime",project.getEndTime());
            map1.put("grouptype",group.getGrouptype());
            map1.put("groupno",group.getGroupno());
            list.add(map1);
        }
        return new Result(ResultStatusCode.OK,list);
    }

    /**
     * 根据项目id删除项目
     * @param projectid 项目id
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/deleteprojectid")
    public Result deleteprojectid(String projectid,String phone){
        User user = userMapper.selectByPrimaryKey(phone);
        UserProject userProject = new UserProject();
        userProject.setProjectId(projectid);
        userProject.setUserId(user.getUserId());
        int i =userProjectMapper.deleteprojectid(userProject);
        if(i > 0){
//            projectMapper.deleteByPrimaryKey(projectid);
            return new Result(ResultStatusCode.OK,"删除成功...");
        }
        return new Result(ResultStatusCode.FAIL,"删除失败...");
    }

    /**
     * 查看该项目中的是否有rvt文件
     * @param id
     * @return
     */
    @RequestMapping("/isExistsrvt")
    public Result isExistsrvt(String id){
        return new Result(ResultStatusCode.OK,rvtMapper.findByprojectid(id));
    }
}
