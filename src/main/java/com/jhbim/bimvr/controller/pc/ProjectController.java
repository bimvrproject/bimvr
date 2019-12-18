package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserProject;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.dao.mapper.UserProjectMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    public void CreateProject(){
        User user= ShiroUtil.getUser();
        String projectid=idWorker.nextId()+"";
        //保存项目
        Project project=new Project();
        project.setId(projectid);
        project.setProjectModelAddr("http://36.112.65.110:8080/project/res_picture/0.png");
        project.setCompletion(0);
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
    public Result Addproject(){
        User user= ShiroUtil.getUser();
        if(user.getRoleId()==4){
            return new Result(ResultStatusCode.ORDINARY);
        }
        int up = userProjectMapper.selectUserProjectnum(user.getUserId());
        if(user.getRoleId()==1){
            if(up<5){
                CreateProject();
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }else if(user.getRoleId()==2){
            if(up<3){
                CreateProject();
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }else if(user.getRoleId()==3){
            if(up<1){
                CreateProject();
            }else{
                return new Result(ResultStatusCode.CREATE_PROJECT_CEILING);
            }
        }
        return new Result(ResultStatusCode.OK,"创建成功");
    }

    /**
     * 根据项目id编辑内容
     * @param project
     * @return
     */
    @RequestMapping("/UpdateProjectById")
    public Result UpdateProjectById(@RequestBody Project project){
        projectMapper.updateByPrimaryKeySelective(project);
        return new Result(ResultStatusCode.OK,"编辑成功");
    }
}
