package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.BranchAccountBalance;
import com.kingtech.enums.PushStatus;

public interface BranchAccountBalanceDAO extends PagingAndSortingRepository<BranchAccountBalance, String> {

	@Modifying
	@Query("update BranchAccountBalance c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Modifying
	@Query("delete BranchAccountBalance c where c.dateMonth = :dateMonth ")
	public void deleteByMonth(@Param("dateMonth")String dateMonth);
	

}
