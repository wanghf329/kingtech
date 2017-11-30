package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.ScaleType;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.service.ContractService;
/**
 * 合同信息
 * @author XA_JKWHF
 *
 */
@Service
public class ContractServiceImpl implements ContractService{
	@Autowired
	private ContractDAO contractDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Override
	public List<Contract> listAll(){
		return (List)contractDao.findAll();
	}

	@Override
	@Transactional
	public void addNew(String id,String loanContractId, String loanContractName,
					   BorrowerTypeEnum borrowerType, String customerId, String guarantee,
					   BigDecimal loanAmount, PeriodTypeEnum periodType, int periodTerm,
					   Date loanStartDate, Date loanEndDate, RateTypeEnum rateType,
					   BigDecimal rate, LoanPurposeEnum purpose, IndustryEnum industry,
					   LoanTypeEnum loanType, UnionFlagEnum unionFlag, PayTypeEnum payType,
					   Date signDate, String repaySource, LoanstatusEnum status, YesNoEnum isExtend) {
		Contract ct = new Contract(loanContractId,
				loanContractName,borrowerType,
				customerId,guarantee,loanAmount,
				periodType,periodTerm,loanStartDate,
				loanEndDate,rateType,rate,
				purpose,industry,loanType,
				unionFlag,payType,signDate,
				repaySource,status,isExtend);
		ct.setPushStatus(PushStatus.INITATION);
		ct.setReqId(creatRequstId.getReqId());
		contractDao.save(ct);
	}

	@Override
	@Transactional
	public void addEnterprise(String constractId, String corporateName,
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
		Contract ct = contractDao.findOne(constractId);
		if(ct == null) {
			return ;
		}
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
			ct.setCustomerId(enterprise.getId());
			ct.setBorrowerType(BorrowerTypeEnum.CORPORATION);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
