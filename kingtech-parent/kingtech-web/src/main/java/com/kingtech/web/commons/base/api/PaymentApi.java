package com.kingtech.web.commons.base.api;

import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.szsm.model.AsyReponseModel;
import com.kingtech.szsm.model.SynResponseModel;

public interface PaymentApi {
	
	/**
	 * 机构人员信息
	 * @param employeeId
	 * @param type
	 */
	public SynResponseModel employeeInfoApi(String employeeId,IdentifierType type);
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
	
	public SynResponseModel repayInfoApi(String repayInfoId,IdentifierType type);
	
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
	public SynResponseModel otherBaddebtApi(String otherBaddebtId,IdentifierType type);

	/**
	 * 计提信息接口
	 * @param personalCustomerModel
	 * @return
	 */
	public SynResponseModel provisionInfoApi(String provisionInfoId,IdentifierType type); 
	
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
	/**
	 * 
	 * @param financeInfoId
	 * @param type
	 * @return
	 */
	
	public SynResponseModel financeInfoApi(String financeInfoId,IdentifierType type);
	
	
	/**
	 * 查询
	 * @return
	 */
	
	public void queryTranInfoApi(String id,Cmd cmd,String reqId, PushStatus pushStatus);
	
	/**
	 * 资产转让接口
	 * @param assetId
	 * @param type
	 * @return
	 */
	public SynResponseModel assetTransferApi(String  assetId,IdentifierType type);
	
	/**
	 * repayment-finance
	 * @param paymentId
	 * @param type
	 * @return
	 */
	public SynResponseModel financePaymentApi(String  paymentId,IdentifierType type);
	
	/**
	 * 月度融资余额信息
	 * @param financeMonthId
	 * @param type
	 * @return
	 */
	public SynResponseModel financeMonthBalanceApi(String financeMonthId,IdentifierType type);
	/**
	 * 机构银行账户信息
	 * @param branchAccountInfoId
	 * @param type
	 * @return
	 */
	
	public  SynResponseModel branchAccountInfoApi(String branchAccountInfoId,IdentifierType type);
	
	/**机构银行账户月度余额信息
	 * @return
	 */
	public  SynResponseModel branchAccountBalanceApi(String branchAccountBalanceId,IdentifierType type);
	
	
	

}
