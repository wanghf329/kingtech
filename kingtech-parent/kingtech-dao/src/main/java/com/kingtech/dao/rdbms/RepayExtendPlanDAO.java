package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayExtendPlan;

public interface RepayExtendPlanDAO extends PagingAndSortingRepository<RepayExtendPlan, String> {
	
	@Query("select r from RepayExtendPlan r where r.loanContractId = :loanContractId order by loanContractId,r.repayDate ")
	public List<RepayExtendPlan> listByloanContractId(@Param("loanContractId")String loanContractId);
	
}
