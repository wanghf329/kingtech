package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.EnterpriseCustomer;

public interface EnterpriseCustomerDAO extends PagingAndSortingRepository<EnterpriseCustomer, String> {
	

	@Query("select e from EnterpriseCustomer e where e.loanContractId =:loanContractId")
	public EnterpriseCustomer getByloanContractId(@Param("loanContractId")String loanContractId);
}
