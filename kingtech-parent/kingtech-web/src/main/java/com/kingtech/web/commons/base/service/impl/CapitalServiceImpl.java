package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.CapitalModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.CapitalService;

@Service
public class CapitalServiceImpl implements CapitalService{
	
	@Autowired
	private CapitalDAO capitalDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Override
	@Transactional
	public Capital addNew(String id,String financingChannel, double financingMoney,
			String financingTime, String expirationTime, String replyTime,String branchId) {
		try {
			Capital capital = null;
			if(StringUtils.isEmpty(id)){
				capital = new Capital(financingChannel,new BigDecimal(financingMoney),
						DateUtils.parseDate(financingTime, "yyyy-MM-dd"),
						DateUtils.parseDate(expirationTime, "yyyy-MM-dd"),
						replyTime == null ? null : DateUtils.parseDate(replyTime, "yyyy-MM-dd"),
								branchId,creatRequstId.getReqId(),PushStatus.INITATION);
			}else{
				capital = capitalDao.findOne(id);
				capital.setFinancingChannel(financingChannel);
				capital.setFinancingMoney(new BigDecimal(financingMoney));
				capital.setFinancingTime(DateUtils.parseDate(financingTime, "yyyy-MM-dd"));
				capital.setExpirationTime(DateUtils.parseDate(expirationTime, "yyyy-MM-dd"));
				capital.setReplyTime(replyTime == null ? null : DateUtils.parseDate(replyTime, "yyyy-MM-dd"));
				capital.setPushStatus(PushStatus.INITATION);
			}
			capital.setId(id);
			capital = capitalDao.save(capital);
			
			paymentApi.CapitalInfoApi(capital.getId(), StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
			
			return capital;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Capital> listAll() {
		return (List)capitalDao.findAll();
	}

	@Override
	public CapitalModel getById(String id) {
		Capital capital =  capitalDao.findOne(id);
		return new CapitalModel(capital.getId(),capital.getFinancingChannel(),capital.getFinancingMoney().setScale(2).toPlainString(), 
				DateFormatUtils.format(capital.getFinancingTime(), "yyyy-MM-dd"), 
				DateFormatUtils.format(capital.getExpirationTime(), "yyyy-MM-dd"),
				DateFormatUtils.format(capital.getReplyTime(), "yyyy-MM-dd"));
	}

	@Override
	public void delById(String id) {
		paymentApi.CapitalInfoApi(id, IdentifierType.D);
		capitalDao.delete(id);
	}

}
