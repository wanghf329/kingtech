package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.web.commons.base.service.CapitalService;

@Service
public class CapitalServiceImpl implements CapitalService{
	
	@Autowired
	private CapitalDAO capitalDao;

	@Override
	@Transactional
	public Capital addNew(String financingChannel, double financingMoney,
			String financingTime, String expirationTime, String replyTime,String branchId) {
		try {
			Capital capital = capitalDao.save(new Capital(financingChannel,financingMoney,
					DateUtils.parseDate(financingTime, "yyyy-MM-dd"),
					DateUtils.parseDate(financingTime, "yyyy-MM-dd"),
					DateUtils.parseDate(financingTime, "yyyy-MM-dd"),branchId,"11100011",PushStatus.INPROSESS));
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

}
