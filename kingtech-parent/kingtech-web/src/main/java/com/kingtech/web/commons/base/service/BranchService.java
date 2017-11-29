package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import com.kingtech.dao.entity.Branch;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.web.model.BranchModel;



public interface BranchService {
	
	
	public List<Branch> listByInstitutionInfo();
	
	
	public Branch addNewBranchInfo( String id,
									String corporateName,
									String legalRepresentative,
									BigDecimal regCapital,
									String buildDate,
									String openingDate,
									String siteArea,
									String businessaddr,
									String organizationCode,
									String licence,
									String nationalRegNum,
									String landRegNum,
									String businessScope);
	
	
	public BranchInfoModel getBranchInfoById(String id);
}
