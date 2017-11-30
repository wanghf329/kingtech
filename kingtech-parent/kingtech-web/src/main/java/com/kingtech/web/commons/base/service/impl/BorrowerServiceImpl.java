package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.enums.CertType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.ScaleType;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.web.commons.base.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService{
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Autowired
	private PersonalCustomerDAO personalDao;

	@Override
	public List<EnterpriseCustomer> listAllEnterpries() {
		return (List<EnterpriseCustomer>)enterpriseDao.findAll();
	}

	@Override
	public List<PersonalCustomer> listAllPersonal() {
		return (List<PersonalCustomer>)personalDao.findAll();
	}
	
	@Override
	@Transactional
	public void addEnterprise(String corporateName,
			String scale, String industryType, String industryinvolved,
			String organizationcode, String regCode, String regOffice,
			String regDate, String nationalregNum, String landRegNum,
			String licence, String licenceEndDate, String nature,
			int employNum, String legalRepresentative, String bulidDate,
			String actualController, BigDecimal regCapital, BigDecimal reallyCapital,
			String businessScope, String regAddress,
			String contactAddressProvince, String contactAddresscity,
			String contactAddressDistrict, String contactAddress,
			String postcode, String phone, String linkman, String fax,
			String email, String webSite) {
		try {
			EnterpriseCustomer enterprise = 
					new EnterpriseCustomer(corporateName, ScaleType.valueOf(scale),IndustryType.valueOf(industryType),
							IndustryEnum.valueOf(industryinvolved),organizationcode,regCode, regOffice,
							StringUtils.isEmpty(regDate) ? null: DateUtils.parseDate(regDate, "yyyy-MM-dd"),
							nationalregNum, landRegNum,licence,
							StringUtils.isEmpty(licenceEndDate) ? null:DateUtils.parseDate(licenceEndDate, "yyyy-MM-dd"),
							nature, employNum, legalRepresentative, 
							StringUtils.isEmpty(bulidDate)?null:DateUtils.parseDate(bulidDate, "yyyy-MM-dd"),
							actualController, regCapital, reallyCapital,businessScope,regAddress,
							contactAddressProvince,  contactAddresscity,contactAddressDistrict,  contactAddress,
							postcode,  phone,  linkman,  fax,email,  webSite);
			
			enterprise = enterpriseDao.save(enterprise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addPersonnel(String name, String sex, String category,
			String cardNum, String phone, String farmersFlag, String education,
			String fax, String email, String marriage, String nationality,
			String birthDate, String nation, String addressProvince,
			String addressCity, String addressDistrict, String address,
			String postCode, String residence, String nativePlace,
			String workUnit, String post) {
		try {
			PersonalCustomer personalCustomer = 
					new PersonalCustomer( name, SexEnum.valueOf(sex) , CertType.valueOf(category) ,
							 cardNum, phone, YesNoEnum.valueOf(farmersFlag),  education,
							 fax,  email,  marriage,  nationality,
							 StringUtils.isEmpty(birthDate) ? null: DateUtils.parseDate(birthDate, "yyyy-MM-dd"),
							 nation,  addressProvince,
							 addressCity,  addressDistrict,  address,
							 postCode,  residence,  nativePlace,
							 workUnit,  post);
			
			personalCustomer = personalDao.save(personalCustomer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public EnterpriseCustomer getEnterprise(String id) {
		// TODO Auto-generated method stub
		return enterpriseDao.findOne(id);
	}

	@Override
	public PersonalCustomer getPersonnel(String id) {
		// TODO Auto-generated method stub
		return personalDao.findOne(id);
	}
}
