package com.kingtech.web.commons.base.service;

import java.util.List;

import com.kingtech.dao.entity.Branch;
import com.kingtech.model.InstitutionInfoModel;



public interface BranchService {
	
	
	public List<Branch> listByInstitutionInfo();
	
	
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
	
	
	public InstitutionInfoModel getBranchById(String id);
}
