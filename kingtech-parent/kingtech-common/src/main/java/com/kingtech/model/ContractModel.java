package com.kingtech.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.alibaba.fastjson.annotation.JSONField;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.SignUtils;


@Data
@NoArgsConstructor
public class ContractModel  extends BaseRequestModel{
	/**
	 * 合同编号
	 */
	@JSONField(name="loan_contract_id",label ="sign")
	private String loanContractId;
	/**
	 * 合同名称
	 */
	@JSONField(name="loan_contract_name",label ="sign")
	private String loanContractName;
	/**
	 * 借款客户名称类型
	 */
	@JSONField(name="borrower_type",label ="sign")
	private String borrowerType;
	
	/**
	 * 营业执照号或证件号码关联
	 */
	@JSONField(name="customer_id",label ="sign")
	private String customerId;
	
	/**
	 * 担保方
	 */
	@JSONField(name="guarantee")
	private String guarantee;
	/**
	 * 贷款金额(元) 
	 */
	@JSONField(name="loan_amount",label ="sign")
	private String loanAmount;
	/**
	 * 贷款期限类型：1-月 2-日 3-周  4-季度  5-年
	 */
	@JSONField(name="period_type",label ="sign")
	private String periodType;
	/**
	 * 贷款期限
	 */
	@JSONField(name="period_term",label ="sign")
	private String periodTerm;
	/**
	 * 贷款开始日期，格式YYYY-MM-DD
	 */
	@JSONField(name="loan_start_date",label ="sign")
	private String loanStartDate;
	/**
	 * 贷款截止日期，格式YYYY-MM-DD
	 */
	@JSONField(name="loan_end_date",label ="sign")
	private String loanEndDate;
	/**
	 * 利率类型：1-年 2-日  3-月  4-周  5-季
	 */
	@JSONField(name="rate_type",label ="sign")
	private String rateType;
	/**
	 * 利率(%)（参与签名）
	 */
	@JSONField(name="rate",label ="sign")
	private String rate;
	/**
	 * 贷款用途：1-流动资金贷款  2-固定资产投资贷款  3-其他
	 */
	@JSONField(name="purpose",label ="sign")
	private String  purpose;
	/**
	 * 贷款投向：1-居民服务和其他服务业,2-建筑业, 3-交通运输、仓储和邮政业, 4-农、林、牧、渔业, 5-采矿业, 6-制造业, 7-电力、燃气及水的生产和供应业, 8-信息传输、计算机服务和软件业, 9-批发和零售业,10-住宿和餐饮业, 11-房地产业, 12-租赁和商务服务业, 13-其他（参与签名）
	 */
	@JSONField(name="industry",label ="sign")
	private String industry;
	/**
	 * 贷款方式：1-信用  2-保证  3-抵押   4-质押  5-其他
	 */
	@JSONField(name="loan_type",label ="sign")
	private String loanType;
	/**
	 * 是否多户联合贷款，只有当贷款方式为“保证”类是否多户联合贷款，只有当贷款方式为“保证”类
	 */
	@JSONField(name="union_flag")
	private String unionFlag;
	
	/**
	 * 还款方式：1）等额本息  2）等额本金  3）先息后本   4）灵活还款
	 */
	@JSONField(name="pay_type",label ="sign")
	private String payType;
	/**
	 * 合同签订日期，格式YYYY-MM-DD HH:mm:SS
	 */
	@JSONField(name="sign_date",label ="sign")
	private String signDate;
	/**
	 * 还款来源
	 */
	@JSONField(name="repay_source",label ="sign")
	private String repaySource;
	/**
	 * 合同状态：未结清，已结清，逾期未结清，逾期已结清
	 */
	@JSONField(name="status")
	private String status;
	/**
	 * 是否展期：0-否，1-是
	 */
	@JSONField(name="is_extend")
	private String isExtend;
	/**
	 * 创建时间，如2016-01-01 11:12:30
	 */
	@JSONField(name="create_time")
	private String createTime;
	/**
	 * 修改时间，如2016-01-01 11:12:30
	 */
	@JSONField(name="update_time")
	private String updateTime;
	/**
	 * 客户信息
	 */
	@JSONField(name="loan_customer_package")
	private String loanCustomerPackage;
	/**
	 * [{},{}],不存在抵质押物，不出现
	 */
	@JSONField(name="collateral_package")
	private String collateralPackage;
	/**
	 * [{},{}],不存在保证人不出现
	 */
	@JSONField(name="guaranteePackage")
	private String guarantee_package;
	/**
	 * 还款
	 */
	@JSONField(name="repay_plan_package")
	private String repayPlanPackage;
	/**
	 * {}，多次放款的需要多次调用这个接口，如果合同信息已存在，只会添加放款信息
	 */
	@JSONField(name="lending_package")
	private String lendingPackage;
	
	public ContractModel( String roundStr, String identifier, String reqId, String sign,
			String id, String loanContractId, String loanContractName,
			String borrowerType, String customerId, String guarantee,
			String loanAmount, String periodType, String periodTerm,
			String loanStartDate, String loanEndDate, String rateType,
			String rate, String purpose, String industry, String loanType,
			String unionFlag, String payType, String signDate,
			String repaySource, String status, String isExtend,
			String createTime, String updateTime, String loanCustomerPackage,
			String collateralPackage, String guarantee_package,
			String repayPlanPackage, String lendingPackage) {
		super(BaseConfig.CLIENTID, roundStr,BaseConfig.APPKEY,SignUtils.getToken(roundStr), identifier, reqId, sign,null);
		this.loanContractId = loanContractId;
		this.loanContractName = loanContractName;
		this.borrowerType = borrowerType;
		this.customerId = customerId;
		this.guarantee = guarantee;
		this.loanAmount = loanAmount;
		this.periodType = periodType;
		this.periodTerm = periodTerm;
		this.loanStartDate = loanStartDate;
		this.loanEndDate = loanEndDate;
		this.rateType = rateType;
		this.rate = rate;
		this.purpose = purpose;
		this.industry = industry;
		this.loanType = loanType;
		this.unionFlag = unionFlag;
		this.payType = payType;
		this.signDate = signDate;
		this.repaySource = repaySource;
		this.status = status;
		this.isExtend = isExtend;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.loanCustomerPackage = loanCustomerPackage;
		this.collateralPackage = collateralPackage;
		this.guarantee_package = guarantee_package;
		this.repayPlanPackage = repayPlanPackage;
		this.lendingPackage = lendingPackage;
	}
	
	
	
	

}
