package com.ruoyi.project.module.activity.mapper;

import com.ruoyi.project.module.activity.domain.Activity;
import java.util.List;	

/**
 * 商户活动 数据层
 * 
 * @author snailever
 * @date 2018-10-11
 */
public interface ActivityMapper 
{
	/**
     * 查询商户活动信息
     * 
     * @param id 商户活动ID
     * @return 商户活动信息
     */
	public Activity selectActivityById(Integer id);
	
	/**
     * 查询商户活动列表
     * 
     * @param activity 商户活动信息
     * @return 商户活动集合
     */
	public List<Activity> selectActivityList(Activity activity);
	
	/**
     * 新增商户活动
     * 
     * @param activity 商户活动信息
     * @return 结果
     */
	public int insertActivity(Activity activity);
	
	/**
     * 修改商户活动
     * 
     * @param activity 商户活动信息
     * @return 结果
     */
	public int updateActivity(Activity activity);
	
	/**
     * 删除商户活动
     * 
     * @param id 商户活动ID
     * @return 结果
     */
	public int deleteActivityById(Integer id);
	
	/**
     * 批量删除商户活动
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteActivityByIds(String[] ids);


	
}