package com.ruoyi.project.module.wechat.service;


import java.io.*;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.module.util.CreateImgUtil;
import com.ruoyi.project.module.util.HmacUtil;
import com.ruoyi.project.module.util.MD5Util;
import com.ruoyi.project.module.util.MD5Utils;
import com.ruoyi.project.module.wechat.domain.WeChatAppLoginReq;
import com.ruoyi.project.module.wxpay.sdk.WXMyConfigUtil;
import org.jose4j.base64url.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Project Name: sns-business
 * File Name: WeAppLoginBiz.java
 * Date: 2017年1月13日下午1:06:17
 * Author: zhao.zhibo@xiaoyi.com
 * Explain: 微信小程序登录
 */
@Component
public class WeChatAppLoginServiceImpl implements IWeChatAppLoginService
{
    private static final Logger logger = LoggerFactory.getLogger(WeChatAppLoginServiceImpl.class);

    //@Autowired
    //private UserInfoBiz userInfoBiz;

    public static boolean initialized = false;

    private static final String APPID = "wxb19d151f69b2331d";
    private static final String SECRET = "96858a736861b8f2d3b1ac4d2d10f959";


    /**
     *  支付结果通知
     * @param notifyData    异步通知后的XML数据
     * @return
     */
    @Override
    public String payBack(String notifyData) {
        WXMyConfigUtil config = null;
        try {
            config = new WXMyConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack="";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(notifyData);         // 转换成map
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                String  return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//订单号

                if(return_code.equals("SUCCESS")){
                    if(out_trade_no!=null){
                        //处理订单逻辑
                        /**
                         *          更新数据库中支付状态。
                         *          特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功。
                         *          此处需要判断一下。后面写入库操作的时候再写
                         *
                         */

                        System.err.println(">>>>>支付成功");

                        logger.info("微信手机支付回调成功订单号:{}",out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }else {
                        logger.info("微信手机支付回调失败订单号:{}",out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }

                }
                return xmlBack;
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                logger.error("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return xmlBack;
            }
        } catch (Exception e) {
            logger.error("手机支付回调通知失败",e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        return xmlBack;
    }


    @Override
    public Map<String, String> dounifiedOrder(String attach, String out_trade_no, String total_fee,String spbill_create_ip,int type) throws Exception {
        Map<String, String> fail = new HashMap<>();
        WXMyConfigUtil config = new WXMyConfigUtil();
        WXPay wxpay = new WXPay(config);
        Map<String, String> data = new HashMap<String, String>();
        //      data.put("appid", config.getAppID());
//        data.put("mch_id", config.getMchID());
//        data.put("nonce_str",WXPayUtil.generateNonceStr());
//        data.put("nonce_str","6128be982a7f40daa930025dedd1a90d");
        String body="订单支付";
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("spbill_create_ip",spbill_create_ip);
        //异步通知地址（请注意必须是外网）
        data.put("notify_url", "http://www.baidu.com");

        data.put("trade_type", "APP");
        data.put("attach", attach);
//        data.put("sign", md5Util.getSign(data));
        StringBuffer url= new StringBuffer();
        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            String returnCode = resp.get("return_code");    //获取返回码
            String returnMsg = resp.get("return_msg");

            if("SUCCESS".equals(returnCode)){       //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
                String resultCode = (String)resp.get("result_code");
                String errCodeDes = (String)resp.get("err_code_des");
                System.out.print(errCodeDes);
                if("SUCCESS".equals(resultCode)){
                    //获取预支付交易回话标志
                    Map<String,String> map = new HashMap<>();
                    String prepay_id = resp.get("prepay_id");
                    String signType = "MD5";
                    map.put("prepay_id",prepay_id);
                    map.put("signType",signType);
                    String sign = MD5Util.getSign(map);
                    resp.put("realsign",sign);
                    url.append("prepay_id="+prepay_id+"&signType="+signType+ "&sign="+sign);
                    return resp;
                }else {
                    logger.info("订单号：{},错误信息：{}",out_trade_no,errCodeDes);
                    url.append(errCodeDes);
                }
            }else {
                logger.info("订单号：{},错误信息：{}",out_trade_no,returnMsg);
                url.append(returnMsg);
            }

        } catch (Exception e) {
            System.out.println("aaaaaaaaaaaaa");
            System.out.println(e);
            logger.info(e.getMessage());
        }
        return fail;
    }




    public Map<String,Object> login(WeChatAppLoginReq req)
    {
        Map userInfo = new HashMap();
        //获取 session_key 和 openId
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+req.getCode()+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,  String.class);

        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();

        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK)
        {
            String sessionData = responseEntity.getBody();
            logger.info("sessionData = "+ sessionData);
            JSONObject jsonObj = JSON.parseObject(sessionData);
            String openId = jsonObj.getString("openid");
            String sessionKey = jsonObj.getString("session_key");

            String signature = HmacUtil.SHA1(req.getRawData()+sessionKey);
            if(!signature.equals(req.getSignature()))
            {
                 userInfo.put("openId",openId);
                 userInfo.put("sessionKey",sessionKey);
                return userInfo;
            }
        }else
        {
            logger.error("error");
            //throw new SystemException(ResponseMsg.WECHAT_LOGIN_CODE_ERROR);
        }
        return userInfo;
    }



    private byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void initialize(){
        if (initialized) return;
       // Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

    public Map<String,Object> getToken(){
        Map userInfo = new HashMap();
        //获取 session_key 和 openId
        String url = "https://api.weixin.qq.com/cgi-bin/token?appid="+APPID+"&secret="+SECRET+"&grant_type=client_credential";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,  String.class);

        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();

        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK)
        {
            String sessionData = responseEntity.getBody();

            JSONObject jsonObj = JSON.parseObject(sessionData);
            String access_token = jsonObj.getString("access_token");
            String expires_in = jsonObj.getString("expires_in");
            userInfo.put("access_token",access_token);
            userInfo.put("expires_in",expires_in);
            logger.info("access_token = "+ access_token);
            return userInfo;
        }
        return null;
    }




