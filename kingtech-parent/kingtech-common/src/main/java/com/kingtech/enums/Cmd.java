package com.kingtech.enums;
/**
 * API字典
 * @author baijt
 *
 */
public enum Cmd implements  BaseEnum{
	
	
	
	contractInfo("loan-contract"),//合同信息
	loanInfo("loan-info"),//放款信息
	repay("repay"),//还款信息
	baddebt("contract-loss"),//坏账信息
	provision("asset-loss"),//计提信息
	assetTransfer("asset-transfer"),//资产转让
	pushCompanyEmployeeData("pushCompanyEmployeeData"),// 机构人员信息
	monthFinance("month-finane"), // 月度融资余额信息
	bankBalance("bank-balance"),// 机构银行账户月度余额信息
	dayEndReport("day-report"),//日终数据对账
	account("account"), // 机构银行账户信息
	singleFinance("single-finane"),// 单笔融资信息
	extendRepayPlan("extend-repay-plan"),//展期还款计划信息
	extendRepay("extend-repay");//展期还款信息
	
	
	
	
	
	
	
	private String key;
	
	private Cmd(String key) {
		this.key = key;
	}



	@Override
	public String getKey() {
		return key;
	}
	
	

}
