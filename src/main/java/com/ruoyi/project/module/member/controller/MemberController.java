package com.ruoyi.project.module.member.controller;

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
import com.ruoyi.project.module.member.domain.Member;
import com.ruoyi.project.module.member.service.IMemberService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 会员 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/module/member")
public class MemberController extends BaseController
{
    private String prefix = "module/member";
	
	@Autowired
	private IMemberService memberService;
	
	@RequiresPermissions("module:member:view")
	@GetMapping()
	public String member()
	{
	    return prefix + "/member";
	}
	
	/**
	 * 查询会员列表
	 */
	@RequiresPermissions("module:member:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Member member)
	{
		startPage();
        List<Member> list = memberService.selectMemberList(member);
		return getDataTable(list);
	}
	
	/**
	 * 新增会员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员
	 */
	@RequiresPermissions("module:member:add")
	@Log(title = "会员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Member member)
	{		
		return toAjax(memberService.insertMember(member));
	}

	/**
	 * 修改会员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Member member = memberService.selectMemberById(id);
		mmap.put("member", member);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员
	 */
	@RequiresPermissions("module:member:edit")
	@Log(title = "会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Member member)
	{		
		return toAjax(memberService.updateMember(member));
	}
	
	/**
	 * 删除会员
	 */
	@RequiresPermissions("module:member:remove")
	@Log(title = "会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberService.deleteMemberByIds(ids));
	}
	
}
