package com.ruoyi.project.module.userTag.controller;

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
import com.ruoyi.project.module.userTag.domain.UserTag;
import com.ruoyi.project.module.userTag.service.IUserTagService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 用户标签 信息操作处理
 * 
 * @author snailever
 * @date 2018-10-28
 */
@Controller
@RequestMapping("/module/userTag")
public class UserTagController extends BaseController
{
    private String prefix = "module/userTag";
	
	@Autowired
	private IUserTagService userTagService;
	
	@RequiresPermissions("module:userTag:view")
	@GetMapping()
	public String userTag()
	{
	    return prefix + "/userTag";
	}
	
	/**
	 * 查询用户标签列表
	 */
	@RequiresPermissions("module:userTag:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserTag userTag)
	{
		startPage();
        List<UserTag> list = userTagService.selectUserTagList(userTag);
		return getDataTable(list);
	}
	
	/**
	 * 新增用户标签
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户标签
	 */
	@RequiresPermissions("module:userTag:add")
	@Log(title = "用户标签", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserTag userTag)
	{		
		return toAjax(userTagService.insertUserTag(userTag));
	}

	/**
	 * 修改用户标签
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		UserTag userTag = userTagService.selectUserTagById(id);
		mmap.put("userTag", userTag);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户标签
	 */
	@RequiresPermissions("module:userTag:edit")
	@Log(title = "用户标签", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserTag userTag)
	{		
		return toAjax(userTagService.updateUserTag(userTag));
	}
	
	/**
	 * 删除用户标签
	 */
	@RequiresPermissions("module:userTag:remove")
	@Log(title = "用户标签", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userTagService.deleteUserTagByIds(ids));
	}
	
}
