package com.ruoyi.project.module.payLevel.controller;

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
import com.ruoyi.project.module.payLevel.domain.PayLevel;
import com.ruoyi.project.module.payLevel.service.IPayLevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 充值等级 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/module/payLevel")
public class PayLevelController extends BaseController
{
    private String prefix = "module/payLevel";
	
	@Autowired
	private IPayLevelService payLevelService;
	
	@RequiresPermissions("module:payLevel:view")
	@GetMapping()
	public String payLevel()
	{
	    return prefix + "/payLevel";
	}
	
	/**
	 * 查询充值等级列表
	 */
	@RequiresPermissions("module:payLevel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayLevel payLevel)
	{
		startPage();
        List<PayLevel> list = payLevelService.selectPayLevelList(payLevel);
		return getDataTable(list);
	}
	
	/**
	 * 新增充值等级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存充值等级
	 */
	@RequiresPermissions("module:payLevel:add")
	@Log(title = "充值等级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayLevel payLevel)
	{		
		return toAjax(payLevelService.insertPayLevel(payLevel));
	}

	/**
	 * 修改充值等级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PayLevel payLevel = payLevelService.selectPayLevelById(id);
		mmap.put("payLevel", payLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存充值等级
	 */
	@RequiresPermissions("module:payLevel:edit")
	@Log(title = "充值等级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayLevel payLevel)
	{		
		return toAjax(payLevelService.updatePayLevel(payLevel));
	}
	
	/**
	 * 删除充值等级
	 */
	@RequiresPermissions("module:payLevel:remove")
	@Log(title = "充值等级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(payLevelService.deletePayLevelByIds(ids));
	}
	
}
