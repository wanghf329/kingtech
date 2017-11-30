package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Collateral;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.Guarantee;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.dao.rdbms.CollateralDAO;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.EnterpriseCustomerDAO;
import com.kingtech.dao.rdbms.GuaranteeDAO;
import com.kingtech.dao.rdbms.PersonalCustomerDAO;
import com.kingtech.dao.rdbms.RepayPlanDAO;
import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CollateralTypeFor1Enum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.PledgeTypeEnum;
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
	private CollateralDAO collateralDAO;
	
	@Autowired
	private GuaranteeDAO guaranteeDAO;
	
	@Autowired
	private RepayPlanDAO repayPlanDAO;
	
	@Autowired
	private SettledInfoDAO settledInfoDAO;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PersonalCustomerDAO personalCustomerDao;
	
	@Autowired
	private EnterpriseCustomerDAO enterpriseDao;
	
	@Override
	public List<Contract> listAll(){
		List<Contract> list = (List<Contract>)contractDao.findAll();
		for(Contract ct : list){
			if(BorrowerTypeEnum.S_1.equals(ct.getBorrowerType())){
				ct.setBorrowerName(enterpriseDao.findOne(ct.getBorrowerId()).getCorporateName());
			}else{
				ct.setBorrowerName(personalCustomerDao.findOne(ct.getBorrowerId()).getName());
			}
		}
		return list;
	}

	@Override
	@Transactional
	public void addNew(String id,String loanContractId, String loanContractName,
					   BorrowerTypeEnum borrowerType,String borrowerId, String customerId, String guarantee,
					   BigDecimal loanAmount, PeriodTypeEnum periodType, int periodTerm,
					   Date loanStartDate, Date loanEndDate, RateTypeEnum rateType,
					   BigDecimal rate, LoanPurposeEnum purpose, IndustryEnum industry,
					   LoanTypeEnum loanType, UnionFlagEnum unionFlag, PayTypeEnum payType,
					   Date signDate, String repaySource, LoanstatusEnum status, YesNoEnum isExtend) {
		Contract ct = null;
		if(StringUtils.isEmpty(id)){
			ct = new Contract(loanContractId,
					loanContractName,borrowerType,borrowerId,
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
			ct.setBorrowerId(borrowerId);
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
	
	@Override
	@Transactional
	public Collateral addCollateral(String id, String loanContractId, PledgeTypeEnum pledgeType,
			CollateralTypeFor1Enum collateralType, String collateralName,
			String warrantNum, BigDecimal evaluationValue, String warrantHolder,
			String collateralAddr, Date handleDate) {
		Collateral collateral = null;
		if(StringUtils.isEmpty(id)){
			collateral = new Collateral(loanContractId, pledgeType, collateralType, collateralName, warrantNum, evaluationValue, warrantHolder, collateralAddr, handleDate);
		} else {
			collateral = collateralDAO.findOne(id);
			collateral.setPledgeType(pledgeType);
			collateral.setCollateralType(collateralType);
			collateral.setCollateralName(collateralName);
			collateral.setWarrantNum(warrantNum);
			collateral.setEvaluationValue(evaluationValue);
			collateral.setWarrantHolder(warrantHolder);
			collateral.setCollateralAddr(collateralAddr);
			collateral.setHandleDate(handleDate);
		}
		return collateralDAO.save(collateral);
	}

	@Override
	@Transactional
	public Guarantee addGuarantee(String id, String loanContractId, String name,
			String cardNum, String phone, String address) {
		Guarantee guarantee = null;
		if(StringUtils.isEmpty(id)){
			guarantee = new Guarantee(loanContractId, name, cardNum, phone, address);
		} else {
			guarantee = guaranteeDAO.findOne(id);
			guarantee.setName(name);
			guarantee.setCardNum(cardNum);
			guarantee.setPhone(phone);
			guarantee.setAddress(address);
		}
		return guaranteeDAO.save(guarantee);
	}

	@Override
	@Transactional
	public RepayPlan addRepayPlan(String id, String loanContractId, Date repayDate,
			BigDecimal principal, BigDecimal interest) {
		RepayPlan repayPlan = null;
		if(StringUtils.isEmpty(id)){
			repayPlan = new RepayPlan(loanContractId, repayDate, principal, interest);
		} else {
			repayPlan = repayPlanDAO.findOne(id);
			repayPlan.setRepayDate(repayDate);
			repayPlan.setPrincipal(principal);
			repayPlan.setInterest(interest);
		}
		return repayPlanDAO.save(repayPlan);
	}

	@Override
	@Transactional
	public SettledInfo addSettledInfo(String id, String loanContractId, BigDecimal money,
			Date loanDate, Date debtStartDate, Date debtEndDate) {
		SettledInfo settledInfo = null;
		if(StringUtils.isEmpty(id)){
			settledInfo = new SettledInfo(loanContractId, money, loanDate, debtStartDate, debtEndDate);
		} else {
			settledInfo = settledInfoDAO.findOne(id);
			settledInfo.setMoney(money);
			settledInfo.setLoanDate(loanDate);
			settledInfo.setDebtStartDate(debtStartDate);
			settledInfo.setDebtEndDate(debtEndDate);
		}
		return settledInfoDAO.save(settledInfo);
	}
	
	@Override
	public List<Collateral> listCollateralByloanContractId(String loanContractId) {
		return collateralDAO.listByloanContractId(loanContractId);
	}
}
