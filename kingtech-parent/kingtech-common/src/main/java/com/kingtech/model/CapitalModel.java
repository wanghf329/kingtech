package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

import com.kingtech.enums.ChannelTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RateTypeEnum;

/**
 * 单笔融资信息
 * @author wangyu
 * @version: v1.0
 */
@Data
public class CapitalModel extends BaseModel {
	// 融资编号
	private String financeNumber;
	
	// 融资名称
	private String financeName;
	
	// 出借方名称
	private String lender;
	
	// 融资渠道
	@Enumerated(EnumType.STRING)
	private ChannelTypeEnum channel;

	// 融资金额（元）
	private BigDecimal money;
	
	// 应付利息
	private BigDecimal interest;
	
	// 手续费
	private BigDecimal charge;
	
	// 合同担保金额
	private BigDecimal guaranteeMoney;
	
	// 备注
	private String remark;

	// 融资日期
	private Date financeDate;
	
	// 到期日期
	private Date endDate;
	
	// 融资利率
	private BigDecimal rate;
	
	// 利率类型
	private RateTypeEnum rateType;
	
	// 合同编号
	private String loanContractNumber;
	
	private PushStatus pushStatus;

	public CapitalModel(String id, String financeNumber, String financeName,
			String lender, ChannelTypeEnum channel, BigDecimal money,
			BigDecimal interest, BigDecimal charge, BigDecimal guaranteeMoney,
			String remark, Date financeDate, Date endDate, BigDecimal rate,
			RateTypeEnum rateType, String loanContractNumber, String reqId,
			PushStatus pushStatus) {
		super(id, reqId);
		this.financeNumber = financeNumber;
		this.financeName = financeName;
		this.lender = lender;
		this.channel = channel;
		this.money = money;
		this.interest = interest;
		this.charge = charge;
		this.guaranteeMoney = guaranteeMoney;
		this.remark = remark;
		this.financeDate = financeDate;
		this.endDate = endDate;
		this.rate = rate;
		this.rateType = rateType;
		this.loanContractNumber = loanContractNumber;
		this.pushStatus = pushStatus;
	}

	
	

	
	
	
}
