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
 * 机构银行账户月度余额信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchAccountBalanceModel extends BaseModel {
	
	// 年月，格式YYYYMM
	private String dateMonth;
	
	// 账户号
	private String account;
	
	// 账户余额（万元）
	private BigDecimal money;
	
	private PushStatus pushStatus;

	public BranchAccountBalanceModel(String id, String dateMonth,
			String account, BigDecimal money, PushStatus pushStatus, String reqId) {
		super(id, reqId);
		this.dateMonth = dateMonth;
		this.account = account;
		this.money = money;
		this.pushStatus = pushStatus;
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
