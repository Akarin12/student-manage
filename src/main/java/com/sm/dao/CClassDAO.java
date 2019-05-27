package com.sm.dao;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

/**
 * 班级DAO接口
 */
public interface CClassDAO {
    /**
     * 查询所有班级
     *
     * @return List<CClass>
     * @throws SQLException
     */
    List<CClass> selectAll() throws SQLException;


    /**
     * 按照院系id查询班级
     * @param departmentId
     * @return List<CClass>
     * @throws SQLException
     */
    List<CClass> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 新增班级功能
     * @param cClass
     * @return
     * @throws SQLException
     */
    int insertClass(CClass cClass) throws SQLException;

    /**
     * 根据ID删除班级
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteClass(int id) throws SQLException;
}