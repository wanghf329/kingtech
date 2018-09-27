package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.kingtech.common.dynamicquery.DynamicQuery;
import com.kingtech.dao.entity.BranchAccountBalance;
import com.kingtech.dao.rdbms.BranchAccountBalanceDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.BranchAccountBalanceModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.BranchAccountBalanceService;

@Service
public class BranchAccountBalanceServiceImpl implements BranchAccountBalanceService{
	
	@Autowired
	private BranchAccountBalanceDAO branchAccountBalanceDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private DynamicQuery dq;
	
	private static final String LISTACOUNTMONTHBALANCESQL = "SELECT * from TB_BRANCH_ACCOUNT_BALANCE t order by t.DATE_MONTH DESC";

	@Override
	public BranchAccountBalance addNew(BranchAccountBalanceModel model) {
		BranchAccountBalance branchAccountBalance = null;
		try {
			if (StringUtils.isEmpty(model.getId())) {
				branchAccountBalance = new BranchAccountBalance();
				model.setReqId(creatRequstId.getReqId());
				model.setPushStatus(PushStatus.INITATION);
				BeanUtils.copyProperties(branchAccountBalance, model);
			} else {
				branchAccountBalance = branchAccountBalanceDao.findOne(model.getId());
				String reqId = branchAccountBalance.getReqId();
				BeanUtils.copyProperties(branchAccountBalance, model);
				branchAccountBalance.setReqId(reqId);
				branchAccountBalance.setPushStatus(PushStatus.INITATION);
			}
			branchAccountBalance.setRecordStatus(RecordStatus.NORMAL);
			branchAccountBalance = branchAccountBalanceDao.save(branchAccountBalance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchAccountBalance;
	}

	@Override
	public List<BranchAccountBalance> listAll() {
		return (List)branchAccountBalanceDao.findAll();
	}

	@Override
	public BranchAccountBalance getById(String id) {
		return branchAccountBalanceDao.findOne(id);
	}

	@Override
	public PagedResult<BranchAccountBalance> pageList(Pageable pageAble) {
		String[] params = new String[0];
		List<BranchAccountBalance> list = dq.nativeQueryPagingList(BranchAccountBalance.class, pageAble, LISTACOUNTMONTHBALANCESQL, params);
		Long count = dq.nativeQueryCount(LISTACOUNTMONTHBALANCESQL, params);
		return new PagedResult(list,count);
	}

}
