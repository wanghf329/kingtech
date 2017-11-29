package com.kingtech.enums;
/**
 * API字典
 * @author baijt
 *
 */
public enum Cmd implements  BaseEnum{
	
	
	
	pushCompanyInformation("机构基本信息接口"),
	pushEmployee("机构人员信息接口"),
	pushInstitutionStockholder("机构股东信息接口"),
	pushInstitutionCapital("机构资本信息接口"),
	pushEnterpriseCustomer("企业客户信息接口"),
	pushPersonalCustomer("个人客户信息接口"),
	pushContract("放贷业务合同信息接口"),
	pushRepayPlan("还款计划信息接口"),
	pushMortgage("抵质押物信息接口"),
	pushGuarantee("保证人信息接口"),
	pushLending("放款信息接口"),
	pushRepay("还款信息接口"),
	pushExtendPlan("展期还款计划信息接口"),
	pushExtendRepay("展期还款信息接口"),
	pushBadDebt("展期还款信息接口"),
	pushOverdue("逾期信息接口"),
	pushProvision("计提信息接口"),
	pushDayReport("日清数据对账接口");
	
	
	
	
	
	
	
	private String key;
	
	private Cmd(String key) {
		this.key = key;
	}



	@Override
	public String getKey() {
		return key;
	}
	
	

}
