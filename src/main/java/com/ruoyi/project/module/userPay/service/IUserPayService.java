package com.ruoyi.project.module.userPay.service;

import com.ruoyi.project.module.memberAward.domain.MemberAward;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPayLog.domain.UserPayLog;

import java.util.List;

/**
 * 商户充值 服务层
 * 
 * @author snailever
 * @date 2018-10-11
 */
public interface IUserPayService 
{
	/**
     * 查询商户充值信息
     * 
     * @param id 商户充值ID
     * @return 商户充值信息
     */
	public UserPay selectUserPayById(String id);
	
	/**
     * 查询商户充值列表
     * 
     * @param userPay 商户充值信息
     * @return 商户充值集合
     */
	public List<UserPay> selectUserPayList(UserPay userPay);
	
	/**
     * 新增商户充值
     * 
     * @param userPay 商户充值信息
     * @return 结果
     */
	public int insertUserPay(UserPay userPay);
	
	/**
     * 修改商户充值
     * 
     * @param userPay 商户充值信息
     * @return 结果
     */
	public int updateUserPay(UserPay userPay);

	/**
	 * 修改商户充值
	 *
	 * @param userPay 商户充值信息
	 * @return 结果
	 */
	public int updateUserPay(UserPay userPay,MemberAward memberAward);
		
	/**
     * 删除商户充值信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserPayByIds(String ids);


	/**
	 * 商户充值信息
	 *
	 * @param userPayLog 充值
	 * @return 结果
	 */
	public int pay(UserPayLog userPayLog);
	/**
	 * 查询商户充值
	 *
	 * @param userId 商户充值信息
	 * @return 商户充值集合
	 */
	public List<UserPay> selectUserPayByUserId(Integer userId);
	
}
