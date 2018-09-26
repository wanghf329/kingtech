package com.kingtech.szsm.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;


@Data
@NoArgsConstructor
public class ContractRequestModel  extends BaseRequestModel{
	/**
	 * 合同编号
	 */
	@JSONField(label="sign")
	private String contractNumber;
	/**
	 * 合同名称
	 */
	@JSONField( label="sign")
	private String contractName;
	/**
	 * 借款客户类型
	 */
	@JSONField( label="sign")
	private int borrowerType;
	
	/**
	 * 贷款金额(元) 
	 */
	@JSONField( label="sign")
	private String money;
	
	/**
	 * 贷款期限类型
	 */
	@JSONField( label="sign")
	private int termType;
	
	/**
	 * 贷款期限
	 */
	@JSONField( label="sign")
	private int term;
	
	/**
	 * 贷款开始日期，格式YYYY-MM-DD
	 */
	@JSONField( label="sign")
	private String startDate;
	/**
	 * 贷款截止日期，格式YYYY-MM-DD
	 */
	@JSONField( label="sign")
	private String endDate;
	
	/**
	 * 利率类型
	 */
	@JSONField( label="sign")
	private int rateType;
	
	/**
	 * 利率(%)
	 */
	@JSONField( label="sign")
	private String rate;
	
	
	/**
	 * 年化利率(%)
	 */
	private String annualRate;
	
	
	/**
	 * 平台费
	 */
	private String platformCost;
	
	//受托支付
	private int isEntrust;
	
	/**
	 * 贷款用途：1-流动资金贷款  2-固定资产投资贷款  3-其他
	 */
	@JSONField( label="sign")
	private int purpose;
	
	//投向行业
	@JSONField( label="sign")
	private int business;
	
	/**
	 * 贷款方式（多选：1信用，2保证，3抵押，4质押，5其它）用逗号分隔
	 */
	@JSONField( label="sign")
	private String loanMethod;
	
	/**
	 * 还款方式：1）等额本息  2）等额本金  3）先息后本   4）灵活还款
	 */
	@JSONField( label="sign")
	private int repayMethod;
	
	/**
	 * 合同签订日期，格式YYYY-MM-DD HH:mm:SS
	 */
	@JSONField( label="sign")
	private String signTime;
	
	/**
	 * 还款来源
	 */
	@JSONField( label="sign")
	private String repaySource;
	
	/**
	 * 区域代码（区/县）参见excel区域代码（没有区的，取6位），若没有地址自行从营业执照或身份证取
	 */
	private String districtCode;
	
	
	/**
	 * 个人借款人
	 */
	private PersonalCustomerRequestModel personBorrower;
	/**
	 * 企业借款人
	 */
	private EnterpriseCustomerRequestModel enterpriseBorrower;
	/**
	 * 质押集合
	 */
	private List<ContractDywRequestModel> pledgeInfos;
	
	/**
	 * 抵押集合
	 */
	private List<ContractZywRequestModel> mortgageInfos;
	/**
	 * 保证人集合
	 */
	
	private List<GuaranteeRequestModel> ensureInfos;
	/**
	 * 还款计划集合
	 */
	
	private List<RepayPlanRequestModel> repayPlans;
	
	public ContractRequestModel( String roundStr, String reqId,
			String sign,String contractNumber,
			String contractName, int borrowerType, String money, int termType,
			int term, String startDate, String endDate, int rateType,
			String rate, String annualRate, String platformCost, int isEntrust,
			int purpose, int business, String loanMethod, int repayMethod,
			String signTime, String repaySource, String districtCode,
			PersonalCustomerRequestModel personBorrower,
			EnterpriseCustomerRequestModel enterpriseBorrower,
			List<ContractDywRequestModel> pledgeInfos,
			List<ContractZywRequestModel> mortgageInfos,
			List<GuaranteeRequestModel> ensureInfos,
			List<RepayPlanRequestModel> repayPlans) {
		super(BaseConfig.CLIENTID, roundStr, BaseConfig.APPKEY, SignUtils.getToken(roundStr), reqId, sign, null);
		this.contractNumber = contractNumber;
		this.contractName = contractName;
		this.borrowerType = borrowerType;
		this.money = money;
		this.termType = termType;
		this.term = term;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rateType = rateType;
		this.rate = rate;
		this.annualRate = annualRate;
		this.platformCost = platformCost;
		this.isEntrust = isEntrust;
		this.purpose = purpose;
		this.business = business;
		this.loanMethod = loanMethod;
		this.repayMethod = repayMethod;
		this.signTime = signTime;
		this.repaySource = repaySource;
		this.districtCode = districtCode;
		this.personBorrower = personBorrower;
		this.enterpriseBorrower = enterpriseBorrower;
		this.pledgeInfos = pledgeInfos;
		this.mortgageInfos = mortgageInfos;
		this.ensureInfos = ensureInfos;
		this.repayPlans = repayPlans;
	}
	
	
	
	
}
