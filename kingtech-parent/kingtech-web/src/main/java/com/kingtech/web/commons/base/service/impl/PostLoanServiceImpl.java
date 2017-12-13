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

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.RepayInfoDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.service.PostLoanService;


@Service
public class PostLoanServiceImpl implements PostLoanService{
	
	@Autowired
	private ContractDAO contractDao;
	
	@Autowired
	private RepayInfoDAO repayInfoDao;
	
	@Autowired
	private CreatRequstId creatRequstId;

	@Override
	public List<Contract> listAllContract() {
		return (List<Contract>) contractDao.findAll();
	}

	@Override
	public List<RepayInfo> listAllRepayInfo() {
		return (List<RepayInfo>) repayInfoDao.findAll();
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
				repayInfo.setUpdateTime(new Date());
				repayInfo.setRepayDate(DateUtils.parseDate(repayDate, "yyyy-MM-dd"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repayInfoDao.save(repayInfo);
		return repayInfo;
	}
	

}
