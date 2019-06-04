package com.sm.service;

import com.sm.entity.Rewards;

import java.util.List;

public interface RewardsService {
    /**
     * 查询所有
     * @return
     */
    List<Rewards> getAll();

    /**
     * 根据Id删除
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 关键字搜索
     * @param keywords
     * @return
     */
    List<Rewards> selectByKeywords(String keywords);

    /**
     * 新增奖惩
     * @param rewards
     * @return
     */
    int insertRewards(Rewards rewards);
}
