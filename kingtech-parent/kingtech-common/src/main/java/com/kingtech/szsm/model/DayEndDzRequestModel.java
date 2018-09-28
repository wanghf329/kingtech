package com.kingtech.szsm.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.ext.ModelExt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DayEndDzRequestModel extends BaseRequestModel {
	//日期
	@JSONField( label = "sign")
	private String checkDate;
	
	//当日上传合同笔数
	@JSONField( label = "sign")
	private int dayCount;
	
	//当日上传合同总金额
	@JSONField( label = "sign")
	private String dayMoney;
	
	//当日放款总金额（元）
	@JSONField( label = "sign")
	private String dayLoan;
	
	//当日还款金额（元）
	@JSONField( label = "sign")
	private String dayRepay;
	
	//贷款余额（元）
	@JSONField( label = "sign")
	private String loanBalance;
	
	//贷款总额（元）
	@JSONField( label = "sign")
	private String loanMoney;
	
	//贷款总笔数
	@JSONField( label = "sign")
	private int loanCount;

	public DayEndDzRequestModel(String roundStr, String reqId,String checkDate, int dayCount,
			String dayMoney, String dayLoan, String dayRepay,
			String loanBalance, String loanMoney, int loanCount) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.checkDate = checkDate;
		this.dayCount = dayCount;
		this.dayMoney = dayMoney;
		this.dayLoan = dayLoan;
		this.dayRepay = dayRepay;
		this.loanBalance = loanBalance;
		this.loanMoney = loanMoney;
		this.loanCount = loanCount;
	}

	public DayEndDzRequestModel(String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);

	
	}

	
}
