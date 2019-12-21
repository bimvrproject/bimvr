package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.DrawingMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/getProjectDrawing/{projectid}")
    public Result getProjectDrawing(@PathVariable Integer projectid){
        return new Result(ResultStatusCode.OK,drawingMapper.getprojectdrawing(projectid));
    }

}
