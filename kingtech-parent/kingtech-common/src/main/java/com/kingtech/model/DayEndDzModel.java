package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.kingtech.enums.PushStatus;
import com.kingtech.model.ext.ModelExt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DayEndDzModel extends ModelExt {
	//日期
	private Date checkDate;
	
	//当日上传合同笔数
	private int dayCount;
	
	//当日上传合同总金额
	private BigDecimal dayMoney;
	
	//当日放款总金额（元）
	private BigDecimal dayLoan;
	
	//当日还款金额（元）
	private BigDecimal dayRepay;
	
	//贷款余额（元）
	private BigDecimal loanBalance;
	
	//贷款总额（元）
	private BigDecimal loanMoney;
	
	//贷款总笔数
	private int loanCount;

	public DayEndDzModel(PushStatus pushStatus, Date checkDate,
			int dayCount, BigDecimal dayMoney, BigDecimal dayLoan,
			BigDecimal dayRepay, BigDecimal loanBalance, BigDecimal loanMoney,
			int loanCount) {
		super(null, null, null, pushStatus);
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
