package com.ruoyi.project.module.activity.controller;

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
import com.ruoyi.project.module.activity.domain.Activity;
import com.ruoyi.project.module.activity.service.IActivityService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 商户活动 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/module/activity")
public class ActivityController extends BaseController
{
    private String prefix = "module/activity";
	
	@Autowired
	private IActivityService activityService;
	
	@RequiresPermissions("module:activity:view")
	@GetMapping()
	public String activity()
	{
	    return prefix + "/activity";
	}
	
	/**
	 * 查询商户活动列表
	 */
	@RequiresPermissions("module:activity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Activity activity)
	{
		startPage();
        List<Activity> list = activityService.selectActivityList(activity);
		return getDataTable(list);
	}
	
	/**
	 * 新增商户活动
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商户活动
	 */
	@RequiresPermissions("module:activity:add")
	@Log(title = "商户活动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Activity activity)
	{		
		return toAjax(activityService.insertActivity(activity));
	}

	/**
	 * 修改商户活动
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Activity activity = activityService.selectActivityById(id);
		mmap.put("activity", activity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商户活动
	 */
	@RequiresPermissions("module:activity:edit")
	@Log(title = "商户活动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Activity activity)
	{		
		return toAjax(activityService.updateActivity(activity));
	}
	
	/**
	 * 删除商户活动
	 */
	@RequiresPermissions("module:activity:remove")
	@Log(title = "商户活动", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(activityService.deleteActivityByIds(ids));
	}
	
}
