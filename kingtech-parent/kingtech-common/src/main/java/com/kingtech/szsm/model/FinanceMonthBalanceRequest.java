package com.kingtech.szsm.model;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.PushStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Tson
 * 月度融资余额信息
 */
@Data
@NoArgsConstructor
public class FinanceMonthBalanceRequest extends BaseRequestModel {
	
	
	// 融资年月 格式YYYY-MM
	@JSONField(name="financeMonth", label="sign")
	private String financeMonth;
	
	// 融资余额（万元）
	@JSONField(name="balance")
	private String balance;
	

	public FinanceMonthBalanceRequest(String roundStr, String reqId,String financeMonth, String balance) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
		this.financeMonth = financeMonth;
		this.balance = balance;
	}
	
	public FinanceMonthBalanceRequest(String roundStr, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, null, null);
	}
	


}
