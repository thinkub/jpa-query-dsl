package com.ming.jpa.repository;

import com.ming.jpa.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
