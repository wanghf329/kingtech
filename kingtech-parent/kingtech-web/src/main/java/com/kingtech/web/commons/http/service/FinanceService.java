package com.kingtech.web.commons.http.service;

import com.kingtech.enums.IdentifierType;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.szsm.model.ContractRequestModel;
import com.kingtech.szsm.model.EmployeeRequestModel;
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
	public SynResponseModel repayInfoFacade(RepayInfoModel repayInfoModel);
	
	/**
	 * 展期还款信息接口
	 * @param repayExtendInfoModel
	 * @return
	 */
	public SynResponseModel repayExtendInfoFacade(RepayExtendInfoModel repayExtendInfoModel);
	
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
	public SynResponseModel otherBaddebtFacade(OtherBaddebtModel otherBaddebtModel);
	/**
	 * 逾期信息
	 * @param otherOverdueInfoModel
	 * @return
	 */
	
	public SynResponseModel otherOverdueInfoFacade(OtherOverdueInfoModel otherOverdueInfoModel);

	/**
	 * 计提信息接口
	 * @param personalCustomerModel
	 * @return
	 */
	public SynResponseModel provisionInfoFacade(ProvisionInfoModel provisionInfoModel); 
}
