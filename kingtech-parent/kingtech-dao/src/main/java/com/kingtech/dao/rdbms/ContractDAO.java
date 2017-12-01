package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Contract;
import com.kingtech.enums.PushStatus;

public interface ContractDAO extends PagingAndSortingRepository<Contract, String> {
	@Modifying
	@Query("update Contract c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Query("from Contract c where c.borrowerId = :borrowerId and c.pushStatus in :pushStatus ")
	public List<Contract> listByUserIdAndPushstatus(@Param("borrowerId") String borrowerId,@Param("pushStatus") List<PushStatus> pushStatus);
}
