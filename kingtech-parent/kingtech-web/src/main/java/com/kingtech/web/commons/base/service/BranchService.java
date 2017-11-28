package com.kingtech.web.commons.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.Branch;



public interface BranchService {
	
	
	public Page<Branch> listByInstitutionInfo(Pageable pageable);
}
