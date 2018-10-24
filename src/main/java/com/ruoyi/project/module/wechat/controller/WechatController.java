package com.ruoyi.project.module.wechat.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.wechat.domain.WeChatAppLoginReq;
import com.ruoyi.project.module.wechat.service.IWeChatAppLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
	/**
	 * 获取用户信息
	 */
	///Post api/wechat/getwechat?code=***&iv=***&encryptedData=***
	@PostMapping( "/getToken")
	@ResponseBody
	public AjaxResult getToken()
	{
		Map map = weChatAppLoginService.getToken();
		return ok(map);
	}

	/**
	 * 获取商户小程序二维码
	 */
	///Post api/wechat/getwechat?code=***&iv=***&encryptedData=***
	@PostMapping( "/getWechatImg")
	@ResponseBody
	public AjaxResult getWechatImg(HttpServletRequest request, Integer userId)
	{
		Map map = new HashMap();
		try{
			//Map imgMap = weChatAppLoginService.getminiqrQr(request,userId);
			 String imgMap = weChatAppLoginService.GetPostUrl(userId);
			System.out.println(imgMap);

			map.put("wechatImg",imgMap);
		}catch(Exception e){}

		return ok(map);
	}

	
}
