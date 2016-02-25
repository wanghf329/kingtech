package com.kingtech.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.UuidEntity;
import com.kingtech.enums.SpecificationsEnum;

/**
 * 流量单价
 * @author: JKWHF
 * @date: 2016年1月28日 下午12:56:42
 * @version: v1.0
 */
@Data
@Entity
@Table(name="TB_RECHARGE_RECORD")
@NoArgsConstructor
@AllArgsConstructor
public class RechargeRecord extends UuidEntity {
	
	// 流量规格
	@Column(name="CONTRACT_NAME")
	private String contractName;

	// 流量单价
	@Column(name="ACTIVITY_NAME")
	private String activityName;

	// 有效期
	@Column(name="ORDER_ID")
	private String orderId;

	// 有效期
	@Column(name="ORDER_NAME")
	private String orderName;

	// 是否启用
	@Column(name="AMOUNT")
	private Double amount;
	
	// 是否启用
	@Column(name="DISCRIPTION")
	private Double discription;	
}
