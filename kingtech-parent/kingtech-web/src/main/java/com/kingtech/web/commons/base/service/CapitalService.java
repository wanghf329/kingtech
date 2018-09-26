package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.Capital;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.misc.PagedResult;




public interface CapitalService {
	
	public Capital addNew(CapitalModel model);
	
	public List<Capital> listAll();
	
	public Capital getById(String id);
	
	public PagedResult<Capital> pageList(Pageable pageAble);
}
