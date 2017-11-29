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
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.UnionFlagEnum;

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
	@Column(name="LOAN_CONTRACT_ID")
	private String loanContractId;
	/**
	 * 合同名称
	 */
	@Column(name="LOAN_CONTRACT_NAME")
	private String loanContractName;
	/**
	 * 借款客户名称类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="BORROWER_TYPE")
	private BorrowerTypeEnum borrowerType;
	
	/**
	 * 营业执照号或证件号码关联
	 */
	@Column(name="CUSTOMER_ID")
	private String customerId;
	
	/**
	 * 担保方
	 */
	@Column(name="GUARANTEE")
	private String guarantee;
	/**
	 * 贷款金额(元) 
	 */
	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;
	/**
	 * 贷款期限类型：1-月 2-日 3-周  4-季度  5-年
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="PERIOD_TYPE")
	private PeriodTypeEnum periodType;
	/**
	 * 贷款期限
	 */
	@Column(name="PERIOD_TERM")
	private int periodTerm;
	/**
	 * 贷款开始日期，格式YYYY-MM-DD
	 */
	@Column(name="LOAN_START_DATE")
	private Date loanStartDate;
	/**
	 * 贷款截止日期，格式YYYY-MM-DD
	 */
	@Column(name="LOAN_END_DATE")
	private Date loanEndDate;
	/**
	 * 利率类型：1-年 2-日  3-月  4-周  5-季
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="RATE_TYPE")
	private RateTypeEnum rateType;
	/**
	 * 利率(%)（参与签名）
	 */
	@Column(name="RATE")
	private BigDecimal rate;
	/**
	 * 贷款用途：1-流动资金贷款  2-固定资产投资贷款  3-其他
	 */
	@Column(name="PURPOSE")
	private String  purpose;
	/**
	 * 贷款投向：1-居民服务和其他服务业,2-建筑业, 3-交通运输、仓储和邮政业, 4-农、林、牧、渔业, 5-采矿业, 6-制造业, 7-电力、燃气及水的生产和供应业, 8-信息传输、计算机服务和软件业, 9-批发和零售业,10-住宿和餐饮业, 11-房地产业, 12-租赁和商务服务业, 13-其他（参与签名）
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="INDUSTRY")
	private IndustryEnum industry;
	/**
	 * 贷款方式：1-信用  2-保证  3-抵押   4-质押  5-其他
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="LOAN_TYPE")
	private LoanTypeEnum loanType;
	/**
	 * 是否多户联合贷款，只有当贷款方式为“保证”类是否多户联合贷款，只有当贷款方式为“保证”类
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="union_flag")
	private UnionFlagEnum unionFlag;
	
	/**
	 * 还款方式：1）等额本息  2）等额本金  3）先息后本   4）灵活还款
	 */
	@Column(name="pay_type")
	private String payType;
	/**
	 * 合同签订日期，格式YYYY-MM-DD HH:mm:SS
	 */
	@Column(name="sign_date")
	private String signDate;
	/**
	 * 还款来源
	 */
	@Column(name="repay_source")
	private String repaySource;
	/**
	 * 合同状态：未结清，已结清，逾期未结清，逾期已结清
	 */
	@Column(name="status")
	private String status;
	/**
	 * 是否展期：0-否，1-是
	 */
	@Column(name="is_extend")
	private String isExtend;
}
