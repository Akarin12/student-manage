package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<StudentVO> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> students = new ArrayList<>();
        while (rs.next()) {
            StudentVO studentVO = new StudentVO();
            studentVO.setId(rs.getString("id"));
            studentVO.setStudentName(rs.getString("student_name"));
            studentVO.setAvatar(rs.getString("avatar"));
            studentVO.setGender(rs.getString("gender"));
            studentVO.setBirthday(rs.getDate("birthday"));
            studentVO.setAddress(rs.getString("address"));
            studentVO.setPhone(rs.getString("phone"));
            studentVO.setClassName(rs.getString("class_name"));
            studentVO.setDepartmentName(rs.getString("department_name"));
            students.add(studentVO);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return students;
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n" +
                "WHERE t3.id=? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> students = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return students;
    }

    @Override
    public List<StudentVO> selectByClassId(int ClassId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n" +
                "WHERE t2.id=? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, ClassId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> students = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return students;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
                "FROM t_student t1\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.class_id=t2.id\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.department_id=t3.id\n" +
                "WHERE t1.student_name LIKE ? OR t1.address LIKE ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, "%" + keywords + "%");
        pstmt.setString(2, "%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> students = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return students;
    }

    @Override
    public int updateStudent(Student student) throws SQLException {
        return 0;
    }

    @Override
    public int deleteById(String id) throws SQLException {
        return 0;
    }

    private List<StudentVO> convert(ResultSet rs) throws SQLException {
        List<StudentVO> students = new ArrayList<>();
        while (rs.next()) {
            StudentVO studentVO = new StudentVO();
            studentVO.setId(rs.getString("id"));
            studentVO.setStudentName(rs.getString("student_name"));
            studentVO.setAvatar(rs.getString("avatar"));
            studentVO.setGender(rs.getString("gender"));
            studentVO.setBirthday(rs.getDate("birthday"));
            studentVO.setAddress(rs.getString("address"));
            studentVO.setPhone(rs.getString("phone"));
            studentVO.setClassName(rs.getString("class_name"));
            studentVO.setDepartmentName(rs.getString("department_name"));
            students.add(studentVO);
        }
        return students;
    }
}