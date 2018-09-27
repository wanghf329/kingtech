package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * 还款信息
 * 
 * @author baijt
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepayInfoModel extends BaseModel {
	// 还款日期
	private Date repayTime;

	// 还款本金
	private BigDecimal money;

	// 还款利息
	private BigDecimal interest;

	// 罚息
	private BigDecimal penaltyInterest;

	// 违约金
	private BigDecimal penalty;

	// 服务费
	private BigDecimal serviceCharge;

	// 其他费用
	private BigDecimal otherCharge;
	
	public RepayInfoModel(String id, Date repayTime,
			BigDecimal money, BigDecimal interest, BigDecimal penaltyInterest,
			BigDecimal penalty, BigDecimal serviceCharge, BigDecimal otherCharge) {
		super(id);
		this.repayTime = repayTime;
		this.money = money;
		this.interest = interest;
		this.penaltyInterest = penaltyInterest;
		this.penalty = penalty;
		this.serviceCharge = serviceCharge;
		this.otherCharge = otherCharge;
	}
}
