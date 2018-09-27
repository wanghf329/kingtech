package com.kingtech.web.commons.base.api;

import com.kingtech.enums.IdentifierType;
import com.kingtech.szsm.model.AsyReponseModel;
import com.kingtech.szsm.model.SynResponseModel;

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
	public SynResponseModel contractInfoApi(String loanIdContract,IdentifierType type);
	
	/**
	 * 还款计划
	 * @param repayInfoId
	 * @param type
	 */
	
	public void repayInfoApi(String repayInfoId,IdentifierType type);
	
	/**
	 * 展期还款信息
	 */
	
	public SynResponseModel repayExtendInfoApi(String repayExtendInfoId,IdentifierType type);
	
	

	/**
	 * 展期还款计划信息
	 */
	
	public SynResponseModel repayExtendPlanApi(String repayExtendPlanId,IdentifierType type);
	
	
	/**
	 * 坏账信息接口
	 * @param otherBaddebtModel
	 * @return
	 */
	public void otherBaddebtApi(String otherBaddebtId,IdentifierType type);
	/**
	 * 逾期信息
	 * @param otherOverdueInfoModel
	 * @return
	 */
	
	public void otherOverdueInfoApi(String otherOverdueInfoId,IdentifierType type);

	/**
	 * 计提信息接口
	 * @param personalCustomerModel
	 * @return
	 */
	public void provisionInfoApi(String provisionInfoId,IdentifierType type); 
	
	/**
	 * 处理回调数据
	 * @param reponseModel
	 */
	public void handleResult(AsyReponseModel reponseModel);
	/**
	 * 放款信息接口
	 * @param settleInfoId
	 * @param type
	 * @return
	 */
	public  SynResponseModel settleInfoApi(String settleInfoId,IdentifierType type);
	
	
	public SynResponseModel 
	
	
	
	

}
