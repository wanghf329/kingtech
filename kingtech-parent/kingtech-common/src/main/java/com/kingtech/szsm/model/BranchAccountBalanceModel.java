package com.kingtech.szsm.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * @author Tson
 * 机构银行账户月度余额信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchAccountBalanceModel extends BaseRequestModel {
	
	// 年月，格式YYYYMM
	@JSONField(name ="dateMonth", label="sign")
	private String dateMonth;
	
	// 账户号
	@JSONField(name ="account")
	private String account;
	
	// 账户余额（万元）
	@JSONField(name ="money")
	private BigDecimal money;

	public BranchAccountBalanceModel(String id, String dateMonth,
			String account, BigDecimal money) {
		this.dateMonth = dateMonth;
		this.account = account;
		this.money = money;
	}
	
	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public BranchAccountBalanceModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId);
	}


}
