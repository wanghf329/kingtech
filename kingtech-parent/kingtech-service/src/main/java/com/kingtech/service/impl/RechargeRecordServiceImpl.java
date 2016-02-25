package com.kingtech.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kingtech.dao.entity.RechargeRecord;
import com.kingtech.dao.rdbms.RechargeRecordDAO;
import com.kingtech.model.RechargeRecordModel;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.service.RechargeRecordService;
import com.kingtech.utils.DTOUtils;

@Service
public class RechargeRecordServiceImpl implements RechargeRecordService{

	@Autowired
	private RechargeRecordDAO rechargeDao;
	
	@Override
	public void add(RechargeRecord rechargeRecord) {
		rechargeDao.save(rechargeRecord);
	}

	@Override
	public PagedResult<RechargeRecordModel> listPaging(PageRequest pageRequest) {
		Page<RechargeRecord> result = rechargeDao.findAll(pageRequest);
		
		List<RechargeRecordModel> resultModel = new ArrayList<RechargeRecordModel>();
		for(RechargeRecord item : result.getContent()){
			resultModel.add(DTOUtils.getRechargeRecordModel(item));
		}
		return new PagedResult<RechargeRecordModel>(resultModel,result.getTotalElements());
	}
}
