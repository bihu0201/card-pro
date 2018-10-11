package com.ruoyi.project.module.memberAward.controller;

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
import com.ruoyi.project.module.memberAward.domain.MemberAward;
import com.ruoyi.project.module.memberAward.service.IMemberAwardService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 会员抽奖 信息操作处理
 * 
 * @author snailever
 * @date 2018-10-11
 */
@Controller
@RequestMapping("/module/memberAward")
public class MemberAwardController extends BaseController
{
    private String prefix = "module/memberAward";
	
	@Autowired
	private IMemberAwardService memberAwardService;
	
	@RequiresPermissions("module:memberAward:view")
	@GetMapping()
	public String memberAward()
	{
	    return prefix + "/memberAward";
	}
	
	/**
	 * 查询会员抽奖列表
	 */
	@RequiresPermissions("module:memberAward:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberAward memberAward)
	{
		startPage();
        List<MemberAward> list = memberAwardService.selectMemberAwardList(memberAward);
		return getDataTable(list);
	}
	
	/**
	 * 新增会员抽奖
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员抽奖
	 */
	@RequiresPermissions("module:memberAward:add")
	@Log(title = "会员抽奖", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberAward memberAward)
	{		
		return toAjax(memberAwardService.insertMemberAward(memberAward));
	}

	/**
	 * 修改会员抽奖
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberAward memberAward = memberAwardService.selectMemberAwardById(id);
		mmap.put("memberAward", memberAward);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员抽奖
	 */
	@RequiresPermissions("module:memberAward:edit")
	@Log(title = "会员抽奖", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberAward memberAward)
	{		
		return toAjax(memberAwardService.updateMemberAward(memberAward));
	}
	
	/**
	 * 删除会员抽奖
	 */
	@RequiresPermissions("module:memberAward:remove")
	@Log(title = "会员抽奖", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberAwardService.deleteMemberAwardByIds(ids));
	}
	
}
