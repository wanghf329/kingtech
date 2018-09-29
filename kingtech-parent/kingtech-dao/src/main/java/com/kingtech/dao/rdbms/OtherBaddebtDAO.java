package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.enums.PushStatus;

public interface OtherBaddebtDAO extends PagingAndSortingRepository<OtherBaddebt, String> {
	
	@Query("select r from OtherBaddebt r where r.loanContractId = :loanContractId order by r.lossDate ")
	public List<OtherBaddebt> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("update OtherBaddebt c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Query("select c from OtherBaddebt c where c.pushStatus in :pushStatus")
	public List<OtherBaddebt> listBypushStatus(@Param("pushStatus") List<PushStatus> pushStatus);
	
	@Modifying
	@Query("delete OtherBaddebt c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);

}
