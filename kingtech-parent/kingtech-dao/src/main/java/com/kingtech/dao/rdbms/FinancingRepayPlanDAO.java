package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.FinanceRepayPlan;

public interface FinancingRepayPlanDAO extends PagingAndSortingRepository<FinanceRepayPlan, String> {
	
	@Query("select c from FinancingRepayPlan c where c.financeId = :financeId  order by c.endDate")
	public List<FinanceRepayPlan> listByFinanceId(@Param("financeId")String financeId);
}
