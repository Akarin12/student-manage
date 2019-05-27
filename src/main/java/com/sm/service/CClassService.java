package com.sm.service;

import com.sm.entity.CClass;

import java.util.List;

public interface CClassService {
    /**
     *
     * @return
     */
    List<CClass> selectAll();
    /**
     *
     * @param departmentId
     * @return
     */
    List<CClass> selectByDepartmentId(int departmentId);

    /**
     *
     * @param cClass
     */
    void addClass(CClass cClass);

    /**
     *
     * @param id
     */
    void delectClass(int id);
}
