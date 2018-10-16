package com.ruoyi.project.module.wechat.service;

import com.ruoyi.project.module.wechat.domain.WeChatAppLoginReq;

import java.util.Map;

public interface IWeChatAppLoginService {
    public Map<String,Object> login(WeChatAppLoginReq req);
}
