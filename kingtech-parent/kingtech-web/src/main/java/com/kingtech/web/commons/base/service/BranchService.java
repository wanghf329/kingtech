package com.kingtech.web.commons.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kingtech.common.utils.Response;
import com.kingtech.dao.entity.Branch;



public interface BranchService {
	
	
	public Page<Branch> listByInstitutionInfo(Pageable pageable);
	
	
	public void addNewBranchInfo(String corporateName,
									String legalRepresentative,
									double regCapital,
									String buildDate,
									String openingDate,
									String siteArea,
									String businessaddr,
									String organizationCode,
									String licence,
									String nationalRegNum,
									String landRegNum,
									String businessScope);
}
