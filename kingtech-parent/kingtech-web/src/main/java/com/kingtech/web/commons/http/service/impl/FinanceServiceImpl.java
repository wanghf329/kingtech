package com.kingtech.web.commons.http.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kingtech.enums.IdentifierType;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.szsm.model.ContractRequestModel;
import com.kingtech.szsm.model.EmployeeRequestModel;
import com.kingtech.szsm.model.FinanceInfoRequestModel;
import com.kingtech.szsm.model.QueryInfoRequestModel;
import com.kingtech.szsm.model.RepayExtendInfoRequestModel;
import com.kingtech.szsm.model.RepayInfoRequestModel;
import com.kingtech.szsm.model.SettledInfoRequestModel;
import com.kingtech.szsm.model.SynResponseModel;
import com.kingtech.web.commons.base.BaseAbstract;
import com.kingtech.web.commons.http.service.FinanceService;

@Service
@Slf4j
public class FinanceServiceImpl   extends BaseAbstract implements FinanceService {

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
	public SynResponseModel branchEmployeeFacade(EmployeeRequestModel employeeModel,IdentifierType type) {
		SynResponseModel responseModel = getResponse(employeeModel, "pushCompanyEmployeeData",type);
		return responseModel;
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
	public SynResponseModel contractFacade(ContractRequestModel contractModel,IdentifierType type) {
		SynResponseModel responseModel = getResponse(contractModel, "loan-contract",type);
		return responseModel;
	}


	@Override
	public SynResponseModel repayInfoFacade(RepayInfoRequestModel repayInfoModel,IdentifierType type) {
		SynResponseModel responseModel = getResponse(repayInfoModel, "repay",type);
		return responseModel;
	}


	@Override
	public SynResponseModel repayExtendInfoFacade(RepayExtendInfoRequestModel repayExtendInfoModel,IdentifierType type) {
		SynResponseModel responseModel = getResponse(repayExtendInfoModel, "extend-repay",type);
		return responseModel;
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


	@Override
	public SynResponseModel settleInfoFacade(SettledInfoRequestModel settledInfoRequestModel, IdentifierType type) {
		SynResponseModel responseModel = getResponse(settledInfoRequestModel, "loan-info",type);
		return responseModel;
	}


	@Override
	public SynResponseModel financeInfoFacade(FinanceInfoRequestModel financeInfoRequestModel, IdentifierType type) {
		SynResponseModel responseModel = getResponse(financeInfoRequestModel, " single-finane",type);
		return responseModel;
	}


	@Override
	public SynResponseModel queryInfoFacade(QueryInfoRequestModel queryInfoRequestModel) {
		SynResponseModel responseModel =  queryResponse(queryInfoRequestModel);
		return responseModel;
	}

}
