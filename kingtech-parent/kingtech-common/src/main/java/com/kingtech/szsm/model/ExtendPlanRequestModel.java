package com.kingtech.szsm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendPlanRequestModel {
	
	
	// 展期后的新还款利息（元）
	private String interest;
	
	//展期后的新还款日期
	private String endDate;
	
	// 展期后的新还款本金（元）
	private String principal;

}
