package com.ruoyi.project.module.wechat.service;

import com.ruoyi.project.module.wechat.domain.WeChatAppLoginReq;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IWeChatAppLoginService {
    public Map<String,Object> login(WeChatAppLoginReq req);
    public Map<String,Object> getToken( );
    public Map getminiqrQr(HttpServletRequest request, Integer userId);
    public String GetPostUrl(Integer userId)throws Exception;
    public String payBack(String notifyData);
    public Map<String, String> dounifiedOrder(String attach, String out_trade_no, String total_fee,String spbill_create_ip,int type) throws Exception;
}
