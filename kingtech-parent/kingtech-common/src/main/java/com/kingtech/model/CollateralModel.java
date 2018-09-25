package com.kingtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollateralModel {
	
	// 担保类型 1-抵押 2-质押
	@JSONField(name ="pledge_type")
	private String pledgeType;

	// 抵质押物类型：抵押选项：1-存货抵押, 2-客账抵押, 3-证券抵押, 4-设备抵押, 5-不动产抵押, 6-人寿险抵押, 7-其他 
    // 质押选项：1-股权质押, 2-定期存单质押, 3-专利权质押,4-应收账款质押,5-其他
	@JSONField(name ="collateral_type")
	private String collateralType;

	// 抵质押物名称
	@JSONField(name ="collateral_name")
	private String collateralName;

	// 权证号
	@JSONField(name ="warrant_num")
	private String warrantNum;
	
	// 评估值(元)
	@JSONField(name ="evaluation_value")
	private String evaluationValue;
	
	// 权证所有人
	@JSONField(name ="warrant_holder")
	private String warrantHolder;
	
	// 抵质押物放置位置
	@JSONField(name ="collateral_addr")
	private String collateralAddr;
	
	// 办理抵质押日期，格式YYYY-MM-DD
	@JSONField(name ="handle_date")
	private String handleDate;

	


}
