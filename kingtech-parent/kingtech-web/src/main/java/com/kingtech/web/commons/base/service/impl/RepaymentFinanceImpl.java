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
import com.kingtech.dao.entity.RepaymentFinance;
import com.kingtech.dao.rdbms.RepaymentFinanceDao;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.RepaymentFinanceModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.RepaymentFinanceService;

@Service
public class RepaymentFinanceImpl implements RepaymentFinanceService{

	@Autowired
	private RepaymentFinanceDao repaymentFinanceDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private DynamicQuery dq;
	
	private static final String LISTREPAYMENTFINANCESQL = "SELECT * from TB_REPAYMENT_FINANCE t where record_status ='NORMAL'  order by t.REPAY_DATE DESC";
	
	@Override
	@Transactional
	public RepaymentFinance addNew(RepaymentFinanceModel model) {
		RepaymentFinance repaymentFinance = null;
		IdentifierType type = null;
		try {
			if (StringUtils.isEmpty(model.getId())) {
				repaymentFinance = new RepaymentFinance();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(repaymentFinance, model);
				type = IdentifierType.A;
			} else {
				repaymentFinance = repaymentFinanceDao.findOne(model.getId());
				String reqId = repaymentFinance.getReqId();
				BeanUtils.copyProperties(repaymentFinance, model);
				repaymentFinance.setReqId(reqId);
				repaymentFinance.setPushStatus(PushStatus.INITATION);
				type = IdentifierType.U;
			}
			repaymentFinance.setRecordStatus(RecordStatus.NORMAL);
			repaymentFinance = repaymentFinanceDao.save(repaymentFinance);
			//paymentApi.financeInfoApi(financeInfoId, type)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repaymentFinance;
	}

	@Override
	public List<RepaymentFinance> listAll() {
		return (List)repaymentFinanceDao.findAll();
	}

	@Override
	public RepaymentFinance getById(String id) {
		return repaymentFinanceDao.findOne(id);
	}

	@Override
	public PagedResult<RepaymentFinance> pageList(Pageable pageAble) {
		String[] params = new String[0];
		List<RepaymentFinance> list = dq.nativeQueryPagingList(RepaymentFinance.class, pageAble, LISTREPAYMENTFINANCESQL, params);
		Long count = dq.nativeQueryCount(LISTREPAYMENTFINANCESQL, params);
		return new PagedResult(list,count);
	}

}
