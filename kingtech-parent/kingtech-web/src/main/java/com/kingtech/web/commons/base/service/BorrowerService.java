package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.misc.PagedResult;

public interface BorrowerService {
	public List<EnterpriseCustomer> listAllEnterpries();

	public List<PersonalCustomer> listAllPersonal();
	
	public EnterpriseCustomer getEnterprise(String id);
	
	public PersonalCustomer getPersonnel(String id);
	
	public void addEnterprise( String id,String corporateName,
			String scale, String industryType, String industryinvolved,
			String organizationcode, String regCode, String regOffice,
			String regDate, String nationalregNum, String landRegNum,
			String licence, String licenceEndDate, String nature, Integer employNum,
			String legalRepresentative, String bulidDate, String actualController, BigDecimal regCapital,
			BigDecimal reallyCapital, String businessScope, String regAddress,String contactAddressProvince,
			String contactAddresscity,String contactAddressDistrict,String contactAddress,String postcode,
			String phone,String linkman,String fax,String email, String webSite);
	
	public void addPersonnel(String id,String name, String sex, String category,
							String cardNum, String phone, String farmersFlag,
							String education, String fax, String email, String marriage,
							String nationality, String birthDate, String nation,
							String addressProvince, String addressCity, String addressDistrict,
							String address, String postCode, String residence,
							String nativePlace, String workUnit, String post);
	
	public PagedResult<PersonalCustomer> pageList(Pageable pageAble);
}
