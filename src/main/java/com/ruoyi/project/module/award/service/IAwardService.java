package com.ruoyi.project.module.award.service;

import com.ruoyi.project.module.award.domain.Award;
import java.util.List;

/**
 * 奖项 服务层
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public interface IAwardService 
{
	/**
     * 查询奖项信息
     * 
     * @param id 奖项ID
     * @return 奖项信息
     */
	public Award selectAwardById(Integer id);
	
	/**
     * 查询奖项列表
     * 
     * @param award 奖项信息
     * @return 奖项集合
     */
	public List<Award> selectAwardList(Award award);
	
	/**
     * 新增奖项
     * 
     * @param award 奖项信息
     * @return 结果
     */
	public int insertAward(Award award);
	
	/**
     * 修改奖项
     * 
     * @param award 奖项信息
     * @return 结果
     */
	public int updateAward(Award award);
		
	/**
     * 删除奖项信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAwardByIds(String ids);
	
}
