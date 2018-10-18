package com.ruoyi.project.module.award.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 奖项表 biz_award
 * 
 * @author snailever
 * @date 2018-10-11
 */
public class Award
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer userId;
	/** 奖项名称 */
	private String awardName;
	/** 奖品图片 */
	private String awardIcon;
	/** 是否中奖 */
	private Integer isAward;
	private Double chance;
	/** 状态 */
	private Integer state;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createBy;
	/** 修改人 */
	private String updateBy;
	/** 修改时间 */
	private Date updateTime;
	/** 奖项说明 */
	private String awardDesc;
	/** 备注 */
	private String remark;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAwardName(String awardName) 
	{
		this.awardName = awardName;
	}

	public String getAwardName() 
	{
		return awardName;
	}
	public void setAwardIcon(String awardIcon) 
	{
		this.awardIcon = awardIcon;
	}

	public String getAwardIcon() 
	{
		return awardIcon;
	}
	public void setIsAward(Integer isAward) 
	{
		this.isAward = isAward;
	}

	public Integer getIsAward() 
	{
		return isAward;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
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
	public void setAwardDesc(String awardDesc) 
	{
		this.awardDesc = awardDesc;
	}

	public String getAwardDesc() 
	{
		return awardDesc;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	public Double getChance() {
		return chance;
	}

	public void setChance(Double chance) {
		this.chance = chance;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("awardName", getAwardName())
            .append("awardIcon", getAwardIcon())
            .append("isAward", getIsAward())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("awardDesc", getAwardDesc())
            .append("remark", getRemark())
            .toString();
    }
}
