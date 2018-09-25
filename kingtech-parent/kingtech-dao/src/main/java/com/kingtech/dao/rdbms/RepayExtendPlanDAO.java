package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.enums.PushStatus;

public interface RepayExtendPlanDAO extends PagingAndSortingRepository<RepayExtendPlan, String> {
	
	@Query("select r from RepayExtendPlan r where r.loanContractId = :loanContractId order by loanContractId ")
	public List<RepayExtendPlan> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	

	@Modifying
	@Query("update RepayExtendPlan c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);

}
