package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.enums.CertType;
import com.kingtech.enums.FarmersFlagEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.ScaleType;
import com.kingtech.enums.SexEnum;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService{
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Autowired
	private PersonalCustomerDAO personalDao;
	
	@Autowired
	private DynamicQuery dq;

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
	public void addEnterprise(String id,String corporateName,
			String scale, String industryType, String industryinvolved,
			String organizationcode, String regCode, String regOffice,
			String regDate, String nationalregNum, String landRegNum,
			String licence, String licenceEndDate, String nature,
			Integer employNum, String legalRepresentative, String bulidDate,
			String actualController, BigDecimal regCapital, BigDecimal reallyCapital,
			String businessScope, String regAddress,
			String contactAddressProvince, String contactAddresscity,
			String contactAddressDistrict, String contactAddress,
			String postcode, String phone, String linkman, String fax,
			String email, String webSite) {
		EnterpriseCustomer enterprise = null;
		try {
			if(StringUtils.isEmpty(id)){
				enterprise = new EnterpriseCustomer(corporateName, ScaleType.valueOf(scale),IndustryType.valueOf(industryType),
								IndustryEnum.valueOf(industryinvolved),organizationcode,regCode, regOffice,
								StringUtils.isEmpty(regDate) ? null: DateUtils.parseDate(regDate, "yyyy-MM-dd"),
								nationalregNum, landRegNum,licence,
								StringUtils.isEmpty(licenceEndDate) ? null:DateUtils.parseDate(licenceEndDate, "yyyy-MM-dd"),
								nature, employNum, legalRepresentative, 
								StringUtils.isEmpty(bulidDate)?null:DateUtils.parseDate(bulidDate, "yyyy-MM-dd"),
								actualController, regCapital, reallyCapital,businessScope,regAddress,
								contactAddressProvince,  contactAddresscity,contactAddressDistrict,  contactAddress,
								postcode,  phone,  linkman,  fax,email,  webSite);
					
				
			} else {
				enterprise = enterpriseDao.findOne(id);
				enterprise.setCorporateName(corporateName);
				enterprise.setScale(ScaleType.valueOf(scale));
				enterprise.setIndustryType(IndustryType.valueOf(industryType));
				enterprise.setIndustryinvolved(IndustryEnum.valueOf(industryinvolved));
				enterprise.setOrganizationcode(organizationcode);
				enterprise.setRegCode(regCode);
				enterprise.setRegOffice(regOffice);
				enterprise.setRegDate(StringUtils.isEmpty(regDate) ? null: DateUtils.parseDate(regDate, "yyyy-MM-dd"));
				enterprise.setNationalregNum(nationalregNum);
				enterprise.setLandRegNum(landRegNum);
				enterprise.setLicence(licence);
				enterprise.setLicenceEndDate(StringUtils.isEmpty(licenceEndDate) ? null:DateUtils.parseDate(licenceEndDate, "yyyy-MM-dd"));
				enterprise.setNature(nature);
				enterprise.setEmployNum(employNum);
				enterprise.setLegalRepresentative(legalRepresentative);
				enterprise.setBulidDate(StringUtils.isEmpty(bulidDate)?null:DateUtils.parseDate(bulidDate, "yyyy-MM-dd"));
				enterprise.setActualController(actualController);
				enterprise.setRegCapital(regCapital);
				enterprise.setReallyCapital(reallyCapital);
				enterprise.setBusinessScope(businessScope);
				enterprise.setRegAddress(regAddress);
				enterprise.setContactAddressProvince(contactAddressProvince);
				enterprise.setContactAddresscity(contactAddresscity);
				enterprise.setContactAddressDistrict(contactAddressDistrict);
				enterprise.setContactAddress(contactAddress);
				enterprise.setPostcode(postcode);
				enterprise.setPhone(phone);
				enterprise.setLinkman(linkman);
				enterprise.setFax(fax);
				enterprise.setEmail(email);
				enterprise.setWebSite(webSite);
			}
			enterpriseDao.save(enterprise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addPersonnel(String id,String name, String sex, String category,
			String cardNum, String phone, String farmersFlag, String education,
			String fax, String email, String marriage, String nationality,
			String birthDate, String nation, String addressProvince,
			String addressCity, String addressDistrict, String address,
			String postCode, String residence, String nativePlace,
			String workUnit, String post) {
		
		try {
			PersonalCustomer personalCustomer = null;
			if(StringUtils.isEmpty(id)){
				personalCustomer =	new PersonalCustomer(name, SexEnum.valueOf(sex) , CertType.valueOf(category) ,
						 cardNum, phone, FarmersFlagEnum.valueOf(farmersFlag),  education,
						 fax,  email,  marriage,  nationality,
						 StringUtils.isEmpty(birthDate) ? null: DateUtils.parseDate(birthDate, "yyyy-MM-dd"),
						 nation,  addressProvince,
						 addressCity,  addressDistrict,  address,
						 postCode,  residence,  nativePlace,
						 workUnit,  post);
			} else {
				personalCustomer = personalDao.findOne(id);
				personalCustomer.setName(name);
				personalCustomer.setSex(SexEnum.valueOf(sex) );
				personalCustomer.setCategory(CertType.valueOf(category));
				personalCustomer.setCardNum(cardNum);
				personalCustomer.setPhone(phone);
				personalCustomer.setFarmersFlag(FarmersFlagEnum.valueOf(farmersFlag));
				personalCustomer.setEducation(education);
				personalCustomer.setFax(fax);
				personalCustomer.setEmail(email);
				personalCustomer.setMarriage(marriage);
				personalCustomer.setNationality(nationality);
				personalCustomer.setBirthDate( StringUtils.isEmpty(birthDate) ? null: DateUtils.parseDate(birthDate, "yyyy-MM-dd"));
				personalCustomer.setNation(nation);
				personalCustomer.setAddressProvince(addressProvince);
				personalCustomer.setAddressCity(addressCity);
				personalCustomer.setAddressDistrict(addressDistrict);
				personalCustomer.setAddress(address);
				personalCustomer.setPostCode(postCode);
				personalCustomer.setResidence(residence);
				personalCustomer.setNativePlace(nativePlace);
				personalCustomer.setWorkUnit(workUnit);
				personalCustomer.setPost(post);
			}
			
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

	@Override
	public PagedResult<PersonalCustomer> pageList(Pageable pageAble) {
		String sql = " SELECT * FROM TB_BORROWER_PERSONAL t ";
		
		String[] params = new String[0];
		List<PersonalCustomer> list = dq.nativeQueryPagingList(PersonalCustomer.class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
//		List<PersonalCustomerModel> result = new ArrayList<PersonalCustomerModel>();
		return new PagedResult(list,count);
	}
}
