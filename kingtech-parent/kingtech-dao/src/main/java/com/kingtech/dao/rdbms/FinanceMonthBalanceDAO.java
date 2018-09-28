package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.FinanceMonthBalance;
import com.kingtech.enums.PushStatus;

public interface FinanceMonthBalanceDAO extends PagingAndSortingRepository<FinanceMonthBalance, String> {
	
	@Modifying
	@Query("update FinanceMonthBalance c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Modifying
	@Query("delete FinanceMonthBalance c where c.financeMonth = :financeMonth")
	public void deleteByMonth(@Param("financeMonth")String financeMonth);
	
	@Query("select c from FinanceMonthBalance c where c.pushStatus in :pushStatus")
	public List<FinanceMonthBalance>listBypushStatus(@Param("pushStatus")List<PushStatus> pushStatus);

}
