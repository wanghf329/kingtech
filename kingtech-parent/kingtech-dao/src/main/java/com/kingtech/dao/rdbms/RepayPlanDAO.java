package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayPlan;

public interface RepayPlanDAO extends PagingAndSortingRepository<RepayPlan, String> {
	
	@Query("select r from RepayPlan r where r.loanContractId = :loanContractId order by r.endDate ")
	public List<RepayPlan> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("delete RepayPlan c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
