package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.BuyModelRecord;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserWallet;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.BuyModelRecordMapper;
import com.jhbim.bimvr.dao.mapper.UserWalletMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/${version}/userwallet")
public class UserWalletController {
    @Resource
    UserWalletMapper userWalletMapper;
    @Resource
    BuyModelRecordMapper buyModelRecordMapper;
    @RequestMapping("/money")
    public Result money(BigDecimal price, String sellphone, String modelid){
        User user = ShiroUtil.getUser();
        UserWallet uw =userWalletMapper.selectByuserphone(user.getPhone());
        if(uw.getMoney().intValue()<price.intValue()){
            return new Result(ResultStatusCode.FAIL,"余额不足调用支付宝或微信接口");
        }
        UserWallet userWallet = new UserWallet();
        userWallet.setMoney(price);
        userWallet.setUserphone(user.getPhone());
        int i =userWalletMapper.updatereducemoney(userWallet);
        if(i>0){
            userWallet.setMoney(price);
            userWallet.setUserphone(sellphone);
            int j =  userWalletMapper.updateplusmoney(userWallet);
            if(j>0){
                BuyModelRecord buyModelRecord = new BuyModelRecord();
                buyModelRecord.setSelluserphone(sellphone);
                buyModelRecord.setBuyuserphone(user.getPhone());
                buyModelRecord.setModelid(modelid);
                buyModelRecord.setPrice(price);
                buyModelRecord.setButtime(new Date());
                buyModelRecordMapper.insertSelective(buyModelRecord);
            }
        }
        return new Result(ResultStatusCode.OK,"购买成功...");
    }
}
