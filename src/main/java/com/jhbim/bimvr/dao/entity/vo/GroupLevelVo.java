package com.jhbim.bimvr.dao.entity.vo;

import java.util.List;

public class GroupLevelVo {
    List<GroupUserVo> list;
    Integer level;

    public List<GroupUserVo> getList() {
        return list;
    }

    public void setList(List<GroupUserVo> list) {
        this.list = list;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
