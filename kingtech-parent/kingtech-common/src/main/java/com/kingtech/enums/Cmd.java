package com.kingtech.enums;
/**
 * API字典
 * @author baijt
 *
 */
public enum Cmd implements  BaseEnum{
	
	
	
	loanInfo("loan-info"),//放款信息
	repay("repay"),//还款信息
	pushCompanyEmployeeData("pushCompanyEmployeeData"),// 机构人员信息
	singleFinance("single-finane"),// 单笔融资信息
	monthFinance("month-finane"), // 月度融资余额信息
	bankBalance("bank-balance");// 机构银行账户月度余额信息
	
	
	
	
	
	
	private String key;
	
	private Cmd(String key) {
		this.key = key;
	}



	@Override
	public String getKey() {
		return key;
	}
	
	

}
