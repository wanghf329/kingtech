package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import com.kingtech.dao.rdbms.GuaranteeDAO;
import com.kingtech.dao.rdbms.RepayPlanDAO;
import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CollateralTypeEnum;
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
	public void addCollateral(String loanContractId, PledgeTypeEnum pledgeType,
			CollateralTypeEnum collateralType, String collateralName,
			String warrantNum, BigDecimal evaluationValue, String warrantHolder,
			String collateralAddr, Date handleDate) {
		Collateral collateral = new Collateral(loanContractId, pledgeType, collateralType, collateralName, warrantNum, evaluationValue, warrantHolder, collateralAddr, handleDate);
		collateralDAO.save(collateral);
	}

	@Override
	public void addGuarantee(String loanContractId, String name,
			String cardNum, String phone, String address) {
		Guarantee guarantee = new Guarantee(loanContractId, name, cardNum, phone, address);
		guaranteeDAO.save(guarantee);
	}

	@Override
	public void addRepayPlan(String loanContractId, Date repayDate,
			BigDecimal principal, BigDecimal interest) {
		RepayPlan repayPlan = new RepayPlan(loanContractId, repayDate, principal, interest);
		repayPlanDAO.save(repayPlan);
	}

	@Override
	public void addSettledInfo(String loanContractId, BigDecimal money,
			Date loanDate, Date debtStartDate, Date debtEndDate) {
		SettledInfo settledInfo = new SettledInfo(loanContractId, money, loanDate, debtStartDate, debtEndDate);
		settledInfoDAO.save(settledInfo);
	}
}
