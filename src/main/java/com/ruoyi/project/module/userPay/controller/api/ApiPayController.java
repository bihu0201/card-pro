package com.ruoyi.project.module.userPay.controller.api;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.award.domain.Award;
import com.ruoyi.project.module.award.service.IAwardService;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import com.ruoyi.project.module.userPayLog.domain.UserPayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 奖项 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/api/userpay")
@RestController
public class ApiPayController extends BaseController
{
    private String prefix = "module/award";

	@Autowired
	private IUserPayService userPayService;


	/**
	 * 充值
	 */
	@GetMapping("/pay")
	public AjaxResult pay(UserPayLog userPayLog)
	{
          userPayService.pay(userPayLog);
          return ok("0","充值成功！");
	}
}
