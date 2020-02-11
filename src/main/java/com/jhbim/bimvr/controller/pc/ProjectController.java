package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.Rvt;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserProject;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.dao.mapper.RvtMapper;
import com.jhbim.bimvr.dao.mapper.UserProjectMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/${version}/project")
public class ProjectController {
    @Resource
    ProjectMapper projectMapper;
    @Resource
    UserProjectMapper userProjectMapper;
    @Resource
    IdWorker idWorker;
    @Resource
    RvtMapper rvtMapper;
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

    public void CreateProject(String name,String address,String createtime,String endtime){
        User user= ShiroUtil.getUser();
        String projectid=idWorker.nextId()+"";
        //保存项目
        Project project=new Project();
        project.setId(projectid);       //项目id
        project.setProjectName(name);   //项目名称
        project.setProjectModelAddr("http://36.112.65.110:8080/project/res_picture/0.png");     //项目的缩略图
        project.setCompletion(0);       //是否完工  1完工 0未完工
        project.setProjectStatus(3);        //项目状态 1已经完成 2正在进行中 3试用阶段
        project.setProjectAddress(address);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            project.setCreateTime(sdf.parse(createtime));   //开始时间
            project.setEndTime(sdf.parse(endtime));     //结束时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        File file = new File("C:/ftp/TomcatRoot/project/"+projectid+"/Rvt");
        file.mkdirs();
        File file1 = new File("C:/ftp/TomcatRoot/project/"+projectid+"/Price");
        file1.mkdirs();
        File file2 = new File("C:/ftp/TomcatRoot/project/"+projectid+"/Drawing");
        file2.mkdirs();
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
    public Result Addproject(String name,String address,String createtime,String endtime){
        User user= ShiroUtil.getUser();
        if(user.getRoleId()==4){
            return new Result(ResultStatusCode.ORDINARY);
        }
        int up = userProjectMapper.selectUserProjectnum(user.getUserId());
        if(user.getRoleId()==1){    //超级会员
            if(up<5){
                CreateProject(name,address,createtime,endtime);
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }else if(user.getRoleId()==2){  //特权会员
            if(up<3){
                CreateProject(name,address,createtime,endtime);
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }else if(user.getRoleId()==3){  //专享会员
            if(up<1){
                CreateProject(name,address,createtime,endtime);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        rvt.setModelId(1);
        rvt.setProjectId(projectid);
        rvt.setUserId(user.getUserId());
        rvt.setCreatetime(new Date());
        rvt.setUrl("/project/"+projectid+"/Rvt/"+name);
        rvtMapper.insert(rvt);
        return new Result(ResultStatusCode.OK);
    }
}
