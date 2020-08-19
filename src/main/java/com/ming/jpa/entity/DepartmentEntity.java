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
@Table(name = "department")
public class DepartmentEntity {

	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;

	@Column(name = "department_name", nullable = false, length = 50)
	private String departmentName;

	public static DepartmentEntity of(String departmentName) {
		return new DepartmentEntity(departmentName);
	}

	private DepartmentEntity(String departmentName) {
		this.departmentName = departmentName;
	}
}
