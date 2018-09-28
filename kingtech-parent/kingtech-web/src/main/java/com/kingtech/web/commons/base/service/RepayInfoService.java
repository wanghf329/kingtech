package com.kingtech.web.commons.base.service;

import org.springframework.data.domain.Pageable;

import com.kingtech.model.ext.ModelExt;
import com.kingtech.model.misc.PagedResult;

public interface RepayInfoService {
	/**
	 * 
	 * @param pageAble
	 * @return
	 */
	public PagedResult<ModelExt> pageList(Pageable pageAble);
	
	/**
	 * 
	 */
	public void syncRepayInfoPushStatus();
}
