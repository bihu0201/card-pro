package com.ruoyi.project.module.payLevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.payLevel.mapper.PayLevelMapper;
import com.ruoyi.project.module.payLevel.domain.PayLevel;
import com.ruoyi.project.module.payLevel.service.IPayLevelService;
import com.ruoyi.common.support.Convert;

/**
 * 充值等级 服务层实现
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Service
public class PayLevelServiceImpl implements IPayLevelService 
{
	@Autowired
	private PayLevelMapper payLevelMapper;

	/**
     * 查询充值等级信息
     * 
     * @param id 充值等级ID
     * @return 充值等级信息
     */
    @Override
	public PayLevel selectPayLevelById(Integer id)
	{
	    return payLevelMapper.selectPayLevelById(id);
	}
	
	/**
     * 查询充值等级列表
     * 
     * @param payLevel 充值等级信息
     * @return 充值等级集合
     */
	@Override
	public List<PayLevel> selectPayLevelList(PayLevel payLevel)
	{
	    return payLevelMapper.selectPayLevelList(payLevel);
	}
	
    /**
     * 新增充值等级
     * 
     * @param payLevel 充值等级信息
     * @return 结果
     */
	@Override
	public int insertPayLevel(PayLevel payLevel)
	{
	    return payLevelMapper.insertPayLevel(payLevel);
	}
	
	/**
     * 修改充值等级
     * 
     * @param payLevel 充值等级信息
     * @return 结果
     */
	@Override
	public int updatePayLevel(PayLevel payLevel)
	{
	    return payLevelMapper.updatePayLevel(payLevel);
	}

	/**
     * 删除充值等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayLevelByIds(String ids)
	{
		return payLevelMapper.deletePayLevelByIds(Convert.toStrArray(ids));
	}
	
}
