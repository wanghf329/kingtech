package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class SettledInfoModel {

	// 放款金额(元)
	@JSONField(name="money")
	private String money;

	// 放款日期，格式YYYY-MM-DD
	@JSONField(name="loan_date")
	private String loanDate;

	// 本笔放款债项开始日
	@JSONField(name="debt_start_date")
	private String debtStartDate;

	// 本笔放款债项结束日
	@JSONField(name="debt_end_date")
	private String debtEndDate;



}
