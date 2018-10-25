package com.ruoyi.project.module.memberAward.controller.api;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.activity.domain.Activity;
import com.ruoyi.project.module.activity.service.IActivityService;
import com.ruoyi.project.module.award.domain.Award;
import com.ruoyi.project.module.award.service.IAwardLotteryService;
import com.ruoyi.project.module.award.service.IAwardService;
import com.ruoyi.project.module.memberAward.domain.MemberAward;
import com.ruoyi.project.module.memberAward.service.IMemberAwardService;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 奖项 信息操作处理
 * 
 * @author snailever
 * @date 2018-10-10
 */
@Controller
@RequestMapping("/api/memberaward")
@RestController
public class ApiMemberAwardController extends BaseController
{


	@Autowired
	private IMemberAwardService memberAwardService;
	@Autowired
	private IUserPayService userPayService;
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IAwardLotteryService iAwardLotteryService;


	@Autowired
	private  IAwardService iAwardService;



	/**
	 * 用户抽奖
	 */
	@PostMapping("/draw")
	public AjaxResult draw(MemberAward memberAward)
	{
        List<MemberAward> list = memberAwardService.selectMemberAwardList(memberAward);
        if(list.size()>0){
        	Map map   = new HashMap();
			map.put("msg","该用户已抽过奖！");
			map.put("awards",list);
			return ok("999",map);
		}else{

		Activity activity = activityService.selectActivityById(memberAward.getActivityId());
		if(activity!=null){
			UserPay userPay = new UserPay();
			userPay.setUserId(activity.getUserId());
			List<UserPay> userPays = 	userPayService.selectUserPayList(userPay);
			if(userPays.size()>0){
				UserPay  currentUserPay= userPays.get(0);
				//判断商家余额账户是否有钱
				if(currentUserPay.getPayFeeCurrent().compareTo(BigDecimal.ZERO)>0){
					BigDecimal bignum1 = new BigDecimal("1");
					currentUserPay.setPayFeeCurrent(currentUserPay.getPayFeeCurrent().subtract(bignum1));
					currentUserPay.setUpdateBy(memberAward.getWechatCode());
					currentUserPay.setUpdateBy(memberAward.getWechatCode());
					currentUserPay.setUpdateTime(new Date());

					memberAward.setUpdateBy(memberAward.getWechatCode());
					memberAward.setUserId(activity.getUserId());

					/// 中奖概率
					Award award = new Award();
					award.setUserId(118);
 					List<Award> awards = iAwardService.selectAwardList(award);
					Award awardObj = iAwardLotteryService.getWard(awards);
					memberAward.setAwardId(awardObj.getId());
					///中奖概率
					userPayService.updateUserPay(currentUserPay,memberAward);
					List<MemberAward> lists = memberAwardService.selectMemberAwardList(memberAward);
					for (MemberAward memberAward1: lists) {
						 Award award1 = memberAward1.getAward();
						 if(award1.getAwardDesc()!=null&&!"".equals(award1.getAwardDesc())){
							 String[]  subAward =	award1.getAwardDesc().split(",");
							 if(subAward.length>0){
								 int num =(int)(1+Math.random()*(subAward.length));
								 award1.setAwardOk(subAward[num-1]);
								 memberAward1.setRemark(subAward[num-1]);
								 memberAwardService.updateMemberAward(memberAward1);
							 }
						 }
					}
					Map map   = new HashMap();
					map.put("msg","抽奖成功！");
					map.put("awards",lists);
					return ok("0",map);
				}else{
					return ok("999","该商家商户余额不足，无法进行抽检活动！");
				}

		}


			}else{
				return ok("999","该商家商户不存在！");
			}
			return ok("999","该商家商户不存在！");
		}
	}


	/**
	 * 查询奖项列表
	 */
	@GetMapping("/notice")
	public AjaxResult notice(int num)
	{
		List<MemberAward> list = memberAwardService.fetchMemberAwardGetNum(num);
		return ok(list);
	}
}
