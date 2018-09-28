package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.BranchAccountBalance;
import com.kingtech.dao.entity.BranchAccountInfo;
import com.kingtech.enums.PushStatus;

public interface BranchAccountInfoDAO extends PagingAndSortingRepository<BranchAccountInfo, String> {

	@Modifying
	@Query("update BranchAccountInfo c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Query("select c from  BranchAccountInfo c where c.pushStatus in :pushStatus ")
	public List<BranchAccountInfo> listBypushStatus(@Param("pushStatus")List<PushStatus> pushStatus);
}
