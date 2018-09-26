package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.ContractDyw;
import com.kingtech.dao.entity.ContractZyw;
import com.kingtech.dao.entity.Guarantee;
import com.kingtech.dao.entity.RepayPlan;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.enums.PushStatus;
import com.kingtech.model.ContractDywModel;
import com.kingtech.model.ContractModel;
import com.kingtech.model.ContractZywModel;
import com.kingtech.model.misc.PagedResult;

public interface ContractService {
	public List<Contract> listAll();
	
	public List<Contract> listByUserIdAndPushstatus(String userId,PushStatus... pushstatus);

	public void save(ContractModel model);
	
	public Contract getById(String id);
	
	public void addDyw(String loanContractId,List<ContractDywModel> dyw);
	
	public void addZyw(String loanContractId,List<ContractZywModel> zyw);
	
	public void addGuarantee(String loanContractId, String[] name, String[] cardNum, String[] phone, String[] address);
	
	public void addRepayPlan(String loanContractId, String[] repayDate, BigDecimal[] principal, BigDecimal[] interest);
	
	public void addSettledInfo(String loanContractId, BigDecimal[] money, String[] loanDate, String[] debtStartDate, String[] debtEndDate);
	
	public List<ContractZyw> listContractZyw(String loanContractId);
	public List<ContractDyw> listContractDyw(String loanContractId);
	
	public List<Guarantee> listGuaranteeByLoanContractId(String loanContractId);
	
	public List<RepayPlan> listRepayPlanByLoanContractId(String loanContractId);
	
	public List<SettledInfo> listSettledInfoByLoanContractId(String loanContractId);
	
	public PagedResult<ContractModel> pageList(Pageable pageAble);
	
}
