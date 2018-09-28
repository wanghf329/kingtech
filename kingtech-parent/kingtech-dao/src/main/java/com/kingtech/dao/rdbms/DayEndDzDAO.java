package com.kingtech.dao.rdbms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.DayEndDz;
import com.kingtech.enums.PushStatus;

public interface DayEndDzDAO extends PagingAndSortingRepository<DayEndDz, String> {
	
	@Query("select c from DayEndDz c where c.pushStatus in :pushStatus")
	public List<DayEndDz> listBypushStatus(@Param("pushStatus") List<PushStatus> pushStatus);
}
