package com.kingtech.web.commons.base.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.CapitalModel;
import com.kingtech.web.commons.base.service.CapitalService;

@Service
public class CapitalServiceImpl implements CapitalService{
	
	@Autowired
	private CapitalDAO capitalDao;

	@Override
	@Transactional
	public Capital addNew(String id,String financingChannel, double financingMoney,
			String financingTime, String expirationTime, String replyTime,String branchId) {
		try {
			Capital capital = new Capital(financingChannel,new BigDecimal(financingMoney),
					DateUtils.parseDate(financingTime, "yyyy-MM-dd"),
					DateUtils.parseDate(financingTime, "yyyy-MM-dd"),
					DateUtils.parseDate(financingTime, "yyyy-MM-dd"),branchId,"11100011",PushStatus.INPROSESS);
			capital.setId(id);
			capital = capitalDao.save(capital);
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
		capitalDao.delete(id);
	}

}
