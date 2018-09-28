package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.RepaymentFinance;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.RepaymentFinanceModel;
import com.kingtech.model.misc.PagedResult;

public interface RepaymentFinanceService {
	
    public RepaymentFinance addNew(RepaymentFinanceModel model);
	
	public List<RepaymentFinance> listAll();
	
	public RepaymentFinance getById(String id);
	
	public PagedResult<RepaymentFinance> pageList(Pageable pageAble);
	
	public void syncRepaymentPushStatus();
	
	public List<RepaymentFinance> listByPushStatus(List<PushStatus> pushStatus);

}
