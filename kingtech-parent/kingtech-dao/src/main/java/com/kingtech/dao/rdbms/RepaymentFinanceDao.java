package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepaymentFinance;
import com.kingtech.enums.PushStatus;

public interface RepaymentFinanceDao extends PagingAndSortingRepository<RepaymentFinance, String> {
	
	@Query("select r from RepaymentFinance r where r.financeNumber = :financeNumber order by r.repayDate ")
	public List<RepaymentFinance> listByFinanceNumber(@Param("financeNumber")String financeNumber);
	
	@Modifying
	@Query("update RepaymentFinance c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);

}
