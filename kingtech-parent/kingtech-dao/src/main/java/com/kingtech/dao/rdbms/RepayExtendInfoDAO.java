package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayExtendInfo;
import com.kingtech.enums.PushStatus;

public interface RepayExtendInfoDAO extends PagingAndSortingRepository<RepayExtendInfo, String> {
	
	@Query("select r from RepayExtendInfo r where r.loanContractId = :loanContractId ")
	public List<RepayExtendInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	
	@Modifying
	@Query("update RepayExtendInfo c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
	@Query("select r from RepayExtendInfo r where r.pushStatus in :pushStatus ")
	public List<RepayExtendInfo> listByPushStatus(@Param("pushStatus")List<PushStatus> pushStatus);
	
	
	@Modifying
	@Query("delete RepayExtendInfo c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
