package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import com.sm.service.CClassService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CClassServiceImpl implements CClassService {
    private CClassDAO cclassDAO = DAOFactory.getCClassDAOInstance();

    @Override
    public List<CClass> selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cclassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cClassList;
    }

    @Override
    public List<CClass> selectByDepartmentId(int departmentId) {
        List<CClass> cClassList = new ArrayList<>();
        try {
            cClassList = cclassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            System.err.println("根据类别查询班级信息出现异常");
        }
        return cClassList;
    }

    @Override
    public void addClass(CClass cClass) {
        try {
            cclassDAO.insertClass(cClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delectClass(int id) {
        try {
            cclassDAO.deleteClass(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
