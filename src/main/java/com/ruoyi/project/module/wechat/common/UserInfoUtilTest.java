package com.ruoyi.project.module.wechat.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UserInfoUtilTest {
    //获取code的请求地址
    public static String Get_Code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STAT#wechat_redirect";
    //替换字符串
    public static String getCode(String APPID, String REDIRECT_URI,String SCOPE) {
        return String.format(Get_Code,APPID,REDIRECT_URI,SCOPE);
    }

    public static void main(String[] args) {
        String  REDIRECT_URI = "http://;/index";
        String SCOPE = "snsapi_userinfo";
        //appId
        String appId = "wx80aeb606a734f7f4";
        HttpHeaders headers = new HttpHeaders();
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        String getCodeUrl = getCode(appId, REDIRECT_URI, SCOPE);
        System.out.println("getCodeUrl:"+getCodeUrl);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        RestTemplate client = new RestTemplate();
        //  执行HTTP请求
        ResponseEntity<String> response = client.exchange(getCodeUrl, HttpMethod.GET, requestEntity, String.class);
   //  输出结果
        System.out.println(response.getBody());
    }
}
