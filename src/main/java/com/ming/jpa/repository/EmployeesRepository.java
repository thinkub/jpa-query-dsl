package com.ming.jpa.repository;

import com.ming.jpa.entity.EmployeesEntity;
import com.ming.jpa.repository.query.EmployeesQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */
public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Long>, EmployeesQueryDslRepository {
	List<EmployeesEntity> findByRankId(Long rankId);

	List<EmployeesEntity> findByDepartmentId(Long departmentId);
}
