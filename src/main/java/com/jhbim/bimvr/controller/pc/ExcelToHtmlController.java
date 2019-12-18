package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.MeterialMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ExcelToHtml;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${version}/excel")
public class ExcelToHtmlController {

    @Resource
    MeterialMapper meterialMapper;

    /**
     * 根据项目id展示相对应的excel表格
     * @param projectId
     * @return
     */
    @RequestMapping("/getprojectid/{projectId}")
    public Result getprojectid(@PathVariable Integer projectId){
        return new Result(ResultStatusCode.OK,meterialMapper.getprojectid(projectId));
    }

    /**
     * 将excel转变成html
     * @param addressurl
     * @return
     */
    @GetMapping("/exceltohtml")
    public Result exceltohtml(String addressurl){
        ExcelToHtml excelToHtml=new ExcelToHtml();
        String convertByFile = excelToHtml.SubmitPost("http://dcs.yozosoft.com:80/upload", "C:/ftp/"+addressurl, "0");
        System.out.println(convertByFile);
        String url= String.valueOf(convertByFile.subSequence(21,86));
        return  new Result(ResultStatusCode.OK,url);
    }
}
