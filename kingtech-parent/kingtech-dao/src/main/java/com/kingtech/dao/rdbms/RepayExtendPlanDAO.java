package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayExtendPlan;
import com.kingtech.enums.PushStatus;

public interface RepayExtendPlanDAO extends PagingAndSortingRepository<RepayExtendPlan, String> {

	@Query("select r from RepayExtendPlan r where r.repayExtendPlanInfoId = :repayExtendPlanInfoId ")
	public List<RepayExtendPlan> listByRepayExtendPlanInfoId(@Param("repayExtendPlanInfoId") String repayExtendPlanInfoId);
	
	@Modifying
	@Query("delete RepayExtendPlan c where c.repayExtendPlanInfoId = :repayExtendPlanInfoId ")
	public void deleteByPlanInfoId(@Param("repayExtendPlanInfoId") String repayExtendPlanInfoId);
}
