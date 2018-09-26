package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.rdbms.CapitalDAO;
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
	public Capital addNew(CapitalModel model) {
		try {
			Capital capital = null;
			if (StringUtils.isEmpty(model.getId())) {
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(capital, model);
			} else {
				capital = capitalDao.findOne(model.getId());
				String reqId = capital.getReqId();
				BeanUtils.copyProperties(capital, model);
				capital.setId(reqId);
				capital.setPushStatus(PushStatus.INITATION);
			}
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
	public Capital getById(String id) {
		return capitalDao.findOne(id);
	}

}
