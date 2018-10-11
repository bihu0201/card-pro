package com.ruoyi.project.module.award.controller.api;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.award.domain.Award;
import com.ruoyi.project.module.award.service.IAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 奖项 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/api/award")
@RestController
public class ApiAwardController extends BaseController
{
    private String prefix = "module/award";

	@Autowired
	private IAwardService awardService;


	/**
	 * 查询奖项列表
	 */
	@GetMapping("/list")
	public AjaxResult list(Award award)
	{
        List<Award> list = awardService.selectAwardList(award);
		return ok(list);
	}
}
