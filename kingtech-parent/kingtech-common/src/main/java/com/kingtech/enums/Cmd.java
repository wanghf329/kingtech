package com.kingtech.enums;
/**
 * API字典
 * @author baijt
 *
 */
public enum Cmd implements  BaseEnum{
	
	
	
	loanInfo("loan-info"),//放款信息
	repay("repay"),//还款信息
	baddebt("contract-loss"),//坏账信息
	provision("asset-loss"),//计提信息
	assetTransfer("asset-transfer"),//资产转让
	pushCompanyEmployeeData("pushCompanyEmployeeData"),// 机构人员信息
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
