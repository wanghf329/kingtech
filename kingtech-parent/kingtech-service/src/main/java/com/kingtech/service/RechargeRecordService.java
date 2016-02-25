package com.kingtech.service;

import org.springframework.data.domain.PageRequest;

import com.kingtech.dao.entity.RechargeRecord;
import com.kingtech.model.RechargeRecordModel;
import com.kingtech.model.misc.PagedResult;

public interface RechargeRecordService {
	/**
	 * recharge add
	 * @param rechargeRecord
	 */
	public void add(RechargeRecord rechargeRecord);
	
	
	/**
	 * list pagging
	 * @param pageRequest
	 * @return
	 */
	public PagedResult<RechargeRecordModel> listPaging(PageRequest pageRequest);
}
