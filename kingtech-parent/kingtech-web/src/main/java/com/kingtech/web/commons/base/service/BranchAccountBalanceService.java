package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.BranchAccountBalance;
import com.kingtech.model.BranchAccountBalanceModel;
import com.kingtech.model.misc.PagedResult;

public interface BranchAccountBalanceService {
	
	public BranchAccountBalance addNew(BranchAccountBalanceModel model);
	
	public List<BranchAccountBalance> listAll();
	
	public BranchAccountBalance getById(String id);
	
	public PagedResult<BranchAccountBalance> pageList(Pageable pageAble);

}
