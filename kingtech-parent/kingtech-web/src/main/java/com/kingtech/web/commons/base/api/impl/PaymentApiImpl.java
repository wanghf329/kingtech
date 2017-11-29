package com.kingtech.web.commons.base.api.impl;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.kingtech.common.utils.DateUtil;
import com.kingtech.common.utils.RandomUtil;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.Employee;
import com.kingtech.dao.entity.Shareholder;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.dao.rdbms.CapitalDAO;
import com.kingtech.dao.rdbms.EmployeeDAO;
import com.kingtech.dao.rdbms.ShareholderDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.ShareholderModel;
import com.kingtech.model.SynResponseModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.http.service.FinanceService;


@Service
@Slf4j
public class PaymentApiImpl implements PaymentApi {
	
	@Autowired
	private BranchDAO branchDAO;
	
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private CreatRequstId creatRequstId;
	
	@Autowired
	private CapitalDAO capitalDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private ShareholderDAO shareholderDAO;

	@Override
	public void branchInfoApi(String branchId, IdentifierType type) {
		
	   Branch branch =	branchDAO.findOne(branchId);
	   if (branch ==null) {
		  return ;
	  }
	   
	   String roundStr =  RandomUtil.random8Len();
	   BranchInfoModel branchInfoModel =null;
	   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
		        branchInfoModel = new BranchInfoModel(
		           roundStr, 
                   type.name(), 
                   branch.getReqId(), 
                   null, 
                   branch.getCorporateName(),
                   branch.getLegalRepresentative(),
                   branch.getRegDapital().setScale(2).toPlainString(),
                   DateUtil.getDateStr(branch.getBuildDate(), "yyyy-MM-dd"),
                   DateUtil.getDateStr(branch.getOpeningDate(), "yyyy-MM-dd"), 
                   branch.getBusinessAddr(), 
                   branch.getSiteArea(),
                   branch.getOrganizationCode(),
                   branch.getLicence(), 
                   branch.getNationalRegNum(), 
                   branch.getLandRegNum(), 
                   branch.getBusinessScope(), 
                   DateUtil.getDateStr(branch.getCreateTime(),JSON.DEFFAULT_DATE_FORMAT),
                   branch.getUpdateTime() == null? null:DateUtil.getDateStr(branch.getUpdateTime(),JSON.DEFFAULT_DATE_FORMAT));
		}else {
//			branchInfoModel = new BranchInfoModel( roundStr, type.name(), branch.getReqId(), branch.getLicence());
			log.info("机构基本信息暂时不支持删除");
			return;
			
		}
		   
	   SynResponseModel responseModel= financeService.branchInfoFacade(branchInfoModel);
	   if (responseModel.isSuccess()) {
		   branch.setPushStatus(PushStatus.INPROSESS);
		   branchDAO.save(branch);
	   }
	   
	  

	}

	@Override
	public void capitalInfoApi(String capitalId, IdentifierType type) {
		Capital capital = capitalDAO.findOne(capitalId);
		if (capital ==null) {
			  return ;
		  }
		String roundStr =  RandomUtil.random8Len();
	    CapitalModel  capitalModel =null;
		if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			capitalModel = new CapitalModel(
					roundStr,
					type.name(), 
					capital.getReqId(),
					null, 
					capital.getFinancingChannel(),
					capital.getFinancingMoney().setScale(2).toPlainString(),
					DateUtil.getDateStr(capital.getFinancingTime(), JSON.DEFFAULT_DATE_FORMAT), 
					DateUtil.getDateStr(capital.getExpirationTime(), JSON.DEFFAULT_DATE_FORMAT),
					capital.getReplyTime() == null ? null:DateUtil.getDateStr(capital.getReplyTime(), JSON.DEFFAULT_DATE_FORMAT), 
				    DateUtil.getDateStr(capital.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT),
				    capital.getUpdateTime() == null ? null:DateUtil.getDateStr(capital.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
			
			
		}else {
//			capitalModel = new CapitalModel(roundStr, type.name(), capital.getReqId());
			
			log.info("机构资本信息暂时不支持删除");
			return;
		}
		
		SynResponseModel responseModel= financeService.branchCapitalFacade(capitalModel);
		   if (responseModel.isSuccess()) {
			   capital.setPushStatus(PushStatus.INPROSESS);
			   capitalDAO.save(capital);
		   }
		   
	}

	@Override
	@Transactional
	public void employeeInfoApi(String capitalId, IdentifierType type) {
		
		
		   Employee employee = employeeDAO.findOne(capitalId);
		   if (employee ==null) {
				  return ;
			 }
		   
		   String roundStr =  RandomUtil.random8Len();
		   
		   EmployeeModel employeeModel = null;
		   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			   employeeModel = new EmployeeModel(roundStr,
					   type.name(),
					   employee.getReqId(), 
					   null, 
					   employee.getName(), 
					   employee.getPhone(),
					   employee.getEmail(),
					   employee.getPostalAddress(),
					   employee.getDepartment(),
					   employee.getSex(), 
					   employee.getIdNumber(),
					   employee.getEducation(),
					   employee.getExecutiveFlag(),
					   employee.getPost(),
					   DateUtil.getDateStr(employee.getReplyTime(), JSON.DEFFAULT_DATE_FORMAT), 
					   DateUtil.getDateStr(employee.getEntryTime(), JSON.DEFFAULT_DATE_FORMAT),
					   employee.getStatus().name(), 
					   DateUtil.getDateStr(employee.getQuitTime(), "yyyy-MM-dd"), 
					   DateUtil.getDateStr(employee.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT), 
					   DateUtil.getDateStr(employee.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
		}else {
			log.info("机构人员信息暂时不支持删除");
			return;
		}
		   
		   SynResponseModel responseModel= financeService.branchEmployeeFacade(employeeModel);
		   if (responseModel.isSuccess()) {
			   employee.setPushStatus(PushStatus.INPROSESS);
			   employeeDAO.save(employee);
		   }
	}

	@Override
	@Transactional
	public void shareholderInfoApi(String holderId, IdentifierType type) {
		Shareholder shareholder =shareholderDAO.findOne(holderId) ;
		 if (shareholder ==null) {
			  return ;
		 }
		  String roundStr =  RandomUtil.random8Len();
		   
		  ShareholderModel shareholderModel = null;
		   if (IdentifierType.A.equals(type)||IdentifierType.U.equals(type)) {
			   shareholderModel = new ShareholderModel(roundStr, 
					   type.name(), 
					   shareholder.getReqId(), 
					   null,
					   shareholder.getPartnerType(), 
					   shareholder.getHolder(),
					   shareholder.getHoldingScale().setScale(2).toPlainString(),
					   shareholder.getContributionAmount().setScale(2).toPlainString(), 
					   DateUtil.getDateStr(shareholder.getJoinTime(), "yyyy-MM-dd"),
					   shareholder.getGender(), 
					   DateUtil.getDateStr(shareholder.getQuitTime(), "yyyy-MM-dd"), 
					   DateUtil.getDateStr(shareholder.getCreateTime(), JSON.DEFFAULT_DATE_FORMAT), 
					   DateUtil.getDateStr(shareholder.getUpdateTime(), JSON.DEFFAULT_DATE_FORMAT));
		   }else {
				log.info("机构股东信息暂时不支持删除");
				return;
			}
		 
		   SynResponseModel responseModel= financeService.branchShareholderFacade(shareholderModel);
		   if (responseModel.isSuccess()) {
			   shareholder.setPushStatus(PushStatus.INPROSESS);
			   shareholderDAO.save(shareholder);
		   }
		
	}

}
