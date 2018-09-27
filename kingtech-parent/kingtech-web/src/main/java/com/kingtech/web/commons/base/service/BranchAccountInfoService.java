package com.kingtech.web.commons.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.BranchAccountInfo;
import com.kingtech.model.BranchAccountInfoModel;
import com.kingtech.model.misc.PagedResult;

public interface BranchAccountInfoService {
	
    public BranchAccountInfo addNew(BranchAccountInfoModel model);
	
	public List<BranchAccountInfo> listAll();
	
	public BranchAccountInfo getById(String id);
	
	public PagedResult<BranchAccountInfo> pageList(Pageable pageAble);
	

}
