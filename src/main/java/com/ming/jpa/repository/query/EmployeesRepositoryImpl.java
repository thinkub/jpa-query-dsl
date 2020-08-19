package com.ming.jpa.repository.query;

import com.ming.jpa.entity.EmployeesEntity;
import com.ming.jpa.entity.QDepartmentEntity;
import com.ming.jpa.entity.QEmployeesEntity;
import com.ming.jpa.entity.QRankEntity;
import com.ming.jpa.model.dto.Employees;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */

public class EmployeesRepositoryImpl extends QuerydslRepositorySupport implements EmployeesQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	private QEmployeesEntity employees = QEmployeesEntity.employeesEntity;
	private QRankEntity rank = QRankEntity.rankEntity;
	private QDepartmentEntity department = QDepartmentEntity.departmentEntity;

	public EmployeesRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		super(EmployeesEntity.class);
		this.queryFactory = jpaQueryFactory;
	}

	@Override
	public List<Employees> findEmployeesList() {
		return queryFactory.from(employees)
				.select(Projections.fields(Employees.class,
						employees.employeesId,
						employees.name,
						department.departmentName,
						rank.rankName))
				.innerJoin(department).on(department.departmentId.eq(employees.departmentId))
				.innerJoin(rank).on(rank.rankId.eq(employees.rankId))
				.fetch();
	}

	@Override
	public Employees findEmployees(Long employeeId) {
		return queryFactory.from(employees)
				.select(Projections.fields(Employees.class,
						employees.employeesId,
						employees.name,
						department.departmentName,
						rank.rankName))
				.innerJoin(department).on(department.departmentId.eq(employees.departmentId))
				.innerJoin(rank).on(rank.rankId.eq(employees.rankId))
				.where(employees.employeesId.eq(employeeId))
				.fetchOne();
	}

	@Override
	public List<Employees> findEmployeesBySearchParam(Employees.Search search) {
		BooleanBuilder criteria = new BooleanBuilder();

		if (search.hasName()) {
			criteria.and(employees.name.contains(search.getName()));
		}
		if (search.hasDepartmentName()) {
			criteria.and(department.departmentName.contains(search.getDepartmentName()));
		}
		if (search.hasRankName()) {
			criteria.and(rank.rankName.contains(search.getRankName()));
		}

		return queryFactory.from(employees)
				.select(Projections.fields(Employees.class,
						employees.employeesId,
						employees.name,
						department.departmentName,
						rank.rankName))
				.innerJoin(department).on(department.departmentId.eq(employees.departmentId))
				.innerJoin(rank).on(rank.rankId.eq(employees.rankId))
				.where(criteria)
				.fetch();
	}
}
