package com.ruoyi.project.module.wechat.service;

import com.ruoyi.project.module.wechat.common.RestTemplateUtil;
import org.springframework.web.client.RestTemplate;

public class UserInfoServiceImpl implements IUserInfoService {

    RestTemplate restT = new RestTemplate();
    @Override
    public String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
        RestTemplateUtil.getRestTemplate();
        return null;
    }
}
