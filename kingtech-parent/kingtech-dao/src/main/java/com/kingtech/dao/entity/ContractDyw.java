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
import com.kingtech.enums.DywTypeEnum;

/**
 * 抵押物表
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_LOAN_CONTRACT_DYW")
@NoArgsConstructor
public class ContractDyw extends PackageEntity {
	
	// 抵质押物类型：抵押选项：1-存货抵押, 2-客账抵押, 3-证券抵押, 4-设备抵押, 5-不动产抵押, 6-人寿险抵押, 7-其他 
	@Enumerated(EnumType.STRING)
	@Column(name="PLEDGE_TYPE")
	private DywTypeEnum pledgeType;

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

	public ContractDyw(String loanContractId,int orderNum, DywTypeEnum pledgeType,
					   String name, BigDecimal worth, String address, String unit) {
		super(loanContractId,orderNum);
		this.pledgeType = pledgeType;
		this.name = name;
		this.worth = worth;
		this.address = address;
		this.unit = unit;
	}
}
