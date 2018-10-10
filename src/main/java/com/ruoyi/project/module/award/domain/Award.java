package com.ruoyi.project.module.award.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 奖项表 biz_award
 * 
 * @author ruoyi
 * @date 2018-10-10
 */
public class Award extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 奖项名称 */
	private String awardName;
	/** 图片 */
	private String awardIcon;
	/** 是否中奖 1 奖品 0  谢谢参与 */
	private Integer isAward;
	/** 状态 0 在用 1 废止 */
	private Integer state;
	/** 添加时间 */
	private Date createTime;
	/**  */
	private String createUser;
	/** 奖项说明 */
	private String awardDesc;

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
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setAwardDesc(String awardDesc) 
	{
		this.awardDesc = awardDesc;
	}

	public String getAwardDesc() 
	{
		return awardDesc;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("awardName", getAwardName())
            .append("awardIcon", getAwardIcon())
            .append("isAward", getIsAward())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("awardDesc", getAwardDesc())
            .toString();
    }
}
