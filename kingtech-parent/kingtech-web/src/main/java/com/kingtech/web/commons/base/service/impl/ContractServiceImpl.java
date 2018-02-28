package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.common.utils.DateUtil;
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
import com.kingtech.model.ContractModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.misc.PagedResult;
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
	
	@Autowired
	private DynamicQuery dq;
	
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
	public List<Contract> listByUserIdAndPushstatus(String userId,PushStatus... pushStatus){
		return (List<Contract>)contractDao.listByUserIdAndPushstatus(userId, Arrays.asList(pushStatus));
	}

	@Override
	@Transactional
	public void addNew(String id,String loanContractNo, String loanContractName,
					   BorrowerTypeEnum borrowerType,String borrowerId, String customerId, String guarantee,
					   BigDecimal loanAmount, PeriodTypeEnum periodType, int periodTerm,
					   Date loanStartDate, Date loanEndDate, RateTypeEnum rateType,
					   BigDecimal rate, LoanPurposeEnum purpose, IndustryEnum industry,
					   LoanTypeEnum loanType, UnionFlagEnum unionFlag, PayTypeEnum payType,
					   Date signDate, String repaySource, LoanstatusEnum status, YesNoEnum isExtend) {
		Contract ct = null;
		if(StringUtils.isEmpty(id)){
			ct = new Contract(loanContractNo,
					loanContractName,borrowerType,borrowerId,
					customerId,guarantee,loanAmount,
					periodType,periodTerm,loanStartDate,
					loanEndDate,rateType,rate,
					purpose,industry,loanType,
					unionFlag,payType,signDate,
					repaySource,status,isExtend);
			ct.setReqId(creatRequstId.getReqId());
			ct.setPushStatus(PushStatus.INITATION);
			ct.setUpdateTime(new Date());
		}else{
			ct = contractDao.findOne(id);
			ct.setLoanContractNo(loanContractNo);
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
	public void addCollateral(String[] ids, String loanContractId, String[] pledgeType,
			String[] collateralType, String[] collateralName,
			String[] warrantNum, BigDecimal[] evaluationValue, String[] warrantHolder,
			String[] collateralAddr, String[] handleDate) {
		
		try {
			collateralDAO.deleteByLoanContractId(loanContractId);
			for (int i = 1; i < pledgeType.length; i++) {
				Collateral collateral= new Collateral(loanContractId, 
							PledgeTypeEnum.valueOf(pledgeType[i]), 
							CollateralTypeFor1Enum.valueOf(collateralType[i]), collateralName[i],
							warrantNum[i], evaluationValue[i], warrantHolder[i], collateralAddr[i], 
							StringUtils.isEmpty(handleDate[i]) ? null : DateUtils.parseDate(handleDate[i], "yyyy-MM-dd"));
				collateralDAO.save(collateral);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void addGuarantee(String loanContractId, String[] name,
			String[] cardNum, String[] phone, String[] address) {
		guaranteeDAO.deleteByLoanContractId(loanContractId);
		for (int i = 1; i < name.length; i++) {
			Guarantee guarantee = new Guarantee(loanContractId, name[i], cardNum[i], phone[i], address[i]);
			guaranteeDAO.save(guarantee);
		}
	}

	@Override
	@Transactional
	public void addRepayPlan(String loanContractId, String[] repayDate,
			BigDecimal[] principal, BigDecimal[] interest) {
		try {
			repayPlanDAO.deleteByLoanContractId(loanContractId);
			for (int i = 1; i < repayDate.length; i++) {
				RepayPlan repayPlan = new RepayPlan(loanContractId, 
						DateUtils.parseDate(repayDate[i], "yyyy-MM-dd"),
						principal[i], interest[i]);
				repayPlanDAO.save(repayPlan);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void addSettledInfo(String loanContractId, BigDecimal[] money,
			String[] loanDate, String[] debtStartDate, String[] debtEndDate) {
		try {
			settledInfoDAO.deleteByLoanContractId(loanContractId);
			for (int i = 1; i < money.length; i++) {
				SettledInfo settledInfo = new SettledInfo(loanContractId, money[i], 
						DateUtils.parseDate(loanDate[i], "yyyy-MM-dd"), 
						DateUtils.parseDate(debtStartDate[i], "yyyy-MM-dd"), 
						DateUtils.parseDate(debtEndDate[i], "yyyy-MM-dd"));
				settledInfoDAO.save(settledInfo);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Collateral> listCollateralByLoanContractId(String loanContractId) {
		return collateralDAO.listByloanContractId(loanContractId);
	}

	@Override
	public List<Guarantee> listGuaranteeByLoanContractId(String loanContractId) {
		return (List<Guarantee>)guaranteeDAO.listByloanContractId(loanContractId);
	}
	
	@Override
	public List<RepayPlan> listRepayPlanByLoanContractId(String loanContractId) {
		return (List<RepayPlan>)repayPlanDAO.listByloanContractId(loanContractId);
	}
	
	@Override
	public List<SettledInfo> listSettledInfoByLoanContractId(String loanContractId) {
		return (List<SettledInfo>)settledInfoDAO.listByloanContractId(loanContractId);
	}

	@Override
	public PagedResult<ContractModel> pageList(Pageable pageAble) {
		String sql = " SELECT * from TB_LOAN_CONTRACT t order by t.CREATE_TIME DESC ";
		
		String[] params = new String[0];
		List<Contract> list = dq.nativeQueryPagingList(Contract.class, pageAble, sql, params);
		Long count = dq.nativeQueryCount(sql, params);
		
		List<Contract> result = new ArrayList<Contract>();
		for(Contract ct : list){
			if(BorrowerTypeEnum.S_1.equals(ct.getBorrowerType())){
				ct.setBorrowerName(enterpriseDao.findOne(ct.getBorrowerId()).getCorporateName());
			}else{
				ct.setBorrowerName(personalCustomerDao.findOne(ct.getBorrowerId()).getName());
			}
			result.add(ct);
		}
		
		return new PagedResult(result,count);
	}
}
