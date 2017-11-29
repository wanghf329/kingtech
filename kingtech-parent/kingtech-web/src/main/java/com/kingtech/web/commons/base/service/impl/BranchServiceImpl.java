package com.kingtech.web.commons.base.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kingtech.common.utils.DateUtil;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.BranchService;

@Service
@Slf4j
public class BranchServiceImpl implements  BranchService {
	
	@Autowired
	private BranchDAO branchDao;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private PaymentApi paymentApi;
	
	@Override
	public List<Branch> listByInstitutionInfo() {
		List branchs = (List)branchDao.findAll();
		return branchs;
	}

	@Override
	@Transactional
	public Branch addNewBranchInfo(String id,String corporateName,String legalRepresentative,
			BigDecimal regCapital, String buildDate, String openingDate,
			String siteArea, String businessaddr, String organizationCode,
			String licence, String nationalRegNum,String landRegNum, String businessScope) {
		Branch branch = null;
		if(StringUtils.isEmpty(id)) {
			branch = new Branch(creatRequstId.getReqId(),
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
			
		} else {
			branch = branchDao.findOne(id);
			branch.setCorporateName(corporateName);
			branch.setLegalRepresentative(legalRepresentative);
			branch.setRegDapital(regCapital);
			branch.setBuildDate(DateUtil.dateFormate(buildDate, "YYYY-MM-DD"));
			branch.setOpeningDate(DateUtil.dateFormate(openingDate,"YYYY-MM-DD"));
			branch.setBusinessAddr(businessaddr);
			branch.setSiteArea(siteArea);
			branch.setOrganizationCode(organizationCode);
			branch.setLicence(licence);
			branch.setNationalRegNum(nationalRegNum);
			branch.setLandRegNum(landRegNum);
			branch.setBusinessScope(businessScope);
			branch.setUpdateTime(new Date());
		}
		try {
			branch = branchDao.save(branch);
			
			paymentApi.branchInfoApi(branch.getId(), StringUtils.isEmpty(id) ? IdentifierType.A : IdentifierType.U);
			return branch;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BranchInfoModel getBranchInfoById(String id) {
		Branch branch = branchDao.findOne(id);
		if(branch == null) {
			return null;
		}
		return new BranchInfoModel(branch.getId(),
									branch.getCorporateName(),
									branch.getLegalRepresentative(),
									branch.getRegDapital().toPlainString(),
									DateFormatUtils.format(branch.getBuildDate(), "yyyy-mm-dd"),
									DateFormatUtils.format(branch.getOpeningDate(), "yyyy-mm-dd"),
									branch.getBusinessAddr(),
									branch.getSiteArea(),
									branch.getOrganizationCode(),
									branch.getLicence(),
									branch.getNationalRegNum(),
									branch.getLandRegNum(),
									branch.getBusinessScope()
									);
	}

	
}
