package com.kingtech.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.PushStatus;


/**
 * @author Tson
 * 月度融资余额信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceMonthBalanceModel extends BaseModel {
	
	// 融资年月 格式YYYY-MM
	private String financeMonth;
	
	// 融资余额（万元）
	private BigDecimal balance;
	
	private PushStatus pushStatus;

	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public FinanceMonthBalanceModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId);
	}

	public FinanceMonthBalanceModel(String id, 
			String financeMonth, BigDecimal balance, PushStatus pushStatus, String reqId) {
		super(id, reqId);
		this.financeMonth = financeMonth;
		this.balance = balance;
		this.pushStatus = pushStatus;
	}
	
	

}
