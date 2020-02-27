package com.jhbim.bimvr.controller.pc.alipay;

import com.jhbim.bimvr.service.AlipayAppService;
import com.jhbim.bimvr.utils.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description: 第三方支付接口
 * @Author: zrk
 * @Date: 2019/9/10
 */
@Slf4j
@RestController
@RequestMapping("/alipayapp")
public class AilipayAppController {

    @Resource
    private AlipayAppService alipayAppService;


    /**
     * 支付宝下订单接口(公钥证书方式)
     *      参数可根据自己的业务需求传相应参数
     * @param request
     * @return
     */
    @PostMapping("/aliPayCertUnifiedOrder")
    public ResultStatus aliPayCertUnifiedOrder(String request){
        return alipayAppService.aliPayCertUnifiedOrder(request);
    }

    /**
     * 支付宝回调接口(公钥证书方式)
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/aliPayCertNotify")
    @ResponseBody
    public String aliPayCertNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取支付宝POST过来反馈信息
        Map requestParams = request.getParameterMap();
        return alipayAppService.aliPayCertNotify(requestParams);
    }
}

