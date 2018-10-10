package com.ruoyi.project.module.userPayLog.service;

import com.ruoyi.project.module.userPayLog.domain.UserPayLog;
import java.util.List;

/**
 * 商户充值记录 服务层
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public interface IUserPayLogService 
{
	/**
     * 查询商户充值记录信息
     * 
     * @param id 商户充值记录ID
     * @return 商户充值记录信息
     */
	public UserPayLog selectUserPayLogById(String id);
	
	/**
     * 查询商户充值记录列表
     * 
     * @param userPayLog 商户充值记录信息
     * @return 商户充值记录集合
     */
	public List<UserPayLog> selectUserPayLogList(UserPayLog userPayLog);
	
	/**
     * 新增商户充值记录
     * 
     * @param userPayLog 商户充值记录信息
     * @return 结果
     */
	public int insertUserPayLog(UserPayLog userPayLog);
	
	/**
     * 修改商户充值记录
     * 
     * @param userPayLog 商户充值记录信息
     * @return 结果
     */
	public int updateUserPayLog(UserPayLog userPayLog);
		
	/**
     * 删除商户充值记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserPayLogByIds(String ids);
	
}
