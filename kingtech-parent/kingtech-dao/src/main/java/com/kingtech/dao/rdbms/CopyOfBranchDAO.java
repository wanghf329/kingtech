package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Branch;
import com.kingtech.enums.PushStatus;

public interface CopyOfBranchDAO extends PagingAndSortingRepository<Branch, String> {
	@Modifying
	@Query("update Branch b set b.pushStatus = :pushStatus  where b.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	
}
