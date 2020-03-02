package com.jhbim.bimvr.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.jhbim.bimvr.config.ZrkAliPayCertConfig;
import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.service.AlipayAppService;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ResultStatus;
import com.jhbim.bimvr.utils.ResultStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class AlipayAppServiceImpl implements AlipayAppService {
    public final static Logger log = LoggerFactory.getLogger(AlipayAppServiceImpl.class);
    /**
     * 文件跟路径
     */
    @Value("${alipay_cert_file_path}")
    private volatile String AliPay_CERT_FILE_PATH;
    @Resource
    IdWorker idWorker;
    @Resource
    ProjectMapper projectMapper;

    private final static  String attachRegex = "BIMAPP";
    @Override
    public ResultStatus aliPayCertUnifiedOrder(BigDecimal orderAmount,String productNum,String userphone) {
        if (orderAmount==null || productNum.isEmpty() || userphone.isEmpty()){
            return  new ResultStatus(ResultStatusEnum.ERROR.getStatus(),"参数解析失败");
        }
        /**
         * 定义变量，可以根据实际需求获取并生成相应变量，变量值仅供参考
         */
        Project project = projectMapper.selectByPrimaryKey(productNum);
        String outTradeNo = idWorker.nextId()+"";
        String projectbody=project.getProjectName();
        /**
         * 拼接自己的业务参数
         *  例如 用户id + 商品id + 订单id
         *  用指定分隔符进行拼接，以便后续做业务处理
         */
        String passBackParams = userphone + attachRegex + productNum + attachRegex + outTradeNo;

        /**
         * 以下部分可以共用，复制即可
         * *********************************************************
         */
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request1 = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(projectbody);
        model.setSubject(projectbody);
        model.setOutTradeNo(outTradeNo);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(orderAmount + "");
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setPassbackParams(URLEncoder.encode(passBackParams));
        request1.setBizModel(model);
        request1.setNotifyUrl(ZrkAliPayCertConfig.ALIPAY_NOTIFY_URL);
        log.info(">>>>支付宝统一下单接口请求参数：" + model.getBody() + "," + model.getOutTradeNo() + "," + model.getTotalAmount());

        /**实例化客户端*/
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(ZrkAliPayCertConfig.SERVERURL);
        certAlipayRequest.setAppId(ZrkAliPayCertConfig.APPID);
        certAlipayRequest.setPrivateKey(ZrkAliPayCertConfig.APP_PRIVATE_KEY);
        certAlipayRequest.setFormat(ZrkAliPayCertConfig.FORMAT);
        certAlipayRequest.setCharset(ZrkAliPayCertConfig.CHARSET);
        certAlipayRequest.setSignType(ZrkAliPayCertConfig.SIGN_TYPE);
        certAlipayRequest.setCertPath(AliPay_CERT_FILE_PATH + ZrkAliPayCertConfig.app_cert_path);
        certAlipayRequest.setAlipayPublicCertPath(AliPay_CERT_FILE_PATH + ZrkAliPayCertConfig.alipay_cert_path);
        certAlipayRequest.setRootCertPath(AliPay_CERT_FILE_PATH + ZrkAliPayCertConfig.alipay_root_cert_path);
        try {
            //构造client
            AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request1);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            log.info(">>>生成调用支付宝参数" + response.getBody());
            /**************************************************************/
            ResultStatus resultStatus = new ResultStatus(ResultStatusEnum.SUCCESS.getStatus());
            resultStatus.setData(response.getBody());
            return resultStatus;
        } catch (AlipayApiException e) {
            log.error(e.getMessage(), e);
        }
        return new ResultStatus(ResultStatusEnum.ERROR.getStatus(),"支付宝下订单失败");
    }

    @Override
    public String aliPayCertNotify(Map requestParams) {
        log.info(">>>支付宝回调参数：" + requestParams);
        Map<String,String> params = new HashMap<>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info(">>>支付宝回调参数解析：" + params);
        try {
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean flag = AlipaySignature.rsaCertCheckV1(
                    params,
                    AliPay_CERT_FILE_PATH+ZrkAliPayCertConfig.alipay_cert_path,
                    ZrkAliPayCertConfig.CHARSET,
                    ZrkAliPayCertConfig.SIGN_TYPE);
            if(flag) {
                log.info(">>>支付宝回调签名认证成功");
                //商户订单号
                String out_trade_no = params.get("out_trade_no");
                //交易状态
                String trade_status = params.get("trade_status");
                //交易金额
                String amount = params.get("total_amount");
                //商户app_id
                String app_id = params.get("app_id");

                if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
                    /**
                     * 自己的业务处理
                     */
                } else {
                    log.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",trade_status,params);
                }
            } else {
                log.info("支付宝回调签名认证失败，signVerified=false, params:{}", params);
                return "failure";
            }
        } catch (Exception e){
            log.error(e.getMessage(), e);
            log.info("支付宝回调签名认证失败，signVerified=false, params:{}", params);
            return "failure";
        }
        return "success";//请不要修改或删除
    }
}
