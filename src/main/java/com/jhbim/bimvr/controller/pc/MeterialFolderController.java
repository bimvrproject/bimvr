package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.Meterial;
import com.jhbim.bimvr.dao.entity.pojo.MeterialFolder;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.MeterialFolderMapper;
import com.jhbim.bimvr.dao.mapper.MeterialMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${version}/MeterialFolder")
public class MeterialFolderController {
    @Resource
    MeterialFolderMapper meterialFolderMapper;
    @Resource
    MeterialMapper meterialMapper;

    /**
     * 根据日期和文件夹名称查询文件夹下面的文件
     * @param mftime 时间
     * @param foldername    文件夹
     * @return
     * @throws ParseException
     */
    @RequestMapping("/listAll")
    public Result listAll(String mftime,String foldername) throws ParseException {
        if(mftime.isEmpty() || foldername.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        List<MeterialFolder> mflist=meterialFolderMapper.listAll(mftime,foldername);
        List<Meterial> meterialList=new ArrayList<>();
        for (MeterialFolder meterialFolder:mflist) {
            Meterial meterial= meterialMapper.selectByPrimaryKey(Integer.valueOf(meterialFolder.getMeterialId()));
            meterialList.add(meterial);
        }
        return new Result(ResultStatusCode.OK,meterialList);
    }

    /**
     * 根据创建时间修改文件夹的名称
     * @param foldername 文件夹
     * @param mftime    时间
     * @return
     */
    @RequestMapping("/updatefolder")
    public Result updatefolder(String foldername,String mftime){
        if(foldername.isEmpty() || mftime.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        //修改清单表和它的关联关系表
        meterialMapper.meterialupdatefolder(foldername,mftime);
        meterialFolderMapper.updatefolder(foldername,mftime);
        return new Result(ResultStatusCode.OK,"文件夹修改成功");
    }
}
