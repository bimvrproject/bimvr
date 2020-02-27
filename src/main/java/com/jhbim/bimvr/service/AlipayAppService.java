package com.jhbim.bimvr.service;



import com.jhbim.bimvr.utils.ResultStatus;

import java.util.Map;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2019/9/10
 */
public interface AlipayAppService {
    /**
     * 支付宝支付统一下单接口(公钥证书方式)
     * @param request
     * @return
     */
    ResultStatus aliPayCertUnifiedOrder(String request);

    /**
     * 支付宝回调(公钥证书方式)
     * @param requestParams
     * @return
     */
    String aliPayCertNotify(Map requestParams);
}
