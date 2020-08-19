package com.ming.jpa.repository;

import com.ming.jpa.entity.DepartmentEntity;
import com.ming.jpa.entity.EmployeesEntity;
import com.ming.jpa.entity.RankEntity;
import com.ming.jpa.model.dto.Employees;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */

@SpringBootTest
class EmployeesRepositoryTest {
	@Autowired
	private EmployeesRepository employeesRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RankRepository rankRepository;

	@Test
	@DisplayName("query-dsl 을 이용한 Employees 리스트 전체 조회")
	void queryDslEmployeesSelectTest() {
		// given
		List<EmployeesEntity> employeesEntities = employeesRepository.findAll();

		// when
		List<Employees> employees = employeesRepository.findEmployeesList();

		// then
		assertAll("size비교",
				() -> assertThat(employeesEntities.size()).isEqualTo(employees.size()));
	}

	@Test
	@DisplayName("query-dsl 을 이용한 Employees 리스트 조회 결과 검증")
	void queryDslEmployeesSelectByEmployeeId() {
		// given
		long employeesId = 1L;

		EmployeesEntity employeesEntity = employeesRepository.findById(employeesId).orElseThrow(RuntimeException::new);
		DepartmentEntity departmentEntity = departmentRepository.findById(employeesEntity.getDepartmentId()).orElseThrow(RuntimeException::new);
		RankEntity rankEntity = rankRepository.findById(employeesEntity.getRankId()).orElseThrow(RuntimeException::new);

		// when
		Employees employees = employeesRepository.findEmployees(employeesId);

		// then
		assertAll("사원 비교",
				() -> assertThat(employeesEntity.getName()).isEqualTo(employees.getName()),
				() -> assertThat(departmentEntity.getDepartmentName()).isEqualTo(employees.getDepartmentName()),
				() -> assertThat(rankEntity.getRankName()).isEqualTo(employees.getRankName())
		);
	}

	@Test
	@DisplayName("query-dsl 을 이용한 rankName으로 사원리스트 조회하기")
	void queryDslEmployeesSelectByRankName() {
		// given
		RankEntity rankEntity = rankRepository.findById(1L).orElseThrow(RuntimeException::new);
		List<EmployeesEntity> employeesEntities = employeesRepository.findByRankId(rankEntity.getRankId());

		// when
		Employees.Search search = Employees.Search.of(null, null, rankEntity.getRankName());
		List<Employees> employees = employeesRepository.findEmployeesBySearchParam(search);

		// then
		assertAll("size비교",
				() -> assertThat(employeesEntities.size()).isEqualTo(employees.size()));
	}

	@Test
	@DisplayName("query-dsl 을 이용한 departmentName으로 사원리스트 조회하기")
	void queryDslEmployeesSelectByDepartmentName() {
		// given
		DepartmentEntity departmentEntity = departmentRepository.findById(1L).orElseThrow(RuntimeException::new);
		List<EmployeesEntity> employeesEntities = employeesRepository.findByDepartmentId(departmentEntity.getDepartmentId());

		// when
		Employees.Search search = Employees.Search.of(null, departmentEntity.getDepartmentName(), null);
		List<Employees> employees = employeesRepository.findEmployeesBySearchParam(search);

		// then
		assertAll("size비교",
				() -> assertThat(employeesEntities.size()).isEqualTo(employees.size()));
	}

}