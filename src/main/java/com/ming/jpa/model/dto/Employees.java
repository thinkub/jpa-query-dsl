package com.ming.jpa.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @Author by mhlee(mhlee@saltlux.com) on 2020-08-19
 */

@Getter
public class Employees {
	private Long employeesId;
	private String name;
	private String departmentName;
	private String rankName;

	@Getter
	@Builder(access = AccessLevel.PRIVATE)
	public static class Search {
		private String name;
		private String departmentName;
		private String rankName;

		public static Search of(String name, String departmentName, String rankName) {
			return Search.builder()
					.name(name)
					.departmentName(departmentName)
					.rankName(rankName)
					.build();
		}

		public boolean hasName() {
			return !StringUtils.isEmpty(this.name);
		}

		public boolean hasDepartmentName() {
			return !StringUtils.isEmpty(this.departmentName);
		}

		public boolean hasRankName() {
			return !StringUtils.isEmpty(this.rankName);
		}
	}
}
