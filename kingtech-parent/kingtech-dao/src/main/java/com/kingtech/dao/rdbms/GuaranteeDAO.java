package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Guarantee;

public interface GuaranteeDAO extends PagingAndSortingRepository<Guarantee, String> {
	
	
	@Query("select g from Guarantee g where g.loanContractId = :loanContractId")
	public List<Guarantee> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("delete Guarantee c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
