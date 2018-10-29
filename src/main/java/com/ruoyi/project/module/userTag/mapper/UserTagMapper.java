package com.ruoyi.project.module.userTag.mapper;

import com.ruoyi.project.module.userTag.domain.UserTag;
import java.util.List;	

/**
 * 用户标签 数据层
 * 
 * @author snailever
 * @date 2018-10-28
 */
public interface UserTagMapper 
{
	/**
     * 查询用户标签信息
     * 
     * @param id 用户标签ID
     * @return 用户标签信息
     */
	public UserTag selectUserTagById(String id);
	
	/**
     * 查询用户标签列表
     * 
     * @param userTag 用户标签信息
     * @return 用户标签集合
     */
	public List<UserTag> selectUserTagList(UserTag userTag);
	
	/**
     * 新增用户标签
     * 
     * @param userTag 用户标签信息
     * @return 结果
     */
	public int insertUserTag(UserTag userTag);
	
	/**
     * 修改用户标签
     * 
     * @param userTag 用户标签信息
     * @return 结果
     */
	public int updateUserTag(UserTag userTag);
	
	/**
     * 删除用户标签
     * 
     * @param id 用户标签ID
     * @return 结果
     */
	public int deleteUserTagById(String id);
	
	/**
     * 批量删除用户标签
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserTagByIds(String[] ids);
	
}