package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayExtendPlanInfo;
import com.kingtech.enums.PushStatus;

public interface RepayExtendPlanInfoDAO extends PagingAndSortingRepository<RepayExtendPlanInfo, String> {
	
	@Query("select r from RepayExtendPlanInfo r where r.pushStatus in :pushStatus ")
	public List<RepayExtendPlanInfo> listByPushStatus(@Param("pushStatus")List<PushStatus> pushStatus);
	
	
	@Modifying
	@Query("delete RepayExtendPlanInfo c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
