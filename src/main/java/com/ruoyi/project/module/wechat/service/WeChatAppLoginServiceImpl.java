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


import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.module.util.CreateImgUtil;
import com.ruoyi.project.module.util.HmacUtil;
import com.ruoyi.project.module.util.MD5Utils;
import com.ruoyi.project.module.wechat.domain.WeChatAppLoginReq;
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
        map.put("scene","userid="+userId);
        String  json = JSON.toJSONString(map);
        System.out.println(json);
        String  res= CreateImgUtil.httpPostWithJSON(url
                + accessToken, json.toString(),userId);
        System.out.println(res);
        return res;
    }
}
