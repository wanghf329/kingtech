package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayPlan;

public interface RepayPlanDAO extends PagingAndSortingRepository<RepayPlan, String> {
	
	@Query("select r from RepayPlan r where r.loanContractId = :loanContractId")
	public List<RepayPlan> listByloanContractId(@Param("loanContractId")String loanContractId);
}
