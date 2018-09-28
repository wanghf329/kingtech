package com.kingtech.dao.rdbms;

import java.util.List;

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
	
	@Query("select c from  BranchAccountBalance c where c.pushStatus in :pushStatus  ")
	public List<BranchAccountBalance> listBypushStatus(@Param("pushStatus")List<PushStatus> pushStatus);
	

}
