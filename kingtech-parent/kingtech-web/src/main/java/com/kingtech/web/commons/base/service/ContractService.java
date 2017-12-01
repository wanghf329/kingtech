package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.Collateral;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.Guarantee;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
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

public interface ContractService {
	public List<Contract> listAll();
	
	public List<Contract> listByUserIdAndPushstatus(String userId,PushStatus... pushstatus);

	public void addNew(String id,String loanContractId, String loanContractName,
						BorrowerTypeEnum borrowerType,String borrowerId, String customerId, String guarantee,
						BigDecimal loanAmount, PeriodTypeEnum periodType, int periodTerm,
						Date loanStartDate, Date loanEndDate, RateTypeEnum rateType,
						BigDecimal rate, LoanPurposeEnum purpose, IndustryEnum industry,
						LoanTypeEnum loanType, UnionFlagEnum unionFlag, PayTypeEnum payType,
						Date signDate, String repaySource, LoanstatusEnum status, YesNoEnum isExtend);
	
	public Contract getById(String id);
	
	public void addCollateral(String id[], String loanContractId, PledgeTypeEnum[] pledgeType, CollateralTypeFor1Enum[] collateralType, 
						String[] collateralName, String[] warrantNum, BigDecimal[] evaluationValue, 
						String[] warrantHolder,String[] collateralAddr, String[] handleDate);
	
	public void addGuarantee(String loanContractId, String[] name, String[] cardNum, String[] phone, String[] address);
	
	public void addRepayPlan(String loanContractId, String[] repayDate, BigDecimal[] principal, BigDecimal[] interest);
	
	public void addSettledInfo(String loanContractId, BigDecimal[] money, String[] loanDate, String[] debtStartDate, String[] debtEndDate);
	
	public List<Collateral> listCollateralByLoanContractId(String loanContractId);
	
	public List<Guarantee> listGuaranteeByLoanContractId(String loanContractId);
	
	public List<RepayPlan> listRepayPlanByLoanContractId(String loanContractId);
	
	public List<SettledInfo> listSettledInfoByLoanContractId(String loanContractId);
	
}
