package com.kingtech.web.commons.base.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kingtech.model.InstitutionInfoModel;
import com.kingtech.model.SynResponseModel;
import com.kingtech.web.commons.base.BaseAbstract;

@Service
@Slf4j
public class FinanceServiceImpl  extends BaseAbstract implements FinanceService {

	@Override
	public SynResponseModel inputInstitutionInfo() {
		
		InstitutionInfoModel institutionInfoModel = new InstitutionInfoModel("b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b");
		
		System.err.println(getDataAndSign(institutionInfoModel));
	
		return null;
	}
	
	public static void main(String[] args) {
		FinanceServiceImpl  financeServiceImpl = new FinanceServiceImpl();
		financeServiceImpl.inputInstitutionInfo();
	}

}
