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
	public void CapitalInfoApi(String capitalId,IdentifierType type);
	

}
