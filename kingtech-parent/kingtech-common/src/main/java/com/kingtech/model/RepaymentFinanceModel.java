package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.PushStatus;

/**
 * @author Tson
 * 单笔融资实际还款信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentFinanceModel extends BaseModel{
	
	// 融资编号
	private String financeNumber;
	
	// 还款日期 格式yyyy-MM-dd
	private Date repayDate;
	
	// 还款本金（元）
	private BigDecimal money;
	
	// 还款利息（元）
	private BigDecimal interest;
	
	// 其它费用（元）
	private BigDecimal charges;
	
	private PushStatus pushStatus;
	
	/**
	 * 删除
	 * @param roundStr
	 * @param identifier
	 * @param reqId
	 */
	public RepaymentFinanceModel( String roundStr,String identifier, String reqId) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId);
	}

	public RepaymentFinanceModel(String id, String financeNumber,
			Date repayDate, BigDecimal money, BigDecimal interest,
			BigDecimal charges, String reqId, PushStatus pushStatus) {
		super(id, reqId);
		this.financeNumber = financeNumber;
		this.repayDate = repayDate;
		this.money = money;
		this.interest = interest;
		this.charges = charges;
		this.pushStatus = pushStatus;
	}
	
	

}
