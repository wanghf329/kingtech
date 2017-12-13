package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayInfo;

public interface RepayInfoDAO extends PagingAndSortingRepository<RepayInfo, String> {
	
	@Query("select r from RepayInfo r where r.loanContractId = :loanContractId order by r.repayDate ")
	public List<RepayInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	
}
