package com.kingtech.dao.rdbms;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Employee;
import com.kingtech.enums.PushStatus;

public interface EmployeeDAO extends PagingAndSortingRepository<Employee, String> {
	
	
	@Modifying
	@Query("update Employee e set e.pushStatus = :pushStatus  where e.reqId = :reqId")
	public int updateStatusByReqId(@Param("reqId")String reqId,@Param("pushStatus")PushStatus pushStatus);
	
}
