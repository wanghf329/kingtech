package com.kingtech.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;

/**
 * @author Tson
 * 单笔融资实际还款信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentFinanceModel extends BaseRequestModel{
	
	// 融资编号
	@JSONField(name="financeNumber", label="sign")
	private String financeNumber;
	
	// 还款日期 格式yyyy-MM-dd
	@JSONField(name="repayDate", label="sign")
	private String repayDate;
	
	// 还款本金（元）
	@JSONField(name="money", label="sign")
	private String money;
	
	// 还款利息（元）
	@JSONField(name="interest", label="sign")
	private String interest;
	
	// 其它费用（元）
	@JSONField(name="charges", label="sign")
	private String charges;
	
	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public RepaymentFinanceModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), identifier, reqId);
	}

}
