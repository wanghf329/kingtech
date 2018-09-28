package com.kingtech.web.commons.http.service;

import com.kingtech.enums.IdentifierType;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.szsm.model.AssetTransferRequestModel;
import com.kingtech.szsm.model.BranchAccountBalanceRequest;
import com.kingtech.szsm.model.BranchAccountInfoRequest;
import com.kingtech.szsm.model.ContractRequestModel;
import com.kingtech.szsm.model.EmployeeRequestModel;
import com.kingtech.szsm.model.FinanceInfoRequestModel;
import com.kingtech.szsm.model.FinanceMonthBalanceRequest;
import com.kingtech.szsm.model.OtherBaddebtRequestModel;
import com.kingtech.szsm.model.ProvisionInfoRequestModel;
import com.kingtech.szsm.model.QueryInfoRequestModel;
import com.kingtech.szsm.model.RepayExtendInfoRequestModel;
import com.kingtech.szsm.model.RepayInfoRequestModel;
import com.kingtech.szsm.model.RepaymentFinanceRequestModel;
import com.kingtech.szsm.model.SettledInfoRequestModel;
import com.kingtech.szsm.model.SynResponseModel;
/**
 * 对接监管系统接口
 * @author baijt
 *
 */
public interface FinanceService {
	/**
	 * 2.1机构基本信息接口
	 * @return
	 */
	public SynResponseModel branchInfoFacade(BranchInfoModel branchInfoModel);
	
	/**
	 * 机构人员信息接口
	 */
	
	public SynResponseModel branchEmployeeFacade(EmployeeRequestModel employeeModel,IdentifierType type);
	
	/**
	 * 
	 * 机构资本信息接口
	 */
	
	public SynResponseModel branchCapitalFacade(CapitalModel capitalModel);
	
	/**
	 * 机构股东信息接口
	 * @param shareholderModel
	 * @return
	 */
	public SynResponseModel branchShareholderFacade(ShareholderModel shareholderModel);
	
	/**
	 * 放贷业务合同信息接口
	 * @param shareholderModel
	 * @return
	 */
	public SynResponseModel contractFacade(ContractRequestModel contractModel,IdentifierType type);
	
	/**
	 * 还款信息接口
	 * @param repayInfoModel
	 * @return
	 */
	public SynResponseModel repayInfoFacade(RepayInfoRequestModel repayInfoModel,IdentifierType type);
	
	/**
	 * 展期还款信息接口
	 * @param repayExtendInfoModel
	 * @return
	 */
	public SynResponseModel repayExtendInfoFacade(RepayExtendInfoRequestModel repayExtendInfoModel,IdentifierType type);
	
	/**
	 * 展期还款计划接口
	 * @param repayExtendInfoModel
	 * @return
	 */
	public SynResponseModel repayExtendPlanFacade(RepayExtendPlanModel repayExtendPlanModel);
	/**
	 * 坏账信息接口
	 * @param otherBaddebtModel
	 * @return
	 */
	public SynResponseModel otherBaddebtFacade(OtherBaddebtRequestModel otherBaddebtModel,IdentifierType type);

	/**
	 * 计提信息接口
	 * @param personalCustomerModel
	 * @return
	 */
	public SynResponseModel provisionInfoFacade(ProvisionInfoRequestModel provisionInfoModel,IdentifierType type); 
	/**
	 * 放款信息接口
	 * @return
	 */
	public SynResponseModel settleInfoFacade(SettledInfoRequestModel settledInfoRequestModel,IdentifierType type);
	
	/**
	 * 单笔融资信息接口
	 * @param financeInfoRequestModel
	 * @param type
	 * @return
	 */
	public SynResponseModel  financeInfoFacade (FinanceInfoRequestModel financeInfoRequestModel,IdentifierType type);
	/**
	 * 查询接口
	 * @param queryInfoRequestModel
	 * @return
	 */
	public SynResponseModel  queryInfoFacade(QueryInfoRequestModel queryInfoRequestModel);
	
	/**
	 * 资产转让接口
	 * @param assetTransferRequestModel
	 * @param type
	 * @return
	 */
	public  SynResponseModel assetTransferFacade(AssetTransferRequestModel assetTransferRequestModel,IdentifierType type);
	/**
	 * 融资实际还款信息
	 * @param financeRequestModel
	 * @param type
	 * @return
	 */
	public  SynResponseModel  financePaymentFacade(RepaymentFinanceRequestModel financeRequestModel,IdentifierType type);
	/**
	 * 月度融资余额信息
	 * @param financeMonthBalanceRequest
	 * @param type
	 * @return
	 */
	public SynResponseModel financeMonthBalanceFacade(FinanceMonthBalanceRequest financeMonthBalanceRequest,IdentifierType type);
	
	/**
	 * @param branchAccountInfoId
	 * @param type
	 * @return
	 */
	public SynResponseModel branchAccountInfoFacade(BranchAccountInfoRequest branchAccountInfoRequest,IdentifierType type);
	
	/**机构银行账户月度余额信息
	 * @return
	 */
	public  SynResponseModel branchAccountBalanceFacade(BranchAccountBalanceRequest branchAccountBalanceRequest,IdentifierType type);
	
}
