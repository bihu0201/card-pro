package com.ruoyi.project.module.memberAward.service;

import com.ruoyi.project.module.memberAward.domain.MemberAward;
import java.util.List;

/**
 * 会员抽奖 服务层
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public interface IMemberAwardService 
{
	/**
     * 查询会员抽奖信息
     * 
     * @param id 会员抽奖ID
     * @return 会员抽奖信息
     */
	public MemberAward selectMemberAwardById(Integer id);
	
	/**
     * 查询会员抽奖列表
     * 
     * @param memberAward 会员抽奖信息
     * @return 会员抽奖集合
     */
	public List<MemberAward> selectMemberAwardList(MemberAward memberAward);
	
	/**
     * 新增会员抽奖
     * 
     * @param memberAward 会员抽奖信息
     * @return 结果
     */
	public int insertMemberAward(MemberAward memberAward);
	
	/**
     * 修改会员抽奖
     * 
     * @param memberAward 会员抽奖信息
     * @return 结果
     */
	public int updateMemberAward(MemberAward memberAward);
		
	/**
     * 删除会员抽奖信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberAwardByIds(String ids);
	
}
