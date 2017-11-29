package com.kingtech.web.commons.base.utils;

import org.apache.commons.lang3.StringUtils;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.PersonalCustomerModel;

public class DTOUtils {
	
	public static PersonalCustomerModel getPersonalCustomerModel(PersonalCustomer personalCustomer){
		PersonalCustomerModel customerModel = null;
		if (personalCustomer != null) {
			customerModel = new PersonalCustomerModel(personalCustomer.getName(),
					                                  personalCustomer.getSex().getKey(),
					                                  getNewStr(personalCustomer.getCategory() ==null ? null:personalCustomer.getCategory().name()),
					                                  personalCustomer.getCardNum(),
					                                  personalCustomer.getPhone(), 
					                                  getNewStr(personalCustomer.getFarmersFlag() ==null ? null :personalCustomer.getFarmersFlag().name()),
					                                  personalCustomer.getEducation(), 
					                                  personalCustomer.getFax(), 
					                                  personalCustomer.getEmail(), 
					                                  personalCustomer.getMarriage(), 
					                                  personalCustomer.getNationality(),
					                                  DateUtil.getDateStr(personalCustomer.getBirthDate(), "yyyy-MM-dd"),
					                                  personalCustomer.getNation(), 
					                                  personalCustomer.getAddressProvince(), 
					                                  personalCustomer.getAddressCity(), 
					                                  personalCustomer.getAddressDistrict(), 
					                                  personalCustomer.getAddress(), 
					                                  personalCustomer.getPostCode(), 
					                                  personalCustomer.getResidence(), 
					                                  personalCustomer.getNativePlace(), 
					                                  personalCustomer.getWorkUnit(), 
					                                  personalCustomer.getPost());
		}
		
		return customerModel;
	}
	
	public static EnterpriseCustomerModel getEnterpriseCustomerModel(EnterpriseCustomer enterpriseCustomer){
		EnterpriseCustomerModel enterpriseCustomerModel = null;
		if (enterpriseCustomer != null ) {
			enterpriseCustomerModel = new EnterpriseCustomerModel(enterpriseCustomer.getCorporateName(),
					                                              getNewStr(enterpriseCustomer.getScale().name()),
					                                              getNewStr(enterpriseCustomer.getIndustryType().name()),
					                                              getNewStr(enterpriseCustomer.getIndustryinvolved().name()),
					                                              enterpriseCustomer.getOrganizationcode(),
					                                              enterpriseCustomer.getRegcode(),
					                                              enterpriseCustomer.getRegoffice(),
					                                              DateUtil.getDateStr(enterpriseCustomer.getRegDate(), "yyyy-MM-dd"),
					                                              enterpriseCustomer.getNationalregNum(), 
					                                              enterpriseCustomer.getLandregNum(), 
					                                              enterpriseCustomer.getLicence(), 
					                                              enterpriseCustomer.getLicenceEndDate(), nature, employNum, legalRepresentative, bulidDate, actualController, regCapital, reallyCapital, businessScope, regAddress, contactAddressProvince, contactAddresscity, contactAddressDistrict, contactAddress, postcode, phone, linkman, fax, email, webSite)
			
		}
		
	}
	
	
	
	public static String getNewStr(String olderStr){
		if (StringUtils.isEmpty(olderStr)) {
			return null;
		}
		return olderStr.replace("S_", "");
	}

}
