package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;

public interface BorrowerService {
	public List<EnterpriseCustomer> listAllEnterpries();

	public List<PersonalCustomer> listAllPersonal();
	
	public void addEnterprise( String corporateName,
			String scale, String industryType, String industryinvolved,
			String organizationcode, String regCode, String regOffice,
			String regDate, String nationalregNum, String landRegNum,
			String licence, String licenceEndDate, String nature, int employNum,
			String legalRepresentative, String bulidDate, String actualController, BigDecimal regCapital,
			BigDecimal reallyCapital, String businessScope, String regAddress,String contactAddressProvince,
			String contactAddresscity,String contactAddressDistrict,String contactAddress,String postcode,
			String phone,String linkman,String fax,String email, String webSite);
}
