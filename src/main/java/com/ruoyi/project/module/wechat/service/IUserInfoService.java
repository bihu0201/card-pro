package com.ruoyi.project.module.wechat.service;

public interface IUserInfoService {
     String getCode(String APPID, String REDIRECT_URI,String SCOPE);
}
