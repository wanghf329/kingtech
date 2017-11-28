package com.kingtech.web.commons.base.service.impl;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.common.utils.Response;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.enums.PushStatus;
import com.kingtech.web.commons.base.service.BranchService;

@Service
@Slf4j
public class BranchServiceImpl implements  BranchService {
	
	@Autowired
	private BranchDAO branchDao;
	
	@Override
	public Page<Branch> listByInstitutionInfo(Pageable pageable) {
		Page<Branch> branchs = branchDao.findAll(pageable);
		return branchs;
	}

	@Override
	@Transactional
	public void addNewBranchInfo(String corporateName,String legalRepresentative,
			double regCapital, String buildDate, String openingDate,
			String siteArea, String businessaddr, String organizationCode,
			String licence, String nationalRegNum,String landRegNum, String businessScope) {
		Branch branch = new Branch( "111111",
									PushStatus.INPROSESS,
									corporateName, 
									legalRepresentative,
									regCapital, 
									DateUtil.dateFormate(buildDate, "YYYY-MM-DD"), 
									DateUtil.dateFormate(openingDate,"YYYY-MM-DD"), 
									businessaddr,
									siteArea, 
									organizationCode,
									licence, 
									nationalRegNum, 
									landRegNum, 
									businessScope);
		try {
			branch = branchDao.save(branch);
		}catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
