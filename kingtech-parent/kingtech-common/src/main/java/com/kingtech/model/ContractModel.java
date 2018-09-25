package com.kingtech.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanMethodEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.TermTypeEnum;
import com.kingtech.enums.YesNoEnum;


@Data
@NoArgsConstructor
public class ContractModel  extends BaseRequestModel{
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 借款客户类型
	 */
	@Enumerated(EnumType.STRING)
	private BorrowerTypeEnum borrowerType;
	
	/**
	 * 贷款金额(元) 
	 */
	private BigDecimal money;
	
	/**
	 * 贷款期限类型
	 */
	@Enumerated(EnumType.STRING)
	private TermTypeEnum termType;
	
	/**
	 * 贷款期限
	 */
	private int term;
	
	/**
	 * 贷款开始日期，格式YYYY-MM-DD
	 */
	private Date startDate;
	/**
	 * 贷款截止日期，格式YYYY-MM-DD
	 */
	private Date endDate;
	
	/**
	 * 利率类型
	 */
	@Enumerated(EnumType.STRING)
	private RateTypeEnum rateType;
	
	/**
	 * 利率(%)
	 */
	private BigDecimal rate;
	
	
	/**
	 * 年化利率(%)
	 */
	private BigDecimal annualRate;
	
	
	/**
	 * 平台费
	 */
	private BigDecimal platformCost;
	
	//受托支付
	@Enumerated(EnumType.STRING)
	private YesNoEnum isEntrust;
	
	/**
	 * 贷款用途：1-流动资金贷款  2-固定资产投资贷款  3-其他
	 */
	@Enumerated(EnumType.STRING)
	private LoanPurposeEnum purpose;
	
	//投向行业
	@Enumerated(EnumType.STRING)
	private IndustryEnum business;
	
	/**
	 * 贷款方式（多选：1信用，2保证，3抵押，4质押，5其它）用逗号分隔
	 */
	@Enumerated(EnumType.STRING)
	private LoanMethodEnum loanMethod;
	
	/**
	 * 还款方式：1）等额本息  2）等额本金  3）先息后本   4）灵活还款
	 */
	private PayTypeEnum repayMethod;
	
	/**
	 * 合同签订日期，格式YYYY-MM-DD HH:mm:SS
	 */
	private Date signTime;
	
	/**
	 * 还款来源
	 */
	private String repaySource;
	
	/**
	 * 区域代码（区/县）参见excel区域代码（没有区的，取6位），若没有地址自行从营业执照或身份证取
	 */
	private String districtCode;
	
	
	/**
	 * 借款人ID
	 */
	private String borrowerId;
}
