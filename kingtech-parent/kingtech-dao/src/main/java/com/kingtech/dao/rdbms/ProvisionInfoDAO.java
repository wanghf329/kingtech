package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.ProvisionInfo;
import com.kingtech.enums.PushStatus;

public interface ProvisionInfoDAO extends PagingAndSortingRepository<ProvisionInfo, String> {
	
	@Query("select r from ProvisionInfo r where r.loanContractId = :loanContractId order by r.provisionDate ")
	public List<ProvisionInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	
	@Modifying
	@Query("update ProvisionInfo c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);

}
