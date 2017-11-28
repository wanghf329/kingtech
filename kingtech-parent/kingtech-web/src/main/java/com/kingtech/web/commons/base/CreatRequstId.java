package com.kingtech.web.commons.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingtech.common.config.BaseConfig;
import com.kingtech.dao.entity.RequestId;
import com.kingtech.dao.rdbms.RequestIdDAO;


@Service
public class CreatRequstId {

	@Autowired
	private RequestIdDAO requestIdDAO;
	
	private static  byte[] lock = new byte[0];
	
	
	public  synchronized  String getReqId(){
		
		List<RequestId> list = (List<RequestId>) requestIdDAO.findAll();
		if (list.isEmpty()) {
			RequestId requestId = new RequestId(null, 1);
			requestIdDAO.save(requestId);
			return BaseConfig.CREDITCODE+String.format("%05d", 1);
		}
		requestIdDAO.updateRequest(list.get(0).getId());
		
		return  BaseConfig.CREDITCODE+String.format("%05d",list.get(0).getRequestId()+1);
	}
}
