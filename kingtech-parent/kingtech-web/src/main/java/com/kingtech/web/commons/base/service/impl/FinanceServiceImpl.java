package com.kingtech.web.commons.base.service.impl;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.model.InstitutionInfoModel;
import com.kingtech.model.SynResponseModel;
import com.kingtech.web.commons.base.BaseAbstract;
import com.kingtech.web.commons.base.service.FinanceService;

@Service
@Slf4j
public class FinanceServiceImpl  extends BaseAbstract implements FinanceService {

	@Override
	public SynResponseModel inputInstitutionInfo() {
		
		InstitutionInfoModel institutionInfoModel = new InstitutionInfoModel("0000000001", 
				"U", 
				"91310000775785552H38101", 
				null, 
				"宝鸡小贷", 
				"tgt",
				"1500.02", 
				"1980-01-01",
				"1980-01-02", 
				"中国台湾", 
				"100平方千米", 
				"asdfas14564",
				"13254681451", 
				"ASFADF1325456", 
				"ASD156468541", 
				"收复台湾", 
				DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"), 
				null);
		
		Map<String, String> data = getDataAndSign(institutionInfoModel);
		SynResponseModel responseModel = getResponse(data, "pushCompanyInformationData");
		
		log.info("responseMode={}",responseModel);
		log.info("verfy={}",verifyResponse(responseModel));
		return null;
	}
	
	public static void main(String[] args) {
		FinanceServiceImpl  financeServiceImpl = new FinanceServiceImpl();
		financeServiceImpl.inputInstitutionInfo();
	}

}
