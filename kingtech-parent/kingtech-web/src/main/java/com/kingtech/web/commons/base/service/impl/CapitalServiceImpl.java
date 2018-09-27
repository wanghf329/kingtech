package com.kingtech.web.commons.base.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.FinanceRepayPlan;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.dao.rdbms.FinanceRepayPlanDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.FinanceRepayPlanModel;
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
	
	@Autowired
	private FinanceRepayPlanDAO financeRepayPlanDAO;
	
	private static final String LISTCAPITALSQL = "SELECT * from TB_BRANCH_CAPITAL t where t.RECORD_STATUS = 'NORMAL'  order by t.CREATE_TIME DESC";
	
	@Override
	@Transactional
	public Capital addNew(CapitalModel model) {
		Capital capital = null;
		IdentifierType type = null;
		try {
			if (StringUtils.isEmpty(model.getId())) {
				capital = new Capital();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(capital, model);
				type = IdentifierType.A;
			} else {
				capital = capitalDao.findOne(model.getId());
				String reqId = capital.getReqId();
				BeanUtils.copyProperties(capital, model);
				capital.setReqId(reqId);
				capital.setPushStatus(PushStatus.INITATION);
				type = IdentifierType.U;
			}
			capital.setRecordStatus(RecordStatus.NORMAL);
			capital = capitalDao.save(capital);
			paymentApi.capitalInfoApi(capital.getId(), type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return capital;
	}

	@Override
	public List<Capital> listAll() {
		return (List)capitalDao.findAll();
	}
	
	public List<Capital> listFinanceNumberByStatus(RecordStatus status) {
		return capitalDao.listCapitalByRecordStatus(status);
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
	
	@Override
	@Transactional
	public void addRepayPlan(String financeId, List<FinanceRepayPlanModel> repayPlanList) {
		financeRepayPlanDAO.delectByFinanceId(financeId);
		for (FinanceRepayPlanModel re : repayPlanList) {
			financeRepayPlanDAO.save(new FinanceRepayPlan(financeId, re.getInterest(), re.getEndDate(), re.getMoney(), re.getOrderNum()));
		}
		Capital capital = capitalDao.findOne(financeId);
		paymentApi.capitalInfoApi(capital.getId(), IdentifierType.U);
	}


}
