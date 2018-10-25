package com.ruoyi.project.module.userPayLog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.userPayLog.mapper.UserPayLogMapper;
import com.ruoyi.project.module.userPayLog.domain.UserPayLog;
import com.ruoyi.project.module.userPayLog.service.IUserPayLogService;
import com.ruoyi.common.support.Convert;

/**
 * 商户充值记录 服务层实现
 * 
 * @author snailever
 * @date 2018-10-11
 */
@Service
public class UserPayLogServiceImpl implements IUserPayLogService 
{
	@Autowired
	private UserPayLogMapper userPayLogMapper;

	/**
     * 查询商户充值记录信息
     * 
     * @param id 商户充值记录ID
     * @return 商户充值记录信息
     */
    @Override
	public UserPayLog selectUserPayLogById(Integer id)
	{
	    return userPayLogMapper.selectUserPayLogById(id);
	}
	
	/**
     * 查询商户充值记录列表
     * 
     * @param userPayLog 商户充值记录信息
     * @return 商户充值记录集合
     */
	@Override
	public List<UserPayLog> selectUserPayLogList(UserPayLog userPayLog)
	{
	    return userPayLogMapper.selectUserPayLogList(userPayLog);
	}
	
    /**
     * 新增商户充值记录
     * 
     * @param userPayLog 商户充值记录信息
     * @return 结果
     */
	@Override
	public int insertUserPayLog(UserPayLog userPayLog)
	{
	    return userPayLogMapper.insertUserPayLog(userPayLog);
	}
	
	/**
     * 修改商户充值记录
     * 
     * @param userPayLog 商户充值记录信息
     * @return 结果
     */
	@Override
	public int updateUserPayLog(UserPayLog userPayLog)
	{
	    return userPayLogMapper.updateUserPayLog(userPayLog);
	}

	/**
     * 删除商户充值记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserPayLogByIds(String ids)
	{
		return userPayLogMapper.deleteUserPayLogByIds(Convert.toStrArray(ids));
	}
	
}
