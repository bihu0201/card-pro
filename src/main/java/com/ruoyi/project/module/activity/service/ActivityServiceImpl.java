package com.ruoyi.project.module.activity.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.activity.mapper.ActivityMapper;
import com.ruoyi.project.module.activity.domain.Activity;
import com.ruoyi.project.module.activity.service.IActivityService;
import com.ruoyi.common.support.Convert;

/**
 * 商户活动 服务层实现
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Service
public class ActivityServiceImpl implements IActivityService 
{
	@Autowired
	private ActivityMapper activityMapper;

	/**
     * 查询商户活动信息
     * 
     * @param id 商户活动ID
     * @return 商户活动信息
     */
    @Override
	public Activity selectActivityById(Integer id)
	{
	    return activityMapper.selectActivityById(id);
	}
	
	/**
     * 查询商户活动列表
     * 
     * @param activity 商户活动信息
     * @return 商户活动集合
     */
	@Override
	public List<Activity> selectActivityList(Activity activity)
	{
	    return activityMapper.selectActivityList(activity);
	}
	
    /**
     * 新增商户活动
     * 
     * @param activity 商户活动信息
     * @return 结果
     */
	@Override
	public int insertActivity(Activity activity)
	{
	    return activityMapper.insertActivity(activity);
	}
	
	/**
     * 修改商户活动
     * 
     * @param activity 商户活动信息
     * @return 结果
     */
	@Override
	public int updateActivity(Activity activity)
	{
	    return activityMapper.updateActivity(activity);
	}

	/**
     * 删除商户活动对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteActivityByIds(String ids)
	{
		return activityMapper.deleteActivityByIds(Convert.toStrArray(ids));
	}
	
}
