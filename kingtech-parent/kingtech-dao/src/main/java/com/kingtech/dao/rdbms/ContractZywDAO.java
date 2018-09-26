package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.ContractZyw;

public interface ContractZywDAO extends PagingAndSortingRepository<ContractZyw, String> {
	
	
	@Query("select c from ContractZyw c where c.loanContractId = :loanContractId order by c.orderNum")
	public List<ContractZyw> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("delete ContractZyw c where c.loanContractId = :loanContractId")
	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
