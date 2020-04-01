package com.jhbim.bimvr.controller;

import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/Vipuser")
public class VipUserController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("/selectvip")
    public Result selectvip(String phone){
        return new Result(ResultStatusCode.OK,userMapper.selectByPrimaryKey(phone));
    }

    @RequestMapping("/close")
    public void close(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
            Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
            Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
