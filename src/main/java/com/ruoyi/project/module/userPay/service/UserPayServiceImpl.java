package com.ruoyi.project.module.userPay.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.userPay.mapper.UserPayMapper;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import com.ruoyi.common.support.Convert;

/**
 * 商户充值 服务层实现
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Service
public class UserPayServiceImpl implements IUserPayService 
{
	@Autowired
	private UserPayMapper userPayMapper;

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
	
}
