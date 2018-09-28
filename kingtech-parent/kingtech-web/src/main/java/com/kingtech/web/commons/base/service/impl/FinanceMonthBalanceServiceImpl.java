package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.FinanceMonthBalance;
import com.kingtech.dao.rdbms.FinanceMonthBalanceDAO;
import com.kingtech.enums.Cmd;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.FinanceMonthBalanceModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.FinanceMonthBalanceService;

@Slf4j
@Service
public class FinanceMonthBalanceServiceImpl implements FinanceMonthBalanceService {

	@Autowired
	private FinanceMonthBalanceDAO financeMonthBalanceDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private DynamicQuery dq;
	
	private static final String LISTFINANCEMONTHBALANCESQL = "SELECT * from TB_FINANCE_MONTH_BALANCE t where RECORD_STATUS = 'NORMAL' order by t.FINANCE_MOTTH DESC";
	
	@Override
	@Transactional
	public FinanceMonthBalance addNew(FinanceMonthBalanceModel model) {
		FinanceMonthBalance financeMonthBalance = null;
		IdentifierType type = null;
		try {
			if (StringUtils.isEmpty(model.getId())) {
				financeMonthBalance = new FinanceMonthBalance();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(financeMonthBalance, model);
				type = IdentifierType.A;
			} else {
				financeMonthBalance = financeMonthBalanceDao.findOne(model.getId());
				String reqId = financeMonthBalance.getReqId();
				BeanUtils.copyProperties(financeMonthBalance, model);
				financeMonthBalance.setReqId(reqId);
				financeMonthBalance.setPushStatus(PushStatus.INITATION);
				type = IdentifierType.U;
			}
			financeMonthBalance.setRecordStatus(RecordStatus.NORMAL);
			financeMonthBalance = financeMonthBalanceDao.save(financeMonthBalance);
			paymentApi.financeMonthBalanceApi(financeMonthBalance.getId(), type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return financeMonthBalance;
	}

	@Override
	public List<FinanceMonthBalance> listAll() {
		return (List)financeMonthBalanceDao.findAll();
	}

	@Override
	public FinanceMonthBalance getById(String id) {
		return financeMonthBalanceDao.findOne(id);
	}

	@Override
	public PagedResult<FinanceMonthBalance> pageList(Pageable pageAble) {
		String[] params = new String[0];
		List<FinanceMonthBalance> list = dq.nativeQueryPagingList(FinanceMonthBalance.class, pageAble, LISTFINANCEMONTHBALANCESQL, params);
		Long count = dq.nativeQueryCount(LISTFINANCEMONTHBALANCESQL, params);
		return new PagedResult(list,count);
	}

	@Override
	public void syncMonthBalancePushStatus() {
		financeMonthBalanceDao.listBypushStatus(Lists.newArrayList(PushStatus.INPROSESS, PushStatus.DELETEING)).forEach(s->{
			paymentApi.queryTranInfoApi(s.getId(), Cmd.monthFinance, s.getReqId(),s.getPushStatus());
		});
		
	}

}
