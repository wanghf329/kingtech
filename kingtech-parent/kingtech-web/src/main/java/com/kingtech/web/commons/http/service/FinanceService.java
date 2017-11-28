package com.kingtech.web.commons.http.service;

import com.kingtech.model.CapitalModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.model.SynResponseModel;
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
	
	public SynResponseModel branchEmployeeFacade(EmployeeModel employeeModel);
	
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

}
