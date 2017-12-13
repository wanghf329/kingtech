package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.RepayExtendInfo;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.dao.rdbms.RepayExtendInfoDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.ext.RepayExtendInfoModelExt;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.ExtendRepayService;

@Slf4j
@Service
public class ExtendRepayServiceImpl implements ExtendRepayService{
	@Autowired
	private RepayExtendInfoDAO repayExtendInfoDao;
	
	@Autowired
	private ContractDAO contractDAO;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;

	@Override
	@Transactional
	public void addOrEdit(String id,String loanContractId,
			long extendNum, Date repayDate,
			BigDecimal repayAmount, BigDecimal repayPrincipalAmount,
			BigDecimal repayInterestAmount) {
		RepayExtendInfo ri = null;
		if (StringUtils.isEmpty(id)) {
			ri = new RepayExtendInfo(loanContractId,creatRequstId.getReqId(),
					PushStatus.INITATION,extendNum,repayDate,
					repayAmount,repayPrincipalAmount,
					repayInterestAmount);
		}else{
			ri = repayExtendInfoDao.findOne(id);
			ri.setExtendNum(extendNum);
			ri.setRepayDate(repayDate);
			ri.setRepayAmount(repayAmount);
			ri.setRepayPrincipalAmount(repayPrincipalAmount);
			ri.setRepayInterestAmount(repayInterestAmount);
		}
		repayExtendInfoDao.save(ri);
		paymentApi.repayExtendInfoApi(ri.getId(), StringUtils.isEmpty(id) ?  IdentifierType.A : IdentifierType.U);
	}

	@Override
	public List<RepayExtendInfoModelExt> listAll() {
		List<RepayExtendInfoModelExt> result = new ArrayList<RepayExtendInfoModelExt>();
		for(RepayExtendInfo rf : repayExtendInfoDao.findAll()){
			Contract ct = contractDAO.findOne(rf.getLoanContractId());
			result.add(new RepayExtendInfoModelExt(rf.getId(), rf.getLoanContractId(),
					String.valueOf(rf.getExtendNum()), DateFormatUtils.format(rf.getRepayDate(), "yyyy-MM-dd"), 
					rf.getRepayAmount().toPlainString(), 
					rf.getRepayPrincipalAmount().toPlainString(), 
					rf.getRepayInterestAmount().toPlainString(),ct.getLoanContractId(),ct.getLoanContractName(),rf.getPushStatus()));
		}
		return result;
	}

	@Override
	public RepayExtendInfoModel getById(String id) {
		RepayExtendInfo rf = repayExtendInfoDao.findOne(id);
		return new RepayExtendInfoModel(rf.getId(), rf.getLoanContractId(),
										String.valueOf(rf.getExtendNum()), DateFormatUtils.format(rf.getRepayDate(), "yyyy-MM-dd"), 
										rf.getRepayAmount().toPlainString(), 
										rf.getRepayPrincipalAmount().toPlainString(), 
										rf.getRepayInterestAmount().toPlainString());
	}
}
