package com.kingtech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingtech.dao.entity.TrafficPrice;
import com.kingtech.dao.rdbms.TrafficPriceDAO;
import com.kingtech.service.TrafficService;

@Service
public class TrafficServiceImpl implements TrafficService {
	
	@Autowired
	private TrafficPriceDAO trafficePriceDao;

	@Override
	public void add(TrafficPrice trafficPrice) {
		trafficePriceDao.save(trafficPrice);
	}
}
