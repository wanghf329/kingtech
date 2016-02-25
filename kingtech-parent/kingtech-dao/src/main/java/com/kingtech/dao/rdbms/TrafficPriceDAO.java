package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.TrafficPrice;

public interface TrafficPriceDAO extends PagingAndSortingRepository<TrafficPrice, String> {
	
}
