package com.kingtech.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class SettledInfoModel {
	//合同编号
	private String contractNumber;

	// 放款金额(元)
	@JSONField(name="money")
	private BigDecimal money;

	// 放款日期，格式YYYY-MM-DD
	@JSONField(name="loanTime")
	private String loanTime;

	// 本笔放款债项开始日
	@JSONField(name="startDate")
	private String startDate;

	// 本笔放款债项结束日
	@JSONField(name="endDate")
	private String endDate;
}
