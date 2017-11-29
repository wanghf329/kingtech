package com.kingtech.web.commons.base.api;

import com.kingtech.enums.IdentifierType;
import com.kingtech.model.AsyReponseModel;

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
	 * @param employeeId
	 * @param type
	 */
	public void employeeInfoApi(String employeeId,IdentifierType type);
	/**
	 * 机构股东信息
	 * @param holderId
	 * @param type
	 */
	
	public void shareholderInfoApi(String holderId,IdentifierType type);
	/**
	 * 放贷业务合同信息
	 * @param loanIdContract
	 * @param type
	 */
	public void contractInfoApi(String loanIdContract,IdentifierType type);
	
	/**
	 * 处理回调数据
	 * @param reponseModel
	 */
	public void handleResult(AsyReponseModel reponseModel);
	

}
