package com.sm.dao;

import com.sm.entity.Student;
import com.sm.entity.StudentVO;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    /**
     * 查询所有学生（视图对象）
     *
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectAll() throws SQLException;

    /**
     * 根据院系Id查询学生
     *
     * @param departmentId
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级Id查询学生
     *
     * @param ClassId
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectByClassId(int ClassId) throws SQLException;

    /**
     * 根据关键字查询学生
     *
     * @param keywords
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectByKeywords(String keywords) throws SQLException;

    /**
     * 更新学生信息
     *
     * @param student
     * @return
     * @throws SQLException
     */
    int updateStudent(Student student) throws SQLException;

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteById(String id) throws SQLException;

    /**
     *
     * @param student
     * @return
     * @throws SQLException
     */
    int insertStudent(Student student) throws SQLException;

    /**
     *
     * @param departmentId
     * @return
     * @throws SQLException
     */
    int countByDepartmentId(int departmentId) throws SQLException;

    /**
     *
     * @param classId
     * @return
     * @throws SQLException
     */
    int countByClassId(int classId) throws SQLException;
}