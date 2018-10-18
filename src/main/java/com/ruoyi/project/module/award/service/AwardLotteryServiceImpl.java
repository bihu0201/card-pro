package com.ruoyi.project.module.award.service;

import com.ruoyi.project.module.award.domain.Award;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwardLotteryServiceImpl implements  IAwardLotteryService {
    /**
     *@Description:抽奖
     *@params: 
     *@Auhtor:snailever
     *@Date: 2018/10/18_14:35 
     */
    public Award getWard(List<Award>   awardList){
            LotteryUtil ll = new LotteryUtil(awardList);
            int index = ll.randomColunmIndex();
            return      awardList.get(index);
    }
}
