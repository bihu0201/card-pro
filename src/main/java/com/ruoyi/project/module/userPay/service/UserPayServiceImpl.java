package com.ruoyi.project.module.userPay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.project.module.memberAward.domain.MemberAward;
import com.ruoyi.project.module.memberAward.mapper.MemberAwardMapper;
import com.ruoyi.project.module.userPayLog.domain.UserPayLog;
import com.ruoyi.project.module.userPayLog.mapper.UserPayLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.userPay.mapper.UserPayMapper;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import com.ruoyi.common.support.Convert;

/**
 * 商户充值 服务层实现
 * 
 * @author snailever
 * @date 2018-10-11
 */
@Service
public class UserPayServiceImpl implements IUserPayService
{
	@Autowired
	private UserPayMapper userPayMapper;
	@Autowired
	private MemberAwardMapper memberAwardMapper;
	@Autowired
	private UserPayLogMapper userPayLogMapper;
	/**
     * 查询商户充值信息
     * 
     * @param id 商户充值ID
     * @return 商户充值信息
     */
    @Override
	public UserPay selectUserPayById(String id)
	{
	    return userPayMapper.selectUserPayById(id);
	}
	
	/**
     * 查询商户充值列表
     * 
     * @param userPay 商户充值信息
     * @return 商户充值集合
     */
	@Override
	public List<UserPay> selectUserPayList(UserPay userPay)
	{
	    return userPayMapper.selectUserPayList(userPay);
	}
	
    /**
     * 新增商户充值
     * 
     * @param userPay 商户充值信息
     * @return 结果
     */
	@Override
	public int insertUserPay(UserPay userPay)
	{
	    return userPayMapper.insertUserPay(userPay);
	}
	
	/**
     * 修改商户充值
     * 
     * @param userPay 商户充值信息
     * @return 结果
     */
	@Override
	public int updateUserPay(UserPay userPay)
	{

	    return userPayMapper.updateUserPay(userPay);
	}

	/**
	 * 修改商户充值
	 *
	 * @param userPay     商户充值信息
	 * @param memberAward
	 * @return 结果
	 */
	@Override
	public int updateUserPay(UserPay userPay, MemberAward memberAward) {


		memberAwardMapper.insertMemberAward(memberAward);
		return userPayMapper.updateUserPay(userPay);
	}

	/**
     * 删除商户充值对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserPayByIds(String ids)
	{
		return userPayMapper.deleteUserPayByIds(Convert.toStrArray(ids));
	}

	/**
	 * 商户充值信息
	 *
	 * @param userPayLog 充值
	 * @return 结果
	 */
	@Override
	public int pay(UserPayLog userPayLog) {


 		List<UserPay> userPays = userPayMapper.selectUserPayByUserId(userPayLog.getUserId());
 		//更新商家充值表
 		if(userPays.size()>0){
			userPays.get(0).setPayFeeCurrent(userPays.get(0).getPayFeeCurrent().add(userPayLog.getPayFee()));
			userPays.get(0).setPayFeeTotal(userPays.get(0).getPayFeeTotal().add(userPayLog.getPayFee()));
			userPayMapper.updateUserPay(userPays.get(0));
			userPayLog.setPayMethod("1");//微信支付
			userPayLog.setCreateBy("admin");
			userPayLogMapper.insertUserPayLog(userPayLog);
			return 1;
		}else{
 			return 0;
		}

	}

	/**
	 * 查询商户userId查询
	 *
	 * @param userId 商户充值信息
	 * @return 商户充值集合
	 */
	@Override
	public List<UserPay> selectUserPayByUserId(Integer userId) {
		return userPayMapper.selectUserPayByUserId(userId);
	}

}
