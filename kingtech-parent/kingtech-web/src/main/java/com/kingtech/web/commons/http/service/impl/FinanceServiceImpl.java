package com.kingtech.web.commons.http.service.impl;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.ContractModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.model.SynResponseModel;
import com.kingtech.web.commons.base.BaseAbstract;
import com.kingtech.web.commons.http.service.FinanceService;

@Service
@Slf4j
public class FinanceServiceImpl  extends BaseAbstract implements FinanceService {

	@Override
	public SynResponseModel branchInfoFacade(BranchInfoModel institutionInfoModel) {
		return null;
		
//		Map<String, String> data = getDataAndSign(institutionInfoModel);
//		SynResponseModel responseModel = getResponse(data, "pushCompanyInformationData");
////		log.info("responseMode={}",responseModel);
////		log.info("verfy={}",verifyResponse(responseModel));
//		return responseModel;
	}
	

	@Override
	public SynResponseModel branchEmployeeFacade(EmployeeModel employeeModel) {
		return null;
//		Map<String, String> data = getDataAndSign(employeeModel);
//		SynResponseModel responseModel = getResponse(data, "pushCompanyEmployeeData");
//		return responseModel;
	}

	@Override
	public SynResponseModel branchCapitalFacade(CapitalModel capitalModel) {
		return null;
//		Map<String, String> data = getDataAndSign(capitalModel);
//		SynResponseModel responseModel = getResponse(data, "pushCompanyCapitalData");
//		return responseModel;
	}

	@Override
	public SynResponseModel branchShareholderFacade(ShareholderModel shareholderModel) {
//		Map<String, String> data = getDataAndSign(shareholderModel);
//		SynResponseModel responseModel = getResponse(data, "pushCompanyPartnerData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel contractFacade(ContractModel contractModel) {
//		Map<String, String> data = getDataAndSign(contractModel);
//		SynResponseModel responseModel = getResponse(data, "pushContractData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel repayInfoFacade(RepayInfoModel repayInfoModel) {
//		Map<String, String> data = getDataAndSign(repayInfoModel);
//		SynResponseModel responseModel = getResponse(data, "pushRepayData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel repayExtendInfoFacade(RepayExtendInfoModel repayExtendInfoModel) {
//		Map<String, String> data = getDataAndSign(repayExtendInfoModel);
//		SynResponseModel responseModel = getResponse(data, "pushExtendRepayData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel repayExtendPlanFacade(RepayExtendPlanModel repayExtendPlanModel) {
//		Map<String, String> data = getDataAndSign(repayExtendPlanModel);
//		SynResponseModel responseModel = getResponse(data, "pushExtendPlanData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel otherBaddebtFacade(OtherBaddebtModel otherBaddebtModel) {
//		Map<String, String> data = getDataAndSign(otherBaddebtModel);
//		SynResponseModel responseModel = getResponse(data, "pushBadDebtData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel otherOverdueInfoFacade(OtherOverdueInfoModel otherOverdueInfoModel) {
//		Map<String, String> data = getDataAndSign(otherOverdueInfoModel);
//		SynResponseModel responseModel = getResponse(data, "pushOverdueData");
//		return responseModel;
		return null;
	}


	@Override
	public SynResponseModel provisionInfoFacade(ProvisionInfoModel provisionInfoModel) {
//		Map<String, String> data = getDataAndSign(provisionInfoModel);
//		SynResponseModel responseModel = getResponse(data, "pushProvisionData");
//		return responseModel;
		return null;
	}

}
