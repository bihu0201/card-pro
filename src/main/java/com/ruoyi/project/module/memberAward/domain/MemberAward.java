package com.ruoyi.project.module.memberAward.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 会员抽奖表 biz_member_award
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public class MemberAward extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 昵称 */
	private Integer wechatName;
	/** 微信号 */
	private String wechatCode;
	/** 奖品ID */
	private String awardId;
	/** 活动ID */
	private Integer activityId;
	/** 抽奖时间 */
	private Date awardTime;
	/** 商家ID */
	private Integer userId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setWechatName(Integer wechatName) 
	{
		this.wechatName = wechatName;
	}

	public Integer getWechatName() 
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
	public void setAwardId(String awardId) 
	{
		this.awardId = awardId;
	}

	public String getAwardId() 
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wechatName", getWechatName())
            .append("wechatCode", getWechatCode())
            .append("awardId", getAwardId())
            .append("activityId", getActivityId())
            .append("awardTime", getAwardTime())
            .append("userId", getUserId())
            .toString();
    }
}
