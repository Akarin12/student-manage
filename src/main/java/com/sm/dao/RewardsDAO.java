package com.sm.dao;

import com.sm.entity.Rewards;

import java.sql.SQLException;
import java.util.List;

public interface RewardsDAO {
    /**
     * 查询所有
     *
     * @return
     * @throws SQLException
     */
    List<Rewards> getAll() throws SQLException;

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteById(String id) throws SQLException;

    /**
     * 根据关键字查询
     *
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<Rewards> selectByKeywords(String keywords) throws SQLException;

    /**
     * 新增奖惩
     *
     * @param rewards
     * @return
     * @throws SQLException
     */
    int insertRewards(Rewards rewards) throws SQLException;
}
