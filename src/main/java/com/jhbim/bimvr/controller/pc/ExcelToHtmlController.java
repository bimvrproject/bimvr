package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Meterial;
import com.jhbim.bimvr.dao.entity.pojo.MeterialFolder;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.MeterialFolderMapper;
import com.jhbim.bimvr.dao.mapper.MeterialMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/${version}/excel")
public class ExcelToHtmlController {

    @Resource
    MeterialMapper meterialMapper;
    @Resource
    MeterialFolderMapper meterialFolderMapper;

    /**
     * 根据项目id展示相对应的excel表格
     * @param projectId
     * @return
     */
    @RequestMapping("/getprojectid")
    public Result getprojectid(String projectId){
        if(projectId==null || projectId == "" || projectId.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        return new Result(ResultStatusCode.OK,meterialMapper.getprojectid(projectId));
    }

    /**
     * 将excel转变成html
     * @param addressurl
     * @return
     */
    @GetMapping("/exceltohtml")
    public Result exceltohtml(String addressurl){
//        ExcelToHtml excelToHtml=new ExcelToHtml();
//        String convertByFile = excelToHtml.SubmitPost("http://dcs.yozosoft.com:80/upload", "C:/ftp/"+addressurl, "0");
//        System.out.println(convertByFile);
//        String url= String.valueOf(convertByFile.subSequence(21,86));
//        return  new Result(ResultStatusCode.OK,url);
        String address="https://view.officeapps.live.com/op/view.aspx?src=http://21z9804m0.zicp.vip:8080/";
        String url=address+addressurl;
        return new Result(ResultStatusCode.OK,url);
    }

    /**
     * 拖拽清单批量修改type
     * @param ids
     * @return
     */
    @RequestMapping("/updateexceltype")
    public Result updateexceltype(Integer[] ids){
        if(ids==null || ids.length==0){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        meterialMapper.batchUpdate(ids);
        return new Result(ResultStatusCode.OK,"清单type修改成功");
    }

    /**
     * 修改excel文件的名称
     * @param meterialName
     * @param id
     * @return
     */
    @RequestMapping("/updateExcelName")
    public Result updateExcelName(String meterialName,Integer id){
        if(meterialName.isEmpty()|| id==null){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        meterialMapper.Updateexcelname(meterialName,id);
        return new Result(ResultStatusCode.OK,"Excel名称修改成功");
    }

    /**
     * 拖动excel
     * @param projectid
     * @param foldername
     * @param ids
     * @return
     */
    @RequestMapping("/DragdropExcel")
    public Result DragdropExcel(String projectid,String foldername,Integer[] ids){
        if(projectid.isEmpty() || foldername.isEmpty() || ids.length == 0){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        User user= ShiroUtil.getUser();
        Meterial meterial =new Meterial();
        meterial.setModelId(1);
        meterial.setProjectId(projectid);
        meterial.setUserId(user.getUserId());
        meterial.setFoldername(foldername);
        meterial.setMtime(new Date());
        meterialMapper.insertSelective(meterial);
        MeterialFolder mf=new MeterialFolder();
        for (int i=0;i<ids.length;i++){
            mf.setFoldername(foldername);
            mf.setMeterialId(String.valueOf(ids[i]));
            mf.setUserId(user.getUserId());
            mf.setMftime(meterial.getMtime());
            meterialFolderMapper.insertSelective(mf);
        }
        meterialMapper.batchUpdate(ids);
        return new Result(ResultStatusCode.OK,"增加成功");
    }
}
