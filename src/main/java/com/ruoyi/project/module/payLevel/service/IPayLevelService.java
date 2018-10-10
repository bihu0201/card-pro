package com.ruoyi.project.module.payLevel.service;

import com.ruoyi.project.module.payLevel.domain.PayLevel;
import java.util.List;

/**
 * 充值等级 服务层
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public interface IPayLevelService 
{
	/**
     * 查询充值等级信息
     * 
     * @param id 充值等级ID
     * @return 充值等级信息
     */
	public PayLevel selectPayLevelById(Integer id);
	
	/**
     * 查询充值等级列表
     * 
     * @param payLevel 充值等级信息
     * @return 充值等级集合
     */
	public List<PayLevel> selectPayLevelList(PayLevel payLevel);
	
	/**
     * 新增充值等级
     * 
     * @param payLevel 充值等级信息
     * @return 结果
     */
	public int insertPayLevel(PayLevel payLevel);
	
	/**
     * 修改充值等级
     * 
     * @param payLevel 充值等级信息
     * @return 结果
     */
	public int updatePayLevel(PayLevel payLevel);
		
	/**
     * 删除充值等级信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayLevelByIds(String ids);
	
}
