package com.ruoyi.project.module.memberAward.domain;

import com.ruoyi.project.module.award.domain.Award;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 会员抽奖表 biz_member_award
 * 
 * @author snailever
 * @date 2018-10-11
 */
public class MemberAward
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 昵称 */
	private String wechatName;
	/** 微信号 */
	private String wechatCode;
	/** 奖品ID */
	private Integer awardId;
	/** 活动ID */
	private Integer activityId;
	/** 抽奖时间 */
	private Date awardTime;
	/** 商家ID */
	private Integer userId;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;
	/** 备注 */
	private String tag;
	private Award award;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setWechatName(String wechatName)
	{
		this.wechatName = wechatName;
	}

	public String getWechatName()
	{
		return wechatName;
	}
	public void setWechatCode(String wechatCode) 
	{
		this.wechatCode = wechatCode;
	}

	public String getWechatCode() 
	{
		return wechatCode;
	}
	public void setAwardId(Integer awardId)
	{
		this.awardId = awardId;
	}

	public Integer getAwardId()
	{
		return awardId;
	}
	public void setActivityId(Integer activityId) 
	{
		this.activityId = activityId;
	}

	public Integer getActivityId() 
	{
		return activityId;
	}
	public void setAwardTime(Date awardTime) 
	{
		this.awardTime = awardTime;
	}

	public Date getAwardTime() 
	{
		return awardTime;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Integer getUserId()
	{
		return userId;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wechatName", getWechatName())
            .append("wechatCode", getWechatCode())
            .append("awardId", getAwardId())
            .append("activityId", getActivityId())
            .append("awardTime", getAwardTime())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
