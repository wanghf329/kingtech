package com.kingtech.web.commons.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.rdbms.ContractDAO;
import com.kingtech.web.commons.base.service.ContractService;
/**
 * 合同信息
 * @author XA_JKWHF
 *
 */
@Service
public class ContractServiceImpl implements ContractService{
	@Autowired
	private ContractDAO contractDao;
	
	@Override
	public List<Contract> listAll(){
		return (List)contractDao.findAll();
	}
}
