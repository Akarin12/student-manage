package com.sm.service.impl;

import com.sm.service.StudentService;
import com.sm.dao.StudentDAO;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();

    @Override
    public List<StudentVO> selectAll() {
        List<StudentVO> students = null;
        try {
            students = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId) {
        List<StudentVO> students = null;
        try {
            students = studentDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<StudentVO> selectByClassId(int ClassId) {
        List<StudentVO> students = null;
        try {
            students = studentDAO.selectByClassId(ClassId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) {
        List<StudentVO> students = null;
        try {
            students = studentDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
