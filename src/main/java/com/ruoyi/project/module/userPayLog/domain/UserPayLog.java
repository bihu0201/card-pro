package com.ruoyi.project.module.userPayLog.domain;

import com.ruoyi.project.system.user.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户充值记录表 biz_user_pay_log
 *
 * @author snailever
 * @date 2018-10-11
 */
public class UserPayLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;
	/** 商户ID */
	private Integer userId;
	/** 商户名称 */
	private String userName;
	/** 充值金额 */
	private BigDecimal payFee;
	/** 充值方式 1 微信 */
	private String payMethod;
	/** 充值时间 */
	private Date payTime;
	/** 充值项目 */
	private String payDesc;
	/** 充值状态 0 代充值 1 成功 2 失败 */
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
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserName()
	{
		return userName;
	}
	public void setPayFee(BigDecimal payFee)
	{
		this.payFee = payFee;
	}

	public BigDecimal getPayFee()
	{
		return payFee;
	}
	public void setPayMethod(String payMethod)
	{
		this.payMethod = payMethod;
	}

	public String getPayMethod()
	{
		return payMethod;
	}
	public void setPayTime(Date payTime)
	{
		this.payTime = payTime;
	}

	public Date getPayTime()
	{
		return payTime;
	}
	public void setPayDesc(String payDesc)
	{
		this.payDesc = payDesc;
	}

	public String getPayDesc()
	{
		return payDesc;
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
				.append("userId", getUserId())
				.append("userName", getUserName())
				.append("payFee", getPayFee())
				.append("payMethod", getPayMethod())
				.append("payTime", getPayTime())
				.append("payDesc", getPayDesc())
				.append("state", getState())
				.append("createBy", getCreateBy())
				.append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime())
				.append("remark", getRemark())
				.toString();
	}
}
