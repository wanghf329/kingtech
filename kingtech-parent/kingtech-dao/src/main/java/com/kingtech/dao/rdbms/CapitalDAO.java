package com.kingtech.dao.rdbms;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Capital;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.RecordStatus;

public interface CapitalDAO extends PagingAndSortingRepository<Capital, String> {
	
	@Modifying
	@Query("update Capital c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Query("select c.id, c.financeNumber from Capital c   where c.recordStatus = :status")
	public List<Capital> listCapitalByRecordStatus(@Param("status")RecordStatus status);
	
}
