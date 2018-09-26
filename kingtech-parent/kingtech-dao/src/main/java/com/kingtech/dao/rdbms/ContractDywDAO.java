package com.kingtech.dao.rdbms;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kingtech.dao.entity.ContractDyw;

public interface ContractDywDAO extends PagingAndSortingRepository<ContractDyw, String> {
	
	
//	@Query("select c from Collateral c where c.loanContractId = :loanContractId")
//	public List<ContractZyw> listByloanContractId(@Param("loanContractId")String loanContractId);
//	
//	@Modifying
//	@Query("delete Collateral c where c.loanContractId = :loanContractId")
//	public int deleteByLoanContractId(@Param("loanContractId") String loanContractId);
}
