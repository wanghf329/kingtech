package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.RepayInfo;
import com.kingtech.model.RepayInfoModel;

public interface PostLoanService {
	
	
	public List<Contract> listAllContract();
	
	public List<RepayInfo> listAllRepayInfo();
	
	public RepayInfoModel getRepayInfoById(String id);
	
	
	public RepayInfo addNewRepayInfo(String id,
								  	 String repayDate,
								  	 BigDecimal repayAmount,
								  	 BigDecimal repayPrincipalAmount,
								  	 BigDecimal repayInterestAmount,
								  	 String loanContractId );

}
