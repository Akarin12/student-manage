package com.sm.service;

import com.sm.entity.RoP;
import com.sm.entity.RoPVO;

import java.util.List;

public interface RoPService {
    /**
     * @return
     */
    List<RoPVO> selectAll();

    /**
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * @param keywords
     * @return
     */
    List<RoPVO> selectByKeywords(String keywords);

    /**
     * @param roP
     * @return
     */
    int insertStudent(RoP roP);
}
