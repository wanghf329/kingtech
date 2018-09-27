package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.misc.PagedResult;
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
	
	@Autowired
	private DynamicQuery dq;
	
	private static final String LISTCAPITALSQL = "SELECT * from TB_BRANCH_CAPITAL t order by t.CREATE_TIME DESC";
	
	@Override
	@Transactional
	public Capital addNew(CapitalModel model) {
		Capital capital = null;
		try {
			if (StringUtils.isEmpty(model.getId())) {
				capital = new Capital();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(capital, model);
			} else {
				capital = capitalDao.findOne(model.getId());
				String reqId = capital.getReqId();
				BeanUtils.copyProperties(capital, model);
				capital.setReqId(reqId);
				capital.setPushStatus(PushStatus.INITATION);
			}
			capital = capitalDao.save(capital);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return capital;
	}

	@Override
	public List<Capital> listAll() {
		return (List)capitalDao.findAll();
	}

	@Override
	public Capital getById(String id) {
		return capitalDao.findOne(id);
	}

	@Override
	public PagedResult<Capital> pageList(Pageable pageAble) {
		String[] params = new String[0];
		List<Capital> list = dq.nativeQueryPagingList(Capital.class, pageAble, LISTCAPITALSQL, params);
		Long count = dq.nativeQueryCount(LISTCAPITALSQL, params);
		return new PagedResult(list,count);
	}


}
