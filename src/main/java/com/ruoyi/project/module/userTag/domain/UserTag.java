package com.ruoyi.project.module.userTag.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户标签表 biz_user_tag
 * 
 * @author snailever
 * @date 2018-10-28
 */
public class UserTag
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/**  */
	private String userOpenId;
	/**  */
	private Integer tag;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setUserOpenId(String userOpenId) 
	{
		this.userOpenId = userOpenId;
	}

	public String getUserOpenId() 
	{
		return userOpenId;
	}
	public void setTag(Integer tag) 
	{
		this.tag = tag;
	}

	public Integer getTag() 
	{
		return tag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userOpenId", getUserOpenId())
            .append("tag", getTag())
            .toString();
    }
}
