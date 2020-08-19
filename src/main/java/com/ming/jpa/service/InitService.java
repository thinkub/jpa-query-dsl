package com.ming.jpa.service;

import com.ming.jpa.entity.DepartmentEntity;
import com.ming.jpa.entity.EmployeesEntity;
import com.ming.jpa.entity.RankEntity;
import com.ming.jpa.repository.DepartmentRepository;
import com.ming.jpa.repository.EmployeesRepository;
import com.ming.jpa.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */

@Service
@RequiredArgsConstructor
public class InitService {
	private final RankRepository rankRepository;
	private final EmployeesRepository employeesRepository;
	private final DepartmentRepository departmentRepository;

	@Transactional
	public void init() {
		List<RankEntity> rankEntities = initRank();
		List<DepartmentEntity> departmentEntities = initDepartment();

		for (int i = 0; i < 60; i++) {
			RankEntity rankEntity = rankEntities.get(getRandomIndex());
			DepartmentEntity departmentEntity = departmentEntities.get(getRandomIndex());

			EmployeesEntity employeesEntity =
					EmployeesEntity.of(rankEntity.getRankName() + "_" + i,
							departmentEntity.getDepartmentId(),
							rankEntity.getRankId());

			employeesRepository.save(employeesEntity);
		}
	}

	private List<RankEntity> initRank() {
		List<RankEntity> rankEntities = Arrays.asList(RankEntity.of("사원"),
				RankEntity.of("대리"),
				RankEntity.of("과장"),
				RankEntity.of("차장"),
				RankEntity.of("부장"),
				RankEntity.of("이사"));

		rankRepository.saveAll(rankEntities);
		return rankEntities;
	}

	private List<DepartmentEntity> initDepartment() {
		List<DepartmentEntity> departmentEntities = Arrays.asList(DepartmentEntity.of("인사팀"),
				DepartmentEntity.of("경영지원팀"),
				DepartmentEntity.of("사업팀"),
				DepartmentEntity.of("회계팀"),
				DepartmentEntity.of("개발팀"),
				DepartmentEntity.of("기획팀"));
		departmentRepository.saveAll(departmentEntities);
		return departmentEntities;
	}

	private int getRandomIndex() {
		Random generator = new Random();
		return generator.nextInt(6);
	}
}
