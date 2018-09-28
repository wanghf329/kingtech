package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.enums.PushStatus;

public interface SettledInfoDAO extends PagingAndSortingRepository<SettledInfo, String> {
	
	@Query("select s from SettledInfo s where s.loanContractId = :loanContractId")
	public SettledInfo getByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Query("select r from SettledInfo r where r.loanContractId = :loanContractId")
	public SettledInfo findByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("delete SettledInfo c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
	
	@Query("select s from SettledInfo s where s.pushStatus in :pushStatus")
	public List<SettledInfo> listBypushStatus(@Param("pushStatus") List<PushStatus> pushStatus);
}
