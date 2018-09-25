package com.kingtech.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.enums.DywTypeEnum;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractDywModel {
	
	private DywTypeEnum pledgeType;

	// 抵质押物名称
	private String name;

	// 评估值(元)
	private BigDecimal worth;
	
	// 权证所有人
	private String address;
	
	// 数量及单位，例如：10克，1000米，4.5吨...
	private String unit;
}
