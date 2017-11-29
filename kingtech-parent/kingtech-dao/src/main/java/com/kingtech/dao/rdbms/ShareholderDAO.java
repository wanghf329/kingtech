package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Shareholder;
import com.kingtech.enums.PushStatus;

public interface ShareholderDAO extends PagingAndSortingRepository<Shareholder, String> {
	
	@Modifying
	@Query("update Shareholder s set s.pushStatus = :pushStatus  where s.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	
}
