package com.ruoyi.project.module.userPay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户充值表 biz_user_pay
 *
 * @author snailever
 * @date 2018-10-11
 */
public class UserPay extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;
	/** 商户ID */
	private Integer userId;
	/** 当前金额 */
	private BigDecimal payFeeCurrent;
	/** 累计充值金额 */
	private BigDecimal payFeeTotal;
	/** 充值方式 1 微信 */
	private String payMethod;
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
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Integer getUserId()
	{
		return userId;
	}
	public void setPayFeeCurrent(BigDecimal payFeeCurrent)
	{
		this.payFeeCurrent = payFeeCurrent;
	}

	public BigDecimal getPayFeeCurrent()
	{
		return payFeeCurrent;
	}
	public void setPayFeeTotal(BigDecimal payFeeTotal)
	{
		this.payFeeTotal = payFeeTotal;
	}

	public BigDecimal getPayFeeTotal()
	{
		return payFeeTotal;
	}
	public void setPayMethod(String payMethod)
	{
		this.payMethod = payMethod;
	}

	public String getPayMethod()
	{
		return payMethod;
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
				.append("userId", getUserId())
				.append("payFeeCurrent", getPayFeeCurrent())
				.append("payFeeTotal", getPayFeeTotal())
				.append("payMethod", getPayMethod())
				.append("createBy", getCreateBy())
				.append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime())
				.append("remark", getRemark())
				.toString();
	}
}
