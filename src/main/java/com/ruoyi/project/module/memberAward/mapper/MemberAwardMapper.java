package com.ruoyi.project.module.memberAward.mapper;

import com.ruoyi.project.module.memberAward.domain.MemberAward;
import java.util.List;	

/**
 * 会员抽奖 数据层
 * 
 * @author snailever
 * @date 2018-10-11
 */
public interface MemberAwardMapper 
{


	public  List<MemberAward> selectMemberAwardByUserId(Long userId);
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
     * 删除会员抽奖
     * 
     * @param id 会员抽奖ID
     * @return 结果
     */
	public int deleteMemberAwardById(Integer id);
	
	/**
     * 批量删除会员抽奖
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberAwardByIds(String[] ids);

	/**
	 * 查询中奖条目
	 * @param num
	 * @return 结果
	 */
	public List<MemberAward>  fetchMemberAwardGetNum(Integer num);
	
}