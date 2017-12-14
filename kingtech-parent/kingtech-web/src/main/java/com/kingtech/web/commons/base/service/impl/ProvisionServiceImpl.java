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

import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.dao.rdbms.ProvisionInfoDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.LoanClassificationEnum;
import com.kingtech.enums.PushStatus;
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
	public void addOrEdit(String id,BigDecimal provisionMoney,
			Date provisionDate, BigDecimal provisionScale,
			LoanClassificationEnum loanClassification, BigDecimal balance) {
		ProvisionInfo pi = null;
		if(StringUtils.isEmpty(id)){
			pi = new ProvisionInfo(creatRequstId.getReqId(), PushStatus.INITATION, provisionMoney, provisionDate, provisionScale, loanClassification, balance);
		}else{
			pi = provisionDao.findOne(id);
			pi.setProvisionMoney(provisionMoney);
			pi.setProvisionDate(provisionDate);
			pi.setProvisionScale(provisionScale);
			pi.setLoanClassification(loanClassification);
			pi.setBalance(balance);
		}
		provisionDao.save(pi);
		
		paymentApi.provisionInfoApi(pi.getId(),StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
	}

	@Override
	public List<ProvisionInfo> listAll() {
		return (List<ProvisionInfo>)provisionDao.findAll();
	}

	@Override
	public ProvisionInfoModel getById(String id) {
		ProvisionInfo pi = provisionDao.findOne(id);
		return new ProvisionInfoModel(pi.getId(), pi.getProvisionMoney().toPlainString(), 
									  DateFormatUtils.format(pi.getProvisionDate(),"yyyy-MM-dd"), 
									  pi.getProvisionScale().toPlainString(), 
									  pi.getLoanClassification().name(), 
									  pi.getBalance().toPlainString());
	}
}
