package com.kingtech.web.commons.base.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kingtech.dao.entity.Contract;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;

public interface ContractService {
	public List<Contract> listAll();

	public void addNew(String id,String loanContractId, String loanContractName,
						BorrowerTypeEnum borrowerType, String customerId, String guarantee,
						BigDecimal loanAmount, PeriodTypeEnum periodType, int periodTerm,
						Date loanStartDate, Date loanEndDate, RateTypeEnum rateType,
						BigDecimal rate, LoanPurposeEnum purpose, IndustryEnum industry,
						LoanTypeEnum loanType, UnionFlagEnum unionFlag, PayTypeEnum payType,
						Date signDate, String repaySource, LoanstatusEnum status, YesNoEnum isExtend);
}
