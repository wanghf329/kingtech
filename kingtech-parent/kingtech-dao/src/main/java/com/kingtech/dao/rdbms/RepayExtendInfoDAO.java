package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayExtendInfo;

public interface RepayExtendInfoDAO extends PagingAndSortingRepository<RepayExtendInfo, String> {
	
	@Query("select r from RepayExtendInfo r where r.loanContractId = :loanContractId order by r.repayDate ")
	public List<RepayExtendInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	
}
