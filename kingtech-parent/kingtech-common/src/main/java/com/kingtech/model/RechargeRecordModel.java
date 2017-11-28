package com.kingtech.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 充值记录
 * @author: JKWHF
 * @date: 2016年1月28日 下午12:56:42
 * @version: v1.0
 */
@Data
@SuppressWarnings("serial")
@NoArgsConstructor
public class RechargeRecordModel extends BaseRequestModel {
	
	private String id;
	
	private String contractName;

	private String activityName;

	private String orderId;

	private String orderName;

	private Double amount;
	
	private Double discription;

	public RechargeRecordModel(String id, String contractName,
			String activityName, String orderId, String orderName,
			Double amount, Double discription) {
		super();
		this.id = id;
		this.contractName = contractName;
		this.activityName = activityName;
		this.orderId = orderId;
		this.orderName = orderName;
		this.amount = amount;
		this.discription = discription;
	}	
}
