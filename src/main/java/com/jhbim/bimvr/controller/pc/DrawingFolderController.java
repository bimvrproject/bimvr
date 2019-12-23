package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Drawing;
import com.jhbim.bimvr.dao.entity.pojo.DrawingFolder;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.DrawingFolderMapper;
import com.jhbim.bimvr.dao.mapper.DrawingMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${version}/DrawingFolder")
public class DrawingFolderController {
    @Resource
    DrawingFolderMapper drawingFolderMapper;

    @Resource
    DrawingMapper drawingMapper;

    /**
     * 根据创建时间和文件夹名称查询
     * @param dftime
     * @param foldername
     * @return
     */
    @RequestMapping("/drawinglistAll")
    public Result drawinglistAll(String dftime,String foldername){
        if(dftime.isEmpty() || foldername.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        List<DrawingFolder> drawingFolderList=drawingFolderMapper.DrawinglistAll(dftime,foldername);
        List<Drawing> drawingList = new ArrayList<>();
        for (DrawingFolder d : drawingFolderList) {
            Drawing drawing=drawingMapper.selectByPrimaryKey(Integer.valueOf(d.getDrawingId()));
            drawingList.add(drawing);
        }
        return new Result(ResultStatusCode.OK,drawingList);
    }

    /**
     * 根据创建时间修改文件夹的名称
     * @param dftime
     * @param foldername
     * @return
     */
    @RequestMapping("/drawingupdatefolder")
    public Result drawingupdatefolder(String dftime,String foldername){
        if(dftime.isEmpty() || foldername.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        //修改图纸表和它的关联关系表
        drawingMapper.updatedDrawingFolder(dftime,foldername);
        drawingFolderMapper.drawingupdatefolder(dftime,foldername);
        return new Result(ResultStatusCode.OK,"图纸文件夹修改成功");
    }
}
