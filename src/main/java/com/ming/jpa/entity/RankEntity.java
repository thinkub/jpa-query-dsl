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
@Table(name = "company_rank")
public class RankEntity {

	@Id
	@Column(name = "rank_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rankId;

	@Column(name = "rank_name", nullable = false, length = 50)
	private String rankName;

	public static RankEntity of(String rankName) {
		return new RankEntity(rankName);
	}

	private RankEntity(String rankName) {
		this.rankName = rankName;
	}
}
