package com.kingtech.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.kingtech.dao.entity.base.RecordEntity;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanMethodEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.TermTypeEnum;
import com.kingtech.enums.YesNoEnum;

/**
 * 合同信息
 * @author XA_JKWHF
 *
 */
@Data
@Entity
@Table(name="TB_LOAN_CONTRACT")
@NoArgsConstructor
public class Contract extends RecordEntity{
	/**
	 * 合同编号
	 */
	@Column(name="CONTRACT_NUMBER")
	private String contractNumber;
	/**
	 * 合同名称
	 */
	@Column(name="CONTRACT_NAME")
	private String contractName;
	/**
	 * 借款客户类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="BORROWER_TYPE")
	private BorrowerTypeEnum BORROWER_TYPE;
	
	/**
	 * 贷款金额(元) 
	 */
	@Column(name="AMOUNT")
	private BigDecimal money;
	
	/**
	 * 贷款期限类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="TERM_TYPE")
	private TermTypeEnum termType;
	
	/**
	 * 贷款期限
	 */
	@Column(name="TERM")
	private int term;
	
	/**
	 * 贷款开始日期，格式YYYY-MM-DD
	 */
	@Column(name="START_DATE")
	private Date startDate;
	/**
	 * 贷款截止日期，格式YYYY-MM-DD
	 */
	@Column(name="END_DATE")
	private Date endDate;
	
	/**
	 * 利率类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="RATE_TYPE")
	private RateTypeEnum rateType;
	
	/**
	 * 利率(%)
	 */
	@Column(name="RATE")
	private BigDecimal rate;
	
	
	/**
	 * 年化利率(%)
	 */
	@Column(name="ANNUAL_RATE")
	private BigDecimal annualRate;
	
	
	/**
	 * 平台费
	 */
	@Column(name="PLATFORM_COST")
	private BigDecimal platformCost;
	
	//受托支付
	@Enumerated(EnumType.STRING)
	@Column(name="ENTRUST")
	private YesNoEnum isEntrust;
	
	/**
	 * 贷款用途：1-流动资金贷款  2-固定资产投资贷款  3-其他
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="PURPOSE")
	private LoanPurposeEnum purpose;
	
	//投向行业
	@Enumerated(EnumType.STRING)
	@Column(name="BUSINESS")
	private IndustryEnum business;
	
	/**
	 * 贷款方式（多选：1信用，2保证，3抵押，4质押，5其它）用逗号分隔
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="LOAN_METHOD")
	private LoanMethodEnum loanMethod;
	
	/**
	 * 还款方式：1）等额本息  2）等额本金  3）先息后本   4）灵活还款
	 */
	@Column(name="REPAY_METHOD")
	private PayTypeEnum repayMethod;
	
	/**
	 * 合同签订日期，格式YYYY-MM-DD HH:mm:SS
	 */
	@Column(name="SIGN_TIME")
	private Date signTime;
	
	/**
	 * 还款来源
	 */
	@Column(name="REPAY_SOURCE")
	private String repaySource;
	
	/**
	 * 区域代码（区/县）参见excel区域代码（没有区的，取6位），若没有地址自行从营业执照或身份证取
	 */
	@Column(name="DISTRICT_CODE")
	private String districtCode;
	
	
	/**
	 * 借款人ID
	 */
	@Column(name="BORROWER_ID")
	private String borrowerId;
}
