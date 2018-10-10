package com.ruoyi.project.module.memberAward.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.module.memberAward.mapper.MemberAwardMapper;
import com.ruoyi.project.module.memberAward.domain.MemberAward;
import com.ruoyi.project.module.memberAward.service.IMemberAwardService;
import com.ruoyi.common.support.Convert;

/**
 * 会员抽奖 服务层实现
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
@Service
public class MemberAwardServiceImpl implements IMemberAwardService 
{
	@Autowired
	private MemberAwardMapper memberAwardMapper;

	/**
     * 查询会员抽奖信息
     * 
     * @param id 会员抽奖ID
     * @return 会员抽奖信息
     */
    @Override
	public MemberAward selectMemberAwardById(Integer id)
	{
	    return memberAwardMapper.selectMemberAwardById(id);
	}
	
	/**
     * 查询会员抽奖列表
     * 
     * @param memberAward 会员抽奖信息
     * @return 会员抽奖集合
     */
	@Override
	public List<MemberAward> selectMemberAwardList(MemberAward memberAward)
	{
	    return memberAwardMapper.selectMemberAwardList(memberAward);
	}
	
    /**
     * 新增会员抽奖
     * 
     * @param memberAward 会员抽奖信息
     * @return 结果
     */
	@Override
	public int insertMemberAward(MemberAward memberAward)
	{
	    return memberAwardMapper.insertMemberAward(memberAward);
	}
	
	/**
     * 修改会员抽奖
     * 
     * @param memberAward 会员抽奖信息
     * @return 结果
     */
	@Override
	public int updateMemberAward(MemberAward memberAward)
	{
	    return memberAwardMapper.updateMemberAward(memberAward);
	}

	/**
     * 删除会员抽奖对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberAwardByIds(String ids)
	{
		return memberAwardMapper.deleteMemberAwardByIds(Convert.toStrArray(ids));
	}
	
}
