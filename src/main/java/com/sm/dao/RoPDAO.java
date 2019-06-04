package com.sm.dao;

import com.sm.entity.RoP;
import com.sm.entity.RoPVO;

import java.sql.SQLException;
import java.util.List;

public interface RoPDAO {
    /**
     * 查询所有
     *
     * @return
     * @throws SQLException
     */
    List<RoPVO> getAll() throws SQLException;

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteById(Integer id) throws SQLException;

    /**
     * 根据关键字查询
     *
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<RoPVO> selectByKeywords(String keywords) throws SQLException;

    /**
     * 新增奖惩
     *
     * @param roP
     * @return
     * @throws SQLException
     */
    int insertRewards(RoP roP) throws SQLException;
}
