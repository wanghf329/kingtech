package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.AssetTransfer;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SysAreaCode;
import com.kingtech.enums.PushStatus;

public interface SysAreaCodeDAO extends PagingAndSortingRepository<SysAreaCode, String> {
	
	@Query("select r from SysAreaCode r where r.parent_code = '0' order by r.code_short ")
	public List<SysAreaCode> listAllProvince();
	
	@Query("select r from SysAreaCode r where r.level =:level and r.code like :code order by r.code_short ")
	public List<SysAreaCode> listAllOther(@Param("code") String code,@Param("level") String level);
}