    public Map getminiqrQr(HttpServletRequest request, Integer userId) {
        Map map = new HashMap();
        String accessToken = "";
        Map tokenMap  = this.getToken();
        if(tokenMap.get("access_token")!=null) {
              accessToken = (String) tokenMap.get("access_token");
        }
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
            Map<String,Object> param = new HashMap<>();
            param.put("scene", userId);
            param.put("page", "pages/lottery/lottery");
            param.put("width", 430);
//            param.put("auto_color", false);
//            Map<String,Object> line_color = new HashMap<>();
//            line_color.put("r", 0);
//            line_color.put("g", 0);
//            line_color.put("b", 0);
//            param.put("line_color", line_color);
            logger.info("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            logger.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
         //   logger.info(Base64.encodeBase64String(result));//      logger.info(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dateStr = df.format(System.currentTimeMillis());// new Date()为获取当前系统时间，也可使用当前时间戳
            String fileName= MD5Utils.MD5Encode(dateStr,"utf8");

            File file = new File(RuoYiConfig.getProfile()+fileName+".png");
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            map.put("code",file.getPath());
            map.put("code_url",fileName+".png");
            return map;
        } catch (Exception e) {
            logger.error("调用小程序生成微信永久小程序码URL接口异常",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String GetPostUrl(Integer userId) throws Exception {
        //String result = HttpRequest.sendPost("http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token, path);
        System.out.println(userId);
        String accessToken = "";
        Map tokenMap  = this.getToken();
        if(tokenMap.get("access_token")!=null) {
            accessToken = (String) tokenMap.get("access_token");
        }
        String url ="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", "pages/lottery/lottery?userid="+userId);//你二维码中跳向的地址
        map.put("width", "430");//图片大小
        map.put("scene",userId);
        String  json = JSON.toJSONString(map);
        System.out.println(json);
        String  res= CreateImgUtil.httpPostWithJSON(url
                + accessToken, json.toString(),userId);
        System.out.println(res);
        return res;
    }
}
