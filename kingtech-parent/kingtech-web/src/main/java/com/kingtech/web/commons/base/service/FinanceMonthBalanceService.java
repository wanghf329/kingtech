package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.FinanceMonthBalance;
import com.kingtech.model.FinanceMonthBalanceModel;
import com.kingtech.model.misc.PagedResult;

public interface FinanceMonthBalanceService {

	public FinanceMonthBalance addNew(FinanceMonthBalanceModel model);
		
	public List<FinanceMonthBalance> listAll();
	
	public FinanceMonthBalance getById(String id);
	
	public PagedResult<FinanceMonthBalance> pageList(Pageable pageAble);
}
