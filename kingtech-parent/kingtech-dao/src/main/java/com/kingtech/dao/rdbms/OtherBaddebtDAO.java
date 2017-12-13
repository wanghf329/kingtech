package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.OtherBaddebt;

public interface OtherBaddebtDAO extends PagingAndSortingRepository<OtherBaddebt, String> {
	
	@Query("select r from OtherBaddebt r where r.loanContractId = :loanContractId order by r.setDate ")
	public List<OtherBaddebt> listByloanContractId(@Param("loanContractId")String loanContractId);
	
}
