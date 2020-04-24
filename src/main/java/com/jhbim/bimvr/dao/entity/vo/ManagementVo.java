package com.jhbim.bimvr.dao.entity.vo;

//管理界面模型信息 数据Vo
public class ManagementVo {
    //用户手机号
    private String phone;
    //会员等级
    private String rolename;
    //项目id
    private String projectid;
    //项目名称
    private String projectname;
    //模型文件
    private String rvtname;
    //模型状态
    private String type;

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRvtname() {
        return rvtname;
    }

    public void setRvtname(String rvtname) {
        this.rvtname = rvtname;
    }
}
