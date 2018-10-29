package com.ruoyi.project.module.userTag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.userTag.mapper.UserTagMapper;
import com.ruoyi.project.module.userTag.domain.UserTag;
import com.ruoyi.project.module.userTag.service.IUserTagService;
import com.ruoyi.common.support.Convert;

/**
 * 用户标签 服务层实现
 * 
 * @author snailever
 * @date 2018-10-28
 */
@Service
public class UserTagServiceImpl implements IUserTagService 
{
	@Autowired
	private UserTagMapper userTagMapper;

	/**
     * 查询用户标签信息
     * 
     * @param id 用户标签ID
     * @return 用户标签信息
     */
    @Override
	public UserTag selectUserTagById(String id)
	{
	    return userTagMapper.selectUserTagById(id);
	}
	
	/**
     * 查询用户标签列表
     * 
     * @param userTag 用户标签信息
     * @return 用户标签集合
     */
	@Override
	public List<UserTag> selectUserTagList(UserTag userTag)
	{
	    return userTagMapper.selectUserTagList(userTag);
	}
	
    /**
     * 新增用户标签
     * 
     * @param userTag 用户标签信息
     * @return 结果
     */
	@Override
	public int insertUserTag(UserTag userTag)
	{
	    return userTagMapper.insertUserTag(userTag);
	}
	
	/**
     * 修改用户标签
     * 
     * @param userTag 用户标签信息
     * @return 结果
     */
	@Override
	public int updateUserTag(UserTag userTag)
	{
	    return userTagMapper.updateUserTag(userTag);
	}

	/**
     * 删除用户标签对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserTagByIds(String ids)
	{
		return userTagMapper.deleteUserTagByIds(Convert.toStrArray(ids));
	}
	
}
