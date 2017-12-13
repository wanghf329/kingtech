package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.OtherOverdueInfo;
import com.kingtech.enums.PushStatus;

public interface OtherOverdueInfoDAO extends PagingAndSortingRepository<OtherOverdueInfo, String> {
	
	@Query("select r from OtherOverdueInfo r where r.loanContractId = :loanContractId order by r.overdueDate ")
	public List<OtherOverdueInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	@Modifying
	@Query("update OtherOverdueInfo c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);

}
