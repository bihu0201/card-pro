package com.ruoyi.project.module.wechat.common;

import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {
    //获取code的请求地址
    public static String Get_Code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STAT#wechat_redirect";

    private static RestTemplate client=null;

    static {
                 try {
                       if(client==null){
                           client=new RestTemplate();
                       }
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
    }
    //将获得的数据库与java的链接返回（返回的类型为Connection）
    public static RestTemplate getRestTemplate  (){
           return client;
    }
    //替换字符串
    public static String getCode(String APPID, String REDIRECT_URI,String SCOPE) {
        return String.format(Get_Code,APPID,REDIRECT_URI,SCOPE);
    }


}
