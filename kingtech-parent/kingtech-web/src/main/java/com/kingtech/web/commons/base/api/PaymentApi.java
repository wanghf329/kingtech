package com.kingtech.web.commons.base.api;

import com.kingtech.enums.IdentifierType;

public interface PaymentApi {
	
	/**
	 * 机构基本信息
	 * @param branchId
	 * @param type
	 */
	public void branchInfoApi(String branchId,IdentifierType type);
	/**
	 * 资本信息
	 * @param capitalId
	 * @param type
	 */
	public void capitalInfoApi(String capitalId,IdentifierType type);
	
	/**
	 * 机构人员信息
	 * @param capitalId
	 * @param type
	 */
	public void employeeInfoApi(String capitalId,IdentifierType type);
	/**
	 * 机构股东信息
	 * @param holder
	 * @param type
	 */
	
	public void shareholderInfoApi(String holder,IdentifierType type);
	

}
