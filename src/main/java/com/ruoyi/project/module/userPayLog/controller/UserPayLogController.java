package com.ruoyi.project.module.userPayLog.controller;

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
import com.ruoyi.project.module.userPayLog.domain.UserPayLog;
import com.ruoyi.project.module.userPayLog.service.IUserPayLogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 商户充值记录 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/module/userPayLog")
public class UserPayLogController extends BaseController
{
    private String prefix = "module/userPayLog";
	
	@Autowired
	private IUserPayLogService userPayLogService;
	
	@RequiresPermissions("module:userPayLog:view")
	@GetMapping()
	public String userPayLog()
	{
	    return prefix + "/userPayLog";
	}
	
	/**
	 * 查询商户充值记录列表
	 */
	@RequiresPermissions("module:userPayLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserPayLog userPayLog)
	{
		startPage();
        List<UserPayLog> list = userPayLogService.selectUserPayLogList(userPayLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增商户充值记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商户充值记录
	 */
	@RequiresPermissions("module:userPayLog:add")
	@Log(title = "商户充值记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserPayLog userPayLog)
	{		
		return toAjax(userPayLogService.insertUserPayLog(userPayLog));
	}

	/**
	 * 修改商户充值记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		UserPayLog userPayLog = userPayLogService.selectUserPayLogById(id);
		mmap.put("userPayLog", userPayLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户充值记录
	 */
	@RequiresPermissions("module:userPayLog:edit")
	@Log(title = "商户充值记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserPayLog userPayLog)
	{		
		return toAjax(userPayLogService.updateUserPayLog(userPayLog));
	}
	
	/**
	 * 删除商户充值记录
	 */
	@RequiresPermissions("module:userPayLog:remove")
	@Log(title = "商户充值记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userPayLogService.deleteUserPayLogByIds(ids));
	}
	
}
