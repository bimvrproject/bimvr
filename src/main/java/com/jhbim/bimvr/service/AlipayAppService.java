package com.jhbim.bimvr.service;



import com.jhbim.bimvr.utils.ResultStatus;

import java.math.BigDecimal;
import java.util.Map;


public interface AlipayAppService {
    /**
     * 支付宝支付统一下单接口(公钥证书方式)
     * @param orderAmount   订单金额
     * @param productNum    商品编号
     * @param userphone     用户手机号
     * @return
     */
    ResultStatus aliPayCertUnifiedOrder(BigDecimal orderAmount,String productNum,String userphone);

    /**
     * 支付宝回调(公钥证书方式)
     * @param requestParams
     * @return
     */
    String aliPayCertNotify(Map requestParams);
}
