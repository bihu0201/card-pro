package com.ruoyi.project.module.common;

/**
 * 微信支付配置文件
 * @author chenp
 *
 */
public class WeChatConfig {

    /**
     * 微信服务号APPID
     */
    public static String APPID="wxb19d151f69b2331d";
    /**
     * 微信支付的商户号
     */
    public static String MCHID="1517022001";
    /**
     * 微信支付的API密钥
     */
    public static String APIKEY="b47851ccda0411e88e9800163e087935";
    /**
     * 微信支付成功之后的回调地址【注意：当前回调地址必须是公网能够访问的地址】
     */
    public static String WECHAT_NOTIFY_URL_PC="https://wechat.gsxmkj.com//module/userPay/bizook";
    /**
     * 微信统一下单API地址
     */
    public static String UFDODER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * true为使用真实金额支付，false为使用测试金额支付（1分）
     */
    public static String WXPAY="";

}
