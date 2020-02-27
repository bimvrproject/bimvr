package com.jhbim.bimvr.config;

/**
 * @Description: 支付宝支付配置类(公钥证书)
 * @Author: zrk
 * @Date: 2019/9/10
 */
public class ZrkAliPayCertConfig {
    /**
     * 商户私钥
     */
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDvf9eAxb9mEcllsocGInN6GQx7CCw4D/v86amRYesqd1//aQFxUdo7r+sMdJo9+h0dWyW67QzlRLu7GUDX44yd3CmNQzpCEp8MsarkhJ0mThe4o3YK5aIVJQG/9n0EPpUPtw9mrD4IwSq/E3AMya14SJ+76Sl3bLrDcPKOyTX59Xa9s6NGervbCgz4Zj2Ohc2vR69R7oJR0mrQKKnH09hquujA9tTElnnxeT001QtGevepymnGpdq/HpHc6APsODUfDUs6jwHukeeau0YPtUZ8/LxxTiwamIp5YSRTGUTjgs69UXvu6yVZBEdfHg/FPH++bZeWz3cBVA4wrpLlD7nAgMBAAECggEAdOepMJe1UxxKJkUE3KG5x4qtPs+2wr58fkXMYEfa+ZFTCTzezHAfyjHyzK75dERpds6sqdBHfG6Q+ouzp6an3Ii44gS5jCDbWBMG0UEyN12v9CM+k9E6J4hGQ1/O3RIj0ZZmghQ/bjJE9iYmKKQ7ebJqlhG6HaX6GzN/xnltXFkGy3+EfVGZQkmTqDyGr9ta2ywxLpqJTowWdl1FkOo76w12REQZnoHlrEMrn2KHlTRJjObO6x2f4bfkoisJb0RlHPYQye+faMCCB4I/ehGQqsTGXz1O/WPeNODpx6cgLW/Oo6D6g2LlhiSVQUAtu48qaOVr4VdIzpPLDIavPYG4YQKBgQDcQzbZL38e2psiTBZ3QFDo1sc8NBMxU71uRSFviaN6HGSEO/76s9pHEG+IK63o1LclGEJ+ff1aZ0y+P9cuTGYq3dHJHhR0OeNuT1cL77KpTTTfB8PKvp6SYD2bQvu1J1LPnDroUcgMt4nQsKjysyCRG+YooZB34WoJH3tA1FvA1QKBgQCZHgRP4jwyWRZNHv5AfMO3pt8QSU7FImbHHxxY9lwrqp6YfELiQ/695D17rRrk9q4/wMZXBaHP3dAj3x1V2CYnwcICRSvFaGImTstyCLnDZs70e+xFze8d+xE6xlf6Qq+RP9LUxHiqRKxDQHvE0A7Dj5k2DlZpVgYP3JPSrwP+ywKBgCl8n4AY6iCtnDrBihm8C4FPswcr59GKrEtwyfNwQVJmtqgRhN1DswFfnyJO19ra9gQOpbedk3LLsjDDZYxa54N7dGAlQkUCAkxstUCSTVyGJkc79yzg6o03g2MTIsXkOjzQeGf5v2xlxZeBLtFH3AJ49skAZW2vD4HYZCa52O5hAoGATaOhzhvKKEFyBHL1ux9IYoQz0V9KmL0j3k3RIu7wt5eRSYKgq3iM4B6ualrRK05F9r+6EoAhl3RCyR2mE1RBrKEbzejjE/sk4YXMtSrlfsZPpDp+CLsuR0Z6axINpDFSMtVpA0gIY1Xk/0jgOWDCS7kxl+5XNJxRAJP5RLhHz1cCgYAnEPRK/HkRMKBu9aQIF6rtTeuVPzt6wiGUS5vWvbJHk4oqhiDrYyRT9aU9EYmjfPlGFejaj+lFNDyjgM4qTemWz9nb4ecNSjjho7FO5XGWM64oQvWiM5o9441Vtpa+qgADq+e5Fkp5sF7yvSbFdmqEsIVl9XBE6OIx+t6mASpJig==";

    /**
     * 支付宝APPID
     */
    public static String APPID = "2021001131601667";

    /**
     * 应用公钥证书路径
     */
    public static String app_cert_path = "CRT/appCertPublicKey_2021001131601667.crt";

    /**
     * 支付宝公钥证书文件路径
     */
    public static String alipay_cert_path = "CRT/alipayCertPublicKey_RSA2.crt";

    /**
     * 支付宝CA根证书文件路径
     */
    public static String alipay_root_cert_path = "CRT/alipayRootCert.crt";

    /**
     * 请求网关
     */
    public static String SERVERURL = "https://openapi.alipay.com/gateway.do";

    /**
     * 回调地址
     */
    public static String ALIPAY_NOTIFY_URL = "http://21z9804m0.zicp.vip:9090/thirdPay/alipayCertNotify";

    /**
     * 字符集
     */
    public static String CHARSET = "utf-8";

    /**
     * 签名类型
     */
    public static String SIGN_TYPE = "RSA2";

    /**
     * 格式
     */
    public static String FORMAT = "json";
}

