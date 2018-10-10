package com.ruoyi.project.module.activity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 商户活动表 biz_activity
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public class Activity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 商家ID */
	private Integer userId;
	/** 活动名称 */
	private String activityName;
	/** 活动满员人数 */
	private Integer activityTotal;
	/** 当前人数 */
	private Integer activityCurrent;
	/** 活动说明 */
	private String activityDesc;
	/**  */
	private Date activityStartTime;
	/**  */
	private Date activityEndTime;
	/** 活动状态 */
	private Integer state;
	/** 备用字段1 */
	private String str1;
	/** 备用字段2 */
	private String str2;
	/** 备用字段3 */
	private String str3;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setActivityName(String activityName) 
	{
		this.activityName = activityName;
	}

	public String getActivityName() 
	{
		return activityName;
	}
	public void setActivityTotal(Integer activityTotal) 
	{
		this.activityTotal = activityTotal;
	}

	public Integer getActivityTotal() 
	{
		return activityTotal;
	}
	public void setActivityCurrent(Integer activityCurrent) 
	{
		this.activityCurrent = activityCurrent;
	}

	public Integer getActivityCurrent() 
	{
		return activityCurrent;
	}
	public void setActivityDesc(String activityDesc) 
	{
		this.activityDesc = activityDesc;
	}

	public String getActivityDesc() 
	{
		return activityDesc;
	}
	public void setActivityStartTime(Date activityStartTime) 
	{
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityStartTime() 
	{
		return activityStartTime;
	}
	public void setActivityEndTime(Date activityEndTime) 
	{
		this.activityEndTime = activityEndTime;
	}

	public Date getActivityEndTime() 
	{
		return activityEndTime;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setStr1(String str1) 
	{
		this.str1 = str1;
	}

	public String getStr1() 
	{
		return str1;
	}
	public void setStr2(String str2) 
	{
		this.str2 = str2;
	}

	public String getStr2() 
	{
		return str2;
	}
	public void setStr3(String str3) 
	{
		this.str3 = str3;
	}

	public String getStr3() 
	{
		return str3;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("activityName", getActivityName())
            .append("activityTotal", getActivityTotal())
            .append("activityCurrent", getActivityCurrent())
            .append("activityDesc", getActivityDesc())
            .append("activityStartTime", getActivityStartTime())
            .append("activityEndTime", getActivityEndTime())
            .append("state", getState())
            .append("str1", getStr1())
            .append("str2", getStr2())
            .append("str3", getStr3())
            .toString();
    }
}
