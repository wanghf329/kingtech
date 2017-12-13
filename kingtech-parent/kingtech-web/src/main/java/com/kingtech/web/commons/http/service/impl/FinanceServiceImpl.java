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
		
		Map<String, String> data = getDataAndSign(institutionInfoModel);
		SynResponseModel responseModel = getResponse(data, "pushCompanyInformationData");
//		log.info("responseMode={}",responseModel);
//		log.info("verfy={}",verifyResponse(responseModel));
		return responseModel;
	}
	

	@Override
	public SynResponseModel branchEmployeeFacade(EmployeeModel employeeModel) {
		Map<String, String> data = getDataAndSign(employeeModel);
		SynResponseModel responseModel = getResponse(data, "pushCompanyEmployeeData");
		return responseModel;
	}

	@Override
	public SynResponseModel branchCapitalFacade(CapitalModel capitalModel) {
		Map<String, String> data = getDataAndSign(capitalModel);
		SynResponseModel responseModel = getResponse(data, "pushCompanyCapitalData");
		return responseModel;
	}

	@Override
	public SynResponseModel branchShareholderFacade(ShareholderModel shareholderModel) {
		Map<String, String> data = getDataAndSign(shareholderModel);
		SynResponseModel responseModel = getResponse(data, "pushCompanyPartnerData");
		return responseModel;
	}


	@Override
	public SynResponseModel contractFacade(ContractModel contractModel) {
		Map<String, String> data = getDataAndSign(contractModel);
		SynResponseModel responseModel = getResponse(data, "pushContractData");
		return responseModel;
	}


	@Override
	public SynResponseModel repayInfoFacade(RepayInfoModel repayInfoModel) {
		Map<String, String> data = getDataAndSign(repayInfoModel);
		SynResponseModel responseModel = getResponse(data, "pushRepayData");
		return responseModel;
	}

}
