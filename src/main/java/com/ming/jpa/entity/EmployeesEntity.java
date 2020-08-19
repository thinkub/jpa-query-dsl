package com.ming.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "employees")
public class EmployeesEntity {

	@Id
	@Column(name = "employees_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeesId;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "rank_id")
	private Long rankId;

	public static EmployeesEntity of(String name, Long departmentId, Long rankId) {
		return new EmployeesEntity(name, departmentId, rankId);
	}

	private EmployeesEntity(String name, Long departmentId, Long rankId) {
		this.name = name;
		this.departmentId = departmentId;
		this.rankId = rankId;
	}
}
