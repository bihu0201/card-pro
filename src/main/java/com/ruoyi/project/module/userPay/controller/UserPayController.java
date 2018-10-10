package com.ruoyi.project.module.userPay.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 商户充值 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/module/userPay")
public class UserPayController extends BaseController
{
    private String prefix = "module/userPay";
	
	@Autowired
	private IUserPayService userPayService;
	
	@RequiresPermissions("module:userPay:view")
	@GetMapping()
	public String userPay()
	{
	    return prefix + "/userPay";
	}
	
	/**
	 * 查询商户充值列表
	 */
	@RequiresPermissions("module:userPay:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserPay userPay)
	{
		startPage();
        List<UserPay> list = userPayService.selectUserPayList(userPay);
		return getDataTable(list);
	}
	
	/**
	 * 新增商户充值
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商户充值
	 */
	@RequiresPermissions("module:userPay:add")
	@Log(title = "商户充值", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserPay userPay)
	{		
		return toAjax(userPayService.insertUserPay(userPay));
	}

	/**
	 * 修改商户充值
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		UserPay userPay = userPayService.selectUserPayById(id);
		mmap.put("userPay", userPay);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户充值
	 */
	@RequiresPermissions("module:userPay:edit")
	@Log(title = "商户充值", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserPay userPay)
	{		
		return toAjax(userPayService.updateUserPay(userPay));
	}
	
	/**
	 * 删除商户充值
	 */
	@RequiresPermissions("module:userPay:remove")
	@Log(title = "商户充值", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userPayService.deleteUserPayByIds(ids));
	}
	
}
