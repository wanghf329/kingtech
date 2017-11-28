package com.kingtech.web.commons.base.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingtech.common.utils.DateUtil;
import com.kingtech.common.utils.RandomUtil;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.rdbms.BranchDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.SynResponseModel;
import com.kingtech.web.commons.base.CreatRequstId;
import com.kingtech.web.commons.http.service.FinanceService;


@Service
public class PaymentApiImpl implements PaymentApi {
	
	@Autowired
	 BranchDAO branchDAO;
	
	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private CreatRequstId creatRequstId;

	@Override
	public void branchInfoApi(String branchId, IdentifierType type) {
		
	   Branch branch =	branchDAO.findOne(branchId);
	   if (branch ==null) {
		  return ;
	  }
	   
	   String roundStr =  RandomUtil.random8Len();
	   BranchInfoModel branchInfoModel =null;
	   String reqId = creatRequstId.getReqId();
	   if ("A".equals(type)||"U".equals(type)) {
		        branchInfoModel = new BranchInfoModel(
		           roundStr, 
                   type.name(), 
                   reqId, 
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
			branchInfoModel = new BranchInfoModel( roundStr, type.name(), reqId, branch.getLicence());
			
		}
		   
	   SynResponseModel responseModel= financeService.branchInfoFacade(branchInfoModel);
	   if (responseModel.isSuccess()) {
		   branch.setReqId(reqId);
		   branch.setPushStatus(PushStatus.INPROSESS);
	   }
	   
		

	}

}
