package com.sm.service;

import com.sm.entity.StudentVO;

import java.util.List;

public interface StudentService {
    /**
     *
     * @return List<StudentVO>
     */
    List<StudentVO> selectAll();

    /**
     * @param departmentId
     * @return
     */
    List<StudentVO> selectByDepartmentId(int departmentId);

    /**
     * @param ClassId
     * @return
     */
    List<StudentVO> selectByClassId(int ClassId);

    /**
     * @param keywords
     * @return
     */
    List<StudentVO> selectByKeywords(String keywords);

}
