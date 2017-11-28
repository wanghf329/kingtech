package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.entity.RequestId;

public interface RequestIdDAO extends PagingAndSortingRepository<RequestId, String> {
	
	@Modifying
	@Query("update RequestId r set r.requestId = r.requestId +1  where r.id= :id")
	public int updateRequest(@Param("id")String id);
	
}
