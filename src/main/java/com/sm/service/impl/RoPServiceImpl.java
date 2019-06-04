package com.sm.service.impl;

import com.sm.dao.RoPDAO;
import com.sm.entity.RoP;
import com.sm.entity.RoPVO;
import com.sm.factory.DAOFactory;
import com.sm.service.RoPService;

import java.sql.SQLException;
import java.util.List;

public class RoPServiceImpl implements RoPService {
    RoPDAO roPDAO = DAOFactory.getRoPDAOInstance();

    @Override
    public List<RoPVO> selectAll() {
        List<RoPVO> rop = null;
        try {
            rop = roPDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rop;
    }

    @Override
    public int deleteById(Integer id) {
        int n = 0;
        try {
            n = roPDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<RoPVO> selectByKeywords(String keywords) {
        List<RoPVO> rop = null;
        try {
            rop = roPDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rop;
    }

    @Override
    public int insertStudent(RoP roP) {
        int n =0;
        try {
            n = roPDAO.insertRewards(roP);
        } catch (SQLException e) {
            System.err.print("新增奖惩信息出现异常");
        }
        return n;
    }
}
