package com.ruoyi.project.module.award.controller;

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
import com.ruoyi.project.module.award.domain.Award;
import com.ruoyi.project.module.award.service.IAwardService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 奖项 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/module/award")
public class AwardController extends BaseController
{
    private String prefix = "module/award";
	
	@Autowired
	private IAwardService awardService;
	
	@RequiresPermissions("module:award:view")
	@GetMapping()
	public String award()
	{
	    return prefix + "/award";
	}
	
	/**
	 * 查询奖项列表
	 */
	@RequiresPermissions("module:award:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Award award)
	{
		startPage();
        List<Award> list = awardService.selectAwardList(award);
		return getDataTable(list);
	}
	
	/**
	 * 新增奖项
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存奖项
	 */
	@RequiresPermissions("module:award:add")
	@Log(title = "奖项", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Award award)
	{		
		return toAjax(awardService.insertAward(award));
	}

	/**
	 * 修改奖项
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Award award = awardService.selectAwardById(id);
		mmap.put("award", award);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存奖项
	 */
	@RequiresPermissions("module:award:edit")
	@Log(title = "奖项", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Award award)
	{		
		return toAjax(awardService.updateAward(award));
	}
	
	/**
	 * 删除奖项
	 */
	@RequiresPermissions("module:award:remove")
	@Log(title = "奖项", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(awardService.deleteAwardByIds(ids));
	}
	
}
