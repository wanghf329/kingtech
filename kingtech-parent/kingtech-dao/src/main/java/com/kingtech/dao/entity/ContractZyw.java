package com.kingtech.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.PackageEntity;
import com.kingtech.enums.ZywTypeEnum;

/**
 * 质押物表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_CONTRACT_ZYW")
@NoArgsConstructor
public class ContractZyw extends PackageEntity {
	
    // 质押选项：1-股权质押, 2-定期存单质押, 3-专利权质押,4-应收账款质押,5-其他
	@Enumerated(EnumType.STRING)
	@Column(name="PLEDGE_TYPE")
	private ZywTypeEnum pledgeType;

	// 抵质押物名称
	@Column(name="NAME")
	private String name;

	// 评估值(元)
	@Column(name="WORTH")
	private BigDecimal worth;
	
	// 权证所有人
	@Column(name="ADDRESS")
	private String address;
	
	// 数量及单位，例如：10克，1000米，4.5吨...
	@Column(name="UNIT")
	private String unit;

	public ContractZyw(String loanContractId, ZywTypeEnum pledgeType,
			String name, BigDecimal worth, String address, String unit) {
		super(loanContractId);
		this.pledgeType = pledgeType;
		this.name = name;
		this.worth = worth;
		this.address = address;
		this.unit = unit;
	}
}
