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
	baddebt("baddebt"),//坏账信息
	pushCompanyEmployeeData("pushCompanyEmployeeData"),// 机构人员信息
	dayEndReport("day-report"),//日终数据对账
	singleFinance("single-finane");// 单笔融资信息
	
	
	
	
	
	
	
	private String key;
	
	private Cmd(String key) {
		this.key = key;
	}



	@Override
	public String getKey() {
		return key;
	}
	
	

}
