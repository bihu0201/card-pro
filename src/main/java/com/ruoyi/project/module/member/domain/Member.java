package com.ruoyi.project.module.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 会员表 biz_member
 * 
 * @author snailever
 * @date 2018-10-11
 */
public class Member extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 会员表 */
	private Integer memberId;
	/** 昵称 */
	private String wechatName;
	/** 微信号 */
	private String wechatCode;
	/** 微信头像 */
	private String wechatIcon;
	/** 状态 */
	private Integer state;
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

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMemberId(Integer memberId) 
	{
		this.memberId = memberId;
	}

	public Integer getMemberId() 
	{
		return memberId;
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
	public void setWechatIcon(String wechatIcon) 
	{
		this.wechatIcon = wechatIcon;
	}

	public String getWechatIcon() 
	{
		return wechatIcon;
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
            .append("memberId", getMemberId())
            .append("wechatName", getWechatName())
            .append("wechatCode", getWechatCode())
            .append("wechatIcon", getWechatIcon())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
