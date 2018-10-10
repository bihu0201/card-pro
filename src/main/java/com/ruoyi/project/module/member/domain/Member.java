package com.ruoyi.project.module.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 会员表 biz_member
 * 
 * @author ruoyi
 * @date 2018-10-10
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
	/** 添加时间 */
	private Date createTime;
	/** 状态 */
	private Integer state;

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
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("wechatName", getWechatName())
            .append("wechatCode", getWechatCode())
            .append("wechatIcon", getWechatIcon())
            .append("createTime", getCreateTime())
            .append("state", getState())
            .toString();
    }
}
