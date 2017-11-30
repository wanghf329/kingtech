package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RateTypeEnum;
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
		Contract ct = null;
		if(StringUtils.isEmpty(id)){
			ct = new Contract(loanContractId,
					loanContractName,borrowerType,
					customerId,guarantee,loanAmount,
					periodType,periodTerm,loanStartDate,
					loanEndDate,rateType,rate,
					purpose,industry,loanType,
					unionFlag,payType,signDate,
					repaySource,status,isExtend);
			ct.setReqId(creatRequstId.getReqId());
			ct.setPushStatus(PushStatus.INITATION);
		}else{
			ct = contractDao.findOne(id);
			ct.setLoanContractId(loanContractId);
			ct.setLoanContractName(loanContractName);
			ct.setBorrowerType(borrowerType);
			ct.setCustomerId(customerId);
			ct.setGuarantee(guarantee);
			ct.setLoanAmount(loanAmount);
			ct.setPeriodType(periodType);
			ct.setPeriodTerm(periodTerm);
			ct.setLoanStartDate(loanStartDate);
			ct.setLoanEndDate(loanEndDate);
			ct.setRateType(rateType);
			ct.setRate(rate);
			ct.setPurpose(purpose);
			ct.setIndustry(industry);
			ct.setLoanType(loanType);
			ct.setUnionFlag(unionFlag);
			ct.setPayType(payType);
			ct.setSignDate(signDate);
			ct.setRepaySource(repaySource);
			ct.setStatus(status);
			ct.setIsExtend(isExtend);
		}
		contractDao.save(ct);
	}

	@Override
	public Contract getById(String id) {
		return contractDao.findOne(id);
	}
}
