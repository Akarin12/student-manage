package com.sm.dao.impl;

import com.sm.dao.RoPDAO;
import com.sm.entity.RoP;
import com.sm.entity.RoPVO;
import com.sm.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoPDAOImpl implements RoPDAO {
    @Override
    public List<RoPVO> getAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.student_name,t3.class_name,t4.department_name\n" +
                "FROM t_rop t1\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t1.student_id=t2.id\n" +
                "LEFT JOIN t_class t3\n" +
                "ON t3.id=t2.class_id\n" +
                "LEFT JOIN t_department t4\n" +
                "ON t4.id=t3.department_id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<RoPVO> rop = new ArrayList<>();
        while (rs.next()) {
            RoPVO roPVO = new RoPVO();
            roPVO.setId(rs.getInt("id"));
            roPVO.setStudentId(rs.getString("student_id"));
            roPVO.setStudentName(rs.getString("student_name"));
            roPVO.setDetails(rs.getString("details"));
            roPVO.setrOrP(rs.getInt("r_or_p"));
            roPVO.setDate(rs.getDate("date"));
            rop.add(roPVO);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rop;
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_rop WHERE id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public List<RoPVO> selectByKeywords(String keywords) throws SQLException {
//        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
//        Connection connection = jdbcUtil.getConnection();
//        String sql = "SELECT t1.*,t2.class_name,t3.department_name\n" +
//                "FROM t_student t1\n" +
//                "LEFT JOIN t_class t2\n" +
//                "ON t1.class_id=t2.id\n" +
//                "LEFT JOIN t_department t3\n" +
//                "ON t2.department_id=t3.id\n" +
//                "WHERE t1.student_name LIKE ? OR t1.address LIKE ? ";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        pstmt.setString(1, "%" + keywords + "%");
//        pstmt.setString(2, "%" + keywords + "%");
//        ResultSet rs = pstmt.executeQuery();
//        List<RoPVO> rop = convert(rs);
//        rs.close();
//        pstmt.close();
//        jdbcUtil.closeConnection();
//        return rop;
        return null;
    }

    @Override
    public int insertRewards(RoP roP) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_rop VALUES (?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, roP.getId());
        pstmt.setString(2, roP.getStudentId());
        pstmt.setString(3, roP.getDetails());
        pstmt.setInt(4, roP.getrOrP());
        pstmt.setDate(5, new Date(roP.getDate().getTime()));
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

//    private List<RoPVO> convert(ResultSet rs) throws SQLException {
//        List<RoPVO> rop = new ArrayList<>();
//        while (rs.next()) {
//            RoPVO roPVO = new RoPVO();
//            studentVO.setId(rs.getString("id"));
//            studentVO.setStudentName(rs.getString("student_name"));
//            studentVO.setAvatar(rs.getString("avatar"));
//            studentVO.setGender(rs.getString("gender"));
//            studentVO.setBirthday(rs.getDate("birthday"));
//            studentVO.setAddress(rs.getString("address"));
//            studentVO.setPhone(rs.getString("phone"));
//            studentVO.setClassName(rs.getString("class_name"));
//            studentVO.setDepartmentName(rs.getString("department_name"));
//            rop.add(roPVO);
//        }
//        return rop;
//    }
}
