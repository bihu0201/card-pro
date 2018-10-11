package com.ruoyi.project.module.award.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.award.mapper.AwardMapper;
import com.ruoyi.project.module.award.domain.Award;
import com.ruoyi.project.module.award.service.IAwardService;
import com.ruoyi.common.support.Convert;

/**
 * 奖项 服务层实现
 * 
 * @author snailever
 * @date 2018-10-11
 */
@Service
public class AwardServiceImpl implements IAwardService 
{
	@Autowired
	private AwardMapper awardMapper;

	/**
     * 查询奖项信息
     * 
     * @param id 奖项ID
     * @return 奖项信息
     */
    @Override
	public Award selectAwardById(Integer id)
	{
	    return awardMapper.selectAwardById(id);
	}
	
	/**
     * 查询奖项列表
     * 
     * @param award 奖项信息
     * @return 奖项集合
     */
	@Override
	public List<Award> selectAwardList(Award award)
	{
	    return awardMapper.selectAwardList(award);
	}
	
    /**
     * 新增奖项
     * 
     * @param award 奖项信息
     * @return 结果
     */
	@Override
	public int insertAward(Award award)
	{
	    return awardMapper.insertAward(award);
	}
	
	/**
     * 修改奖项
     * 
     * @param award 奖项信息
     * @return 结果
     */
	@Override
	public int updateAward(Award award)
	{
	    return awardMapper.updateAward(award);
	}

	/**
     * 删除奖项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAwardByIds(String ids)
	{
		return awardMapper.deleteAwardByIds(Convert.toStrArray(ids));
	}
	
}
