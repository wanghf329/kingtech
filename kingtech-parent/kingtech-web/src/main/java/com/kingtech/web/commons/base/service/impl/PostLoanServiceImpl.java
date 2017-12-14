package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.entity.OtherOverdueInfo;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.OtherBaddebtDAO;
import com.kingtech.dao.rdbms.OtherOverdueInfoDAO;
import com.kingtech.dao.rdbms.RepayInfoDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ext.ModelExt;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.PostLoanService;


@Service
public class PostLoanServiceImpl implements PostLoanService{
	
	@Autowired
	private ContractDAO contractDao;
	
	@Autowired
	private RepayInfoDAO repayInfoDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private OtherBaddebtDAO otherBaddebtDAO;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private OtherOverdueInfoDAO otherOverdueInfoDAO;

	@Override
	public List<Contract> listAllContract() {
		return (List<Contract>) contractDao.findAll();
	}

	@Override
	public List<ModelExt> listAllRepayInfo() {
		List<ModelExt> result = new ArrayList<ModelExt>();
		List<RepayInfo> repayInfos= (List<RepayInfo>) repayInfoDao.findAll();
		String constractId = "";
		Contract contract = null;
		for (RepayInfo repayInfo:repayInfos) {
			if(constractId.equals(repayInfo.getLoanContractId())) {
				constractId = repayInfo.getLoanContractId();
			} else {
				contract = contractDao.findOne(repayInfo.getLoanContractId());
			}
			result.add(new ModelExt(
					   new RepayInfoModel(repayInfo.getId(), 
									   	  repayInfo.getLoanContractId(),
									      repayInfo.getRepayAmount().toPlainString(),
									      repayInfo.getRepayPrincipalAmount().toPlainString(),
									      repayInfo.getRepayInterestAmount().toPlainString(),
									      DateUtil.getDateStr(repayInfo.getRepayDate(), "yyyy-MM-dd")),
					   contract.getLoanContractNo(),
					   contract.getLoanContractName(),
					   repayInfo.getPushStatus()));
		}
		return result;
	}

	@Override
	public RepayInfoModel getRepayInfoById(String id) {
		RepayInfo repayInfo = repayInfoDao.findOne(id);
		if(repayInfo == null) {
			return null;
		}
		RepayInfoModel  model = new RepayInfoModel(id, 
				repayInfo.getLoanContractId(), 
				repayInfo.getRepayAmount().toPlainString(),
				repayInfo.getRepayPrincipalAmount().toPlainString(),
				repayInfo.getRepayInterestAmount().toPlainString(),
				DateUtil.getDateStr(repayInfo.getRepayDate(), "yyyy-MM-dd"));
		return model;
	}

