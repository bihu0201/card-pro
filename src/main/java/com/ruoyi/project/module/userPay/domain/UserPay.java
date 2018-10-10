package com.ruoyi.project.module.userPay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 商户充值表 biz_user_pay
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public class UserPay extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 商户ID */
	private Integer userId;
	/** 充值金额 */
	private BigDecimal payFeeCurrent;
	/**  */
	private BigDecimal payFeeTotal;
	/** 充值方式 1 微信 */
	private String payMethod;
	/** 说明 */
	private String remark;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
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
            .append("remark", getRemark())
            .toString();
    }
}
