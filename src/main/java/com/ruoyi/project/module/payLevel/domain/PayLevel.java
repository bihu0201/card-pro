package com.ruoyi.project.module.payLevel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值等级表 biz_pay_level
 * 
 * @author snailever
 * @date 2018-10-11
 */
public class PayLevel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 充值等级 */
	private Integer awardLevel;
	/** 充值金额 */
	private BigDecimal awardMoney;
	/** 充值说明 */
	private String awardDesc;
	/**  */
	private Integer state;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	/**  */
	private String remark;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAwardLevel(Integer awardLevel) 
	{
		this.awardLevel = awardLevel;
	}

	public Integer getAwardLevel() 
	{
		return awardLevel;
	}
	public void setAwardMoney(BigDecimal awardMoney) 
	{
		this.awardMoney = awardMoney;
	}

	public BigDecimal getAwardMoney() 
	{
		return awardMoney;
	}
	public void setAwardDesc(String awardDesc) 
	{
		this.awardDesc = awardDesc;
	}

	public String getAwardDesc() 
	{
		return awardDesc;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("awardLevel", getAwardLevel())
            .append("awardMoney", getAwardMoney())
            .append("awardDesc", getAwardDesc())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
