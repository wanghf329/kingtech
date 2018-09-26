package com.kingtech.model;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Tson
 * 月度融资余额信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceMonthBalanceModel extends BaseRequestModel {
	
	// 融资编号
	@JSONField(name="financeNumber")
	private String financeNumber;
	
	// 融资年月 格式YYYY-MM
	@JSONField(name="financeMonth", label="sign")
	private String financeMonth;
	
	// 融资余额（万元）
	@JSONField(name="balance")
	private BigDecimal balance;
	
	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public FinanceMonthBalanceModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId);
	}

}
