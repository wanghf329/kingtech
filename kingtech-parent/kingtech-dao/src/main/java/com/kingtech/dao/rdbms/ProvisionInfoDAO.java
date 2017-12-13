package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.ProvisionInfo;

public interface ProvisionInfoDAO extends PagingAndSortingRepository<ProvisionInfo, String> {
	
	@Query("select r from ProvisionInfo r where r.loanContractId = :loanContractId order by r.provisionDate ")
	public List<ProvisionInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	
}
