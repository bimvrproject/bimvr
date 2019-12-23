package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Drawing;
import com.jhbim.bimvr.dao.entity.pojo.DrawingFolder;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.DrawingFolderMapper;
import com.jhbim.bimvr.dao.mapper.DrawingMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/${version}/drawing")
public class DrawingController {

    @Resource
    DrawingMapper drawingMapper;
    @Resource
    DrawingFolderMapper drawingFolderMapper;
    /**
     * 查询该项目下的图纸
     * @param projectid
     * @return
     */
    @RequestMapping("/getProjectDrawing")
    public Result getProjectDrawing(String projectid){
        if(projectid==null || projectid == "" || projectid.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        return new Result(ResultStatusCode.OK,drawingMapper.getprojectdrawing(projectid));
    }

    /**
     * 拖拽图纸批量修改type
     * @param ids
     * @return
     */
    @RequestMapping("/drawingbatchUpdate")
    public Result drawingbatchUpdate(Integer[] ids){
        if(ids==null || ids.length==0){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        drawingMapper.drawingbatchUpdate(ids);
        return new Result(ResultStatusCode.OK,"图纸type修改成功");
    }

    /**
     * 修改图纸名称
     * @param drawName
     * @param id
     * @return
     */
    @RequestMapping("/updateDrawingname")
    public Result updateDrawingname(String drawName,Integer id){
        if(drawName.isEmpty() || id==null){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        drawingMapper.updateDrawingname(drawName,id);
        return new Result(ResultStatusCode.OK,"图纸名称修改成功");
    }

    /**
     * 拖动Drawing图纸
     * @param projectid
     * @param foldername
     * @param ids
     * @return
     */
    @RequestMapping("/DragdropDrawing")
    public Result DragdropDrawing(String projectid,String foldername,Integer[] ids){
        if(projectid.isEmpty() || foldername.isEmpty() || ids.length==0){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user= ShiroUtil.getUser();
        Drawing drawing=new Drawing();
        drawing.setModelId(1);
        drawing.setProjectId(projectid);
        drawing.setFoldername(foldername);
        drawing.setUserId(user.getUserId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        drawing.setDtime(sdf.format(new Date()));
        drawingMapper.insertSelective(drawing);
        DrawingFolder drawingFolder = new DrawingFolder();
        for (int i=0;i<ids.length;i++){
            drawingFolder.setFoldername(foldername);
            drawingFolder.setDrawingId(String.valueOf(ids[i]));
            drawingFolder.setUserId(user.getUserId());
            drawingFolder.setDftime(drawing.getDtime());
            drawingFolderMapper.insertSelective(drawingFolder);
        }
        drawingMapper.drawingbatchUpdate(ids);
        return new Result(ResultStatusCode.OK,"图纸拖动成功");
    }
}
