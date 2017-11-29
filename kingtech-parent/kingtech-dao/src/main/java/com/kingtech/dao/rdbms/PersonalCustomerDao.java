package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.PersonalCustomer;

public interface PersonalCustomerDao extends PagingAndSortingRepository<PersonalCustomer, String> {
	
	@Query("select  p from PersonalCustomer p where p.loanContractId =:loanContractId")
	public PersonalCustomer getByloanContractId(@Param("loanContractId")String loanContractId);

}
