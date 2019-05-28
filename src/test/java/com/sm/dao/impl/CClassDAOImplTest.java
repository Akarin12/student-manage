package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class CClassDAOImplTest {
    CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
    @Test
    public void countByDepartmentId() {
        try {
            System.out.println(cClassDAO.selectByDepartmentId(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}