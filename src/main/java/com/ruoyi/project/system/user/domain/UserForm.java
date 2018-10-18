package com.ruoyi.project.system.user.domain;

import com.ruoyi.project.module.memberAward.domain.MemberAward;

import java.util.List;

public class UserForm {
    private  User user;
    private List<MemberAward> memberAwardList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MemberAward> getMemberAwardList() {
        return memberAwardList;
    }

    public void setMemberAwardList(List<MemberAward> memberAwardList) {
        this.memberAwardList = memberAwardList;
    }
}
