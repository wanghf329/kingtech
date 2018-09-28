package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.dao.rdbms.ProvisionInfoDAO;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.ProvisionService;

@Slf4j
@Service
public class ProvisionServiceImpl implements ProvisionService{
	@Autowired
	private ProvisionInfoDAO provisionDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;

	@Override
	@Transactional
	public void addOrEdit(String id, Date dateMonth,
			BigDecimal normalBalance, BigDecimal normalRate,
			BigDecimal normalReal, BigDecimal followBalance,
			BigDecimal followRate, BigDecimal followReal,
			BigDecimal minorBalance, BigDecimal minorRate,
			BigDecimal minorReal, BigDecimal suspiciousBalance,
			BigDecimal suspiciousRate, BigDecimal suspiciousReal,
			BigDecimal lossBalance, BigDecimal lossRate, BigDecimal lossReal) {
		ProvisionInfo pi = null;
		if(StringUtils.isEmpty(id)){
			pi = new ProvisionInfo(creatRequstId.getReqId(), PushStatus.INITATION, RecordStatus.NORMAL, dateMonth,
					normalBalance, normalRate, normalReal, 
					followBalance, followRate, followReal,
					minorBalance, minorRate, minorReal, 
					suspiciousBalance, suspiciousRate, suspiciousReal,
					lossBalance, lossRate, lossReal);
		}else{
			pi = provisionDao.findOne(id);
			pi.setDateMonth(dateMonth);
			pi.setNormalBalance(normalBalance);
			pi.setNormalRate(normalRate);
			pi.setNormalReal(normalReal);
			pi.setFollowBalance(followBalance);
			pi.setFollowRate(followRate);
			pi.setFollowReal(followReal);
			pi.setMinorBalance(minorBalance);
			pi.setMinorRate(minorRate);
			pi.setMinorReal(minorReal);
			pi.setSuspiciousBalance(suspiciousBalance);
			pi.setSuspiciousRate(suspiciousRate);
			pi.setSuspiciousReal(suspiciousReal);
			pi.setLossBalance(lossBalance);
			pi.setLossRate(lossRate);
			pi.setLossReal(lossReal);
		}
		provisionDao.save(pi);
		
//		paymentApi.provisionInfoApi(pi.getId(),StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
	}

	@Override
	public List<ProvisionInfo> listAll() {
		return (List<ProvisionInfo>)provisionDao.findAll();
	}

	@Override
	public ProvisionInfoModel getById(String id) {
		ProvisionInfo pi = provisionDao.findOne(id);
		return new ProvisionInfoModel(pi.getId(), DateFormatUtils.format(pi.getDateMonth(),"yyyy-MM-dd"), 
									  pi.getNormalBalance().toPlainString(), 
									  pi.getNormalRate().toPlainString(),
									  pi.getNormalReal().toPlainString(),
									  pi.getFollowBalance().toPlainString(),
									  pi.getFollowRate().toPlainString(),
									  pi.getFollowReal().toPlainString(),
									  pi.getMinorBalance().toPlainString(),
									  pi.getMinorRate().toPlainString(),
									  pi.getMinorReal().toPlainString(),
									  pi.getSuspiciousBalance().toPlainString(),
									  pi.getSuspiciousRate().toPlainString(),
									  pi.getSuspiciousReal().toPlainString(),
									  pi.getLossBalance().toPlainString(),
									  pi.getLossRate().toPlainString(),
									  pi.getLossReal().toPlainString());
	}
	
	@Override
	@Transactional
	public void syncProvisionInfoPushStatus(){
		provisionDao.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS,PushStatus.DELETEING)).forEach(s->{
			paymentApi.queryTranInfoApi(s.getId(), Cmd.loanInfo, s.getReqId(),s.getPushStatus());
		});;
	}
}
