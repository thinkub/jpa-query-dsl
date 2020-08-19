package com.ming.jpa.repository.query;

import com.ming.jpa.model.dto.Employees;

import java.util.List;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */
public interface EmployeesQueryDslRepository {
	List<Employees> findEmployeesList();

	Employees findEmployees(Long employeeId);

	List<Employees> findEmployeesBySearchParam(Employees.Search search);
}
