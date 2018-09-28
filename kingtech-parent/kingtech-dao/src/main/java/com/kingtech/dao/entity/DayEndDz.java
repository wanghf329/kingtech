package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;

@Data
@Entity
@Table(name="TB_DAYEND_DZ")
@NoArgsConstructor
public class DayEndDz extends RecordEntity{
	//日期
	@Column(name="CHECK_DATE", nullable = false)
	private Date checkDate;
	
	//当日上传合同笔数
	@Column(name="DAY_COUNT", nullable = false)
	private int dayCount;
	
	//当日上传合同总金额
	@Column(name="DAY_MONEY", nullable = false)
	private BigDecimal dayMoney;
	
	//当日放款总金额（元）
	@Column(name="DAY_LOAN", nullable = false)
	private BigDecimal dayLoan;
	
	//当日还款金额（元）
	@Column(name="DAY_REPAY", nullable = false)
	private BigDecimal dayRepay;
	
	//贷款余额（元）
	@Column(name="LOAN_BALANCE", nullable = false)
	private BigDecimal loanBalance;
	
	//贷款总额（元）
	@Column(name="LOAN_MONEY", nullable = false)
	private BigDecimal loanMoney;
	
	//贷款总笔数
	@Column(name="LOAN_COUNT", nullable = false)
	private int loanCount;

	public DayEndDz(String reqId, PushStatus pushStatus,
			RecordStatus recordStatus, Date checkDate, int dayCount,
			BigDecimal dayMoney, BigDecimal dayLoan, BigDecimal dayRepay,
			BigDecimal loanBalance, BigDecimal loanMoney, int loanCount) {
		super(reqId, pushStatus, recordStatus);
		this.checkDate = checkDate;
		this.dayCount = dayCount;
		this.dayMoney = dayMoney;
		this.dayLoan = dayLoan;
		this.dayRepay = dayRepay;
		this.loanBalance = loanBalance;
		this.loanMoney = loanMoney;
		this.loanCount = loanCount;
	}
}
