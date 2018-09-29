package com.kingtech.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.BaseEntity;

/**
 * 还款计划表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_SYS_AREA_CODE")
@NoArgsConstructor
public class SysAreaCode extends BaseEntity {
	
	@Id
	@Column(name="code")
	private String code;

	@Column(name="name")
	private String name;
	
	@Column(name="parent_code")
	private String parent_code;	

	@Column(name="level")
	private String level;
	
	@Column(name="code_short")
	private String code_short;

	public SysAreaCode(String code, String name, String parent_code,
			String level, String code_short) {
		super();
		this.code = code;
		this.name = name;
		this.parent_code = parent_code;
		this.level = level;
		this.code_short = code_short;
	}
}