	@Override
	@Transactional
	public RepayInfo addNewRepayInfo(String id, String repayDate,
			BigDecimal repayAmount, BigDecimal repayPrincipalAmount,
			BigDecimal repayInterestAmount, String loanContractId) {
		RepayInfo repayInfo = null;
		if(StringUtils.isEmpty(loanContractId)) {
			return null;
		}
		try {
			if(StringUtils.isEmpty(id)) {
				repayInfo = repayInfoDao.save(new RepayInfo(loanContractId,
															creatRequstId.getReqId(), 
															PushStatus.INITATION, 
															DateUtils.parseDate(repayDate, "yyyy-MM-dd"),
															repayAmount,
															repayPrincipalAmount, 
															repayInterestAmount));
				
			} else {
				repayInfo = repayInfoDao.findOne(id);
				repayInfo.setRepayAmount(repayAmount);
				repayInfo.setRepayInterestAmount(repayInterestAmount);
				repayInfo.setRepayPrincipalAmount(repayPrincipalAmount);
				repayInfo.setLoanContractId(loanContractId);
				repayInfo.setRepayDate(DateUtils.parseDate(repayDate, "yyyy-MM-dd"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repayInfoDao.save(repayInfo); 
		paymentApi.repayInfoApi(repayInfo.getId(), StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
		return repayInfo;
	}

	@Override
	public List<ModelExt> listAllOtherBaddebt() {
		
		List<ModelExt> result = new ArrayList<ModelExt>();
		List<OtherBaddebt> baddebtInfos= (List<OtherBaddebt>) otherBaddebtDAO.findAll();
		String constractId = "";
		Contract contract = null;
		for (OtherBaddebt otherBaddebt:baddebtInfos) {
			if(constractId.equals(otherBaddebt.getLoanContractId())) {
				constractId = otherBaddebt.getLoanContractId();
			} else {
				contract = contractDao.findOne(otherBaddebt.getLoanContractId());
			}
			result.add(new ModelExt(
						   new OtherBaddebtModel(otherBaddebt.getId(), 
												 otherBaddebt.getLoanContractId(),
												 otherBaddebt.getBadMoney().toPlainString(),
												 DateUtil.getDateStr(otherBaddebt.getSetDate(), "yyyy-MM-dd"),
												 otherBaddebt.getFollowupWork()),
						   
						   contract.getLoanContractNo(),
						   contract.getLoanContractName(),
						   otherBaddebt.getPushStatus()));
		}
		return result;
		
	}

	@Override
	public OtherBaddebtModel getBaddebtInfoById(String id) {
		OtherBaddebt badDebtInfo = otherBaddebtDAO.findOne(id);
		if(badDebtInfo == null) {
			return null;
		}
		OtherBaddebtModel  model = new OtherBaddebtModel(id, 
														badDebtInfo.getLoanContractId(),
														badDebtInfo.getBadMoney().toPlainString(),
														DateUtil.getDateStr(badDebtInfo.getSetDate(), "yyyy-MM-dd"),
														badDebtInfo.getFollowupWork());
		return model;
	}

	@Override
	@Transactional
	public OtherBaddebt addNewBaddebtInfo(String id,
									  	  String setDate,
									  	  BigDecimal badMoney,
									  	  String followupWork,
									  	  String loanContractId) {
		OtherBaddebt badDebtInfo = null;
		
		if(StringUtils.isEmpty(loanContractId)) {
			return null;
		}
		try {
			if(StringUtils.isEmpty(id)) {
				badDebtInfo = otherBaddebtDAO.save(new OtherBaddebt(loanContractId, 
																	creatRequstId.getReqId(), 
																	PushStatus.INITATION, 
																	badMoney,
																	DateUtils.parseDate(setDate, "yyyy-MM-dd"),
																	followupWork));
				
			} else {
				badDebtInfo = otherBaddebtDAO.findOne(id);
				badDebtInfo.setBadMoney(badMoney);
				badDebtInfo.setLoanContractId(loanContractId);
				badDebtInfo.setSetDate(DateUtils.parseDate(setDate, "yyyy-MM-dd"));
				badDebtInfo.setFollowupWork(followupWork);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paymentApi.otherBaddebtApi(badDebtInfo.getId(),StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
		otherBaddebtDAO.save(badDebtInfo);
		return badDebtInfo;
	}

	@Override
	public List<ModelExt> listAllOverdue() {
		List<ModelExt> result = new ArrayList<ModelExt>();
		List<OtherOverdueInfo> overdueInfos= (List<OtherOverdueInfo>) otherOverdueInfoDAO.findAll();
		String constractId = "";
		Contract contract = null;
		for (OtherOverdueInfo overdueInfo:overdueInfos) {
			if(constractId.equals(overdueInfo.getLoanContractId())) {
				constractId = overdueInfo.getLoanContractId();
			} else {
				contract = contractDao.findOne(overdueInfo.getLoanContractId());
			}
			result.add(new ModelExt(
						   new OtherOverdueInfoModel(overdueInfo.getId(),
								   overdueInfo.getLoanContractId(),
								   overdueInfo.getOverdueMoney().toPlainString(),
								   DateUtil.getDateStr(overdueInfo.getOverdueDate(), "yyyy-MM-dd"),
								   overdueInfo.getOverdueInterest().toPlainString(),
								   overdueInfo.getBalance().toPlainString(),
								   overdueInfo.getRemarks()),
						   contract.getLoanContractNo(),
						   contract.getLoanContractName(),
						   overdueInfo.getPushStatus()));
		}
		return result;
		
	}

	@Override
	public OtherOverdueInfoModel getOverDueInfoById(String id) {
		
		OtherOverdueInfo overdueInfo = otherOverdueInfoDAO.findOne(id);
		if(overdueInfo == null) {
			return null;
		}
		OtherOverdueInfoModel  model = new OtherOverdueInfoModel(overdueInfo.getId(),
															     overdueInfo.getLoanContractId(),
															     overdueInfo.getOverdueMoney().toPlainString(),
															     DateUtil.getDateStr(overdueInfo.getOverdueDate(), "yyyy-MM-dd"),
															     overdueInfo.getOverdueInterest().toPlainString(),
															     overdueInfo.getBalance().toPlainString(),
															     overdueInfo.getRemarks());
		return model;
	}

	@Override
	@Transactional
	public OtherOverdueInfo addNewOverDueInfo(String id, 
											  String overdueDate,
											  BigDecimal overdueMoney, 
											  BigDecimal overdueInterest,
											  BigDecimal balance, 
											  String remarks,
											  String loanContractId) {
		
		OtherOverdueInfo overdueInfo = null;
		
		if(StringUtils.isEmpty(loanContractId)) {
			return null;
		}
		try {
			if(StringUtils.isEmpty(id)) {
				overdueInfo = otherOverdueInfoDAO.save(new OtherOverdueInfo(loanContractId,
																			creatRequstId.getReqId(), 
																			PushStatus.INITATION,  
																			overdueMoney, 
																			DateUtils.parseDate(overdueDate, "yyyy-MM-dd"),
																			overdueInterest, 
																			balance,
																			remarks));
				
			} else {
				overdueInfo = otherOverdueInfoDAO.findOne(id);
				overdueInfo.setBalance(balance);
				overdueInfo.setLoanContractId(loanContractId);
				overdueInfo.setOverdueDate(DateUtils.parseDate(overdueDate, "yyyy-MM-dd"));
				overdueInfo.setOverdueInterest(overdueInterest);
				overdueInfo.setOverdueMoney(overdueMoney);
				overdueInfo.setRemarks(remarks);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paymentApi.otherOverdueInfoApi(overdueInfo.getId(), StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
		otherOverdueInfoDAO.save(overdueInfo);
		return overdueInfo;
	}
	

}
