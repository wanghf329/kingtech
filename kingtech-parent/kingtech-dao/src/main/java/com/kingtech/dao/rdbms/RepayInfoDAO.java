package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.enums.PushStatus;

public interface RepayInfoDAO extends PagingAndSortingRepository<RepayInfo, String> {
	
	@Query("select r from RepayInfo r where r.loanContractId = :loanContractId order by r.repayTime ")
	public List<RepayInfo> listByloanContractId(@Param("loanContractId")String loanContractId);
	
	@Modifying
	@Query("update RepayInfo c set c.pushStatus = :pushStatus  where c.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);

	@Query("select r from RepayInfo r where r.pushStatus in :pushStatus")
	public List<RepayInfo> listByPushStatus(@Param("pushStatus") List<PushStatus> pushStatus);
}
