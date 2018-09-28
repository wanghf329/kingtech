package com.kingtech.web.commons.base.service;

import org.springframework.data.domain.Pageable;

import com.kingtech.model.DayEndDzModel;
import com.kingtech.model.misc.PagedResult;

public interface DayEndService {
	PagedResult<DayEndDzModel> pageListDayEndInfo(Pageable pageAble);
	
	public void save(DayEndDzModel model);
}
