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

import com.google.common.collect.Lists;
import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.AssetTransfer;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.entity.OtherOverdueInfo;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.rdbms.AssetTransferDAO;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.OtherBaddebtDAO;
import com.kingtech.dao.rdbms.OtherOverdueInfoDAO;
import com.kingtech.dao.rdbms.RepayInfoDAO;
import com.kingtech.enums.BadTypeEnum;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.AssetTransferModel;
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
	
	@Autowired
	private AssetTransferDAO assetTransferDao;
	
	@Autowired
	private PaymentApi api;

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
//			result.add(new ModelExt(
//					   new RepayInfoModel(repayInfo.getId(), 
//									   	  repayInfo.getLoanContractId(),
//									      repayInfo.getRepayAmount().toPlainString(),
//									      repayInfo.getRepayPrincipalAmount().toPlainString(),
//									      repayInfo.getRepayInterestAmount().toPlainString(),
//									      DateUtil.getDateStr(repayInfo.getRepayDate(), "yyyy-MM-dd")),
//					   contract.getLoanContractNo(),
//					   contract.getLoanContractName(),
//					   repayInfo.getPushStatus()));
		}
		return result;
	}

	@Override
	public RepayInfoModel getRepayInfoById(String id) {
		RepayInfo repayInfo = repayInfoDao.findOne(id);
		if(repayInfo == null) {
			return null;
		}
		RepayInfoModel model = new RepayInfoModel(id, repayInfo.getRepayTime(),
				repayInfo.getMoney(), repayInfo.getInterest(),
				repayInfo.getPenaltyInterest(), repayInfo.getPenalty(),
				repayInfo.getServiceCharge(), repayInfo.getOtherCharge());
		return model;
	}

	@Override
	@Transactional
	public RepayInfo saveRepayInfo(String loanContractId,RepayInfoModel model){
		RepayInfo repayInfo = null;
		if(StringUtils.isEmpty(loanContractId)) {
			return null;
		}
		try {
			if(StringUtils.isEmpty(model.getId())) {
				repayInfo = new RepayInfo(loanContractId,
										  creatRequstId.getReqId(), 
										  PushStatus.INITATION, 
										  RecordStatus.NORMAL,
										  model.getRepayTime(),
										  model.getMoney(),
										  model.getInterest(),
										  model.getPenaltyInterest(),
										  model.getPenalty(),
										  model.getServiceCharge(),
										  model.getOtherCharge());
			} else {
				repayInfo = repayInfoDao.findOne(model.getId());
				repayInfo.setRepayTime(model.getRepayTime());
				repayInfo.setMoney(model.getMoney());
				repayInfo.setInterest(model.getInterest());
				repayInfo.setPenaltyInterest(model.getPenaltyInterest());
				repayInfo.setPenalty(model.getPenalty());
				repayInfo.setServiceCharge(model.getServiceCharge());
				repayInfo.setOtherCharge(model.getOtherCharge());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		repayInfoDao.save(repayInfo); 
		
		if(PushStatus.INITATION.equals(repayInfo.getPushStatus())){
			api.repayInfoApi(repayInfo.getId(), IdentifierType.A);
		}
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
												 DateUtil.getDateStr(otherBaddebt.getLossDate(), "yyyy-MM-dd"),
												 otherBaddebt.getBadType().name(),
												 otherBaddebt.getFollowUp()),
						   
						   contract.getContractNumber(),
						   contract.getContractName(),
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
														DateUtil.getDateStr(badDebtInfo.getLossDate(), "yyyy-MM-dd"),
														badDebtInfo.getBadType().name(),
														badDebtInfo.getFollowUp());
		return model;
	}

	@Override
	@Transactional
	public OtherBaddebt addNewBaddebtInfo(String id,
									  	  Date lossDate,
									  	  BigDecimal badMoney,
									  	  BadTypeEnum badType,
									  	  String followUp,
									  	  String loanContractId) {
		OtherBaddebt badDebtInfo = null;
		
		if(StringUtils.isEmpty(loanContractId)) {
			return null;
		}
		try {
			if(StringUtils.isEmpty(id)) {
				badDebtInfo = new OtherBaddebt(loanContractId, 
												creatRequstId.getReqId(), 
												PushStatus.INITATION, 
												RecordStatus.NORMAL,
												badMoney,
												lossDate,
												badType,
												followUp);
				
			} else {
				badDebtInfo = otherBaddebtDAO.findOne(id);
				badDebtInfo.setBadMoney(badMoney);
				badDebtInfo.setLoanContractId(loanContractId);
				badDebtInfo.setLossDate(lossDate);
				badDebtInfo.setBadType(badType);
				badDebtInfo.setFollowUp(followUp);
				otherBaddebtDAO.save(badDebtInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		otherBaddebtDAO.save(badDebtInfo);
		paymentApi.otherBaddebtApi(badDebtInfo.getId(),StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
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
//			result.add(new ModelExt(
//						   new OtherOverdueInfoModel(overdueInfo.getId(),
//								   overdueInfo.getLoanContractId(),
//								   overdueInfo.getOverdueMoney().toPlainString(),
//								   DateUtil.getDateStr(overdueInfo.getOverdueDate(), "yyyy-MM-dd"),
//								   overdueInfo.getOverdueInterest().toPlainString(),
//								   overdueInfo.getBalance().toPlainString(),
//								   overdueInfo.getRemarks()),
//						   contract.getLoanContractNo(),
//						   contract.getLoanContractName(),
//						   overdueInfo.getPushStatus()));
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
				overdueInfo = new OtherOverdueInfo(loanContractId,
												   creatRequstId.getReqId(), 
												   PushStatus.INITATION,  
												   RecordStatus.NORMAL,
												   overdueMoney, 
												   DateUtils.parseDate(overdueDate, "yyyy-MM-dd"),
												   overdueInterest, 
												   balance,
												   remarks);
				
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
		otherOverdueInfoDAO.save(overdueInfo);
//		paymentApi.otherOverdueInfoApi(overdueInfo.getId(), StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
		return overdueInfo;
	}
	
	@Override
	public List<ModelExt> listAllAssetTransfer() {
		List<ModelExt> result = new ArrayList<ModelExt>();
		List<AssetTransfer> assetTransfers= (List<AssetTransfer>) assetTransferDao.findAll();
		String constractId = "";
		Contract contract = null;
		for (AssetTransfer assetTransfer:assetTransfers) {
			if(constractId.equals(assetTransfer.getLoanContractId())) {
				constractId = assetTransfer.getLoanContractId();
			} else {
				contract = contractDao.findOne(assetTransfer.getLoanContractId());
			}
			result.add(new ModelExt(
						   new AssetTransferModel(assetTransfer.getId(), 
								   assetTransfer.getLoanContractId(),
								   assetTransfer.getTransferNumber(),
								   assetTransfer.getTransferMoney().toPlainString(),
								   assetTransfer.getOriginalMoney().toPlainString(),
								   assetTransfer.getDiscountMoney().toPlainString(),
								   assetTransfer.getAcceptUnit(),
								   assetTransfer.getProtocol(),
								   DateUtil.getDateStr(assetTransfer.getTransferDate(), "yyyy-MM-dd")),
						   
						   contract.getContractNumber(),
						   contract.getContractName(),
						   assetTransfer.getPushStatus()));
		}
		return result;
		
	}
	
	@Override
	@Transactional
	public void syncOtherBaddebtPushStatus(){
		otherBaddebtDAO.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS,PushStatus.DELETEING)).forEach(s->{
			api.queryTranInfoApi(s.getId(), Cmd.loanInfo, s.getReqId(),s.getPushStatus());
		});;
	}
	
	@Override
	@Transactional
	public void syncAssetTransferPushStatus(){
		assetTransferDao.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS,PushStatus.DELETEING)).forEach(s->{
			api.queryTranInfoApi(s.getId(), Cmd.loanInfo, s.getReqId(),s.getPushStatus());
		});;
	}

}
