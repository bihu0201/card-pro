package com.ruoyi.project.module.userPay.mapper;

import com.ruoyi.project.module.userPay.domain.UserPay;
import java.util.List;	

/**
 * 商户充值 数据层
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public interface UserPayMapper 
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
     * 删除商户充值
     * 
     * @param id 商户充值ID
     * @return 结果
     */
	public int deleteUserPayById(String id);
	
	/**
     * 批量删除商户充值
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserPayByIds(String[] ids);
	
}