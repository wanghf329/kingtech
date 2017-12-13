package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.OtherBaddebt;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ext.ModelExt;

public interface PostLoanService {
	
	
	public List<Contract> listAllContract();
	
	public List<ModelExt> listAllRepayInfo();
	
	public RepayInfoModel getRepayInfoById(String id);
	
	
	public RepayInfo addNewRepayInfo(String id,
								  	 String repayDate,
								  	 BigDecimal repayAmount,
								  	 BigDecimal repayPrincipalAmount,
								  	 BigDecimal repayInterestAmount,
								  	 String loanContractId );
	
	
	public List<ModelExt> listAllOtherBaddebt();
	
	public OtherBaddebtModel getBaddebtInfoById(String id);
	
	public OtherBaddebt addNewBaddebtInfo(String id,
									  	  String setDate,
									  	  BigDecimal badMoney,
									  	  String followupWork,
									  	  String loanContractId );

}
