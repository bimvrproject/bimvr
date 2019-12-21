package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.DrawingMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${version}/drawing")
public class DrawingController {

    @Resource
    DrawingMapper drawingMapper;

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

    @RequestMapping("/drawingbatchUpdate")
    public Result drawingbatchUpdate(Integer[] ids){
        if(ids==null || ids.length==0){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        drawingMapper.drawingbatchUpdate(ids);
        return new Result(ResultStatusCode.OK,"图纸type修改成功");

    }
}
