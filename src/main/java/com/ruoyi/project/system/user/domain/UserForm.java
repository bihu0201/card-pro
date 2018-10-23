package com.ruoyi.project.system.user.domain;

import com.ruoyi.project.module.activity.domain.Activity;
import com.ruoyi.project.module.award.domain.Award;
import com.ruoyi.project.module.memberAward.domain.MemberAward;

import java.util.List;

public class UserForm {
    private  User user;
    private List<MemberAward> memberAwardList;
    private  List<Award>  awardList;
    private  List<Activity>  activitieList;

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

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    public List<Activity> getActivitieList() {
        return activitieList;
    }

    public void setActivitieList(List<Activity> activitieList) {
        this.activitieList = activitieList;
    }
}
