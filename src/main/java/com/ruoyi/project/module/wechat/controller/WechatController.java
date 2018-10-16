package com.ruoyi.project.module.wechat.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.wechat.domain.WeChatAppLoginReq;
import com.ruoyi.project.module.wechat.service.IWeChatAppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信 信息操作处理
 * 
 * @author snailever
 * @date 2018-10-11
 */
@Controller
@RequestMapping("/api/wechat")
public class WechatController extends BaseController
{
    private String prefix = "/api/wechat";
    @Autowired
	IWeChatAppLoginService weChatAppLoginService;
	/**
	 * 获取用户信息
	 */
	///Post api/wechat/getwechat?code=***&iv=***&encryptedData=***
	@PostMapping( "/getwechat")
	@ResponseBody
	public AjaxResult getWechat(WeChatAppLoginReq weChatAppLoginReq)
	{
		 Map map = weChatAppLoginService.login(weChatAppLoginReq);
		 return ok(map);
	}
	
}
