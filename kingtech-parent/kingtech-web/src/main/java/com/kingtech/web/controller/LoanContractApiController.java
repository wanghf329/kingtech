package com.kingtech.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CertType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.ScaleType;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.web.commons.base.service.BranchService;
import com.kingtech.web.commons.base.service.ContractService;

@RequestMapping("/loan")
@Controller
public class LoanContractApiController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private BranchService branchService;

	/**
	 * 使用帮助页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String list(Model model) {
		model.addAttribute("list", contractService.listAll());
		return "/loan/loanList";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	public String edit(Model model) {
		model.addAttribute("list", contractService.listAll());
		model.addAttribute("borrowerType", BorrowerTypeEnum.values());
		model.addAttribute("periodType", PeriodTypeEnum.values());
		model.addAttribute("rateType", RateTypeEnum.values());
		model.addAttribute("purpose", LoanPurposeEnum.values());
		model.addAttribute("industry", IndustryEnum.values());
		model.addAttribute("loanType", LoanTypeEnum.values());
		model.addAttribute("unionFlag", UnionFlagEnum.values());
		model.addAttribute("status", LoanstatusEnum.values());
		model.addAttribute("isExtend", YesNoEnum.values());
		model.addAttribute("payType", PayTypeEnum.values());
		model.addAttribute("branchs", branchService.listByInstitutionInfo());
		return "/loan/loanEdit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String save(Model model, String id, String loanContractId,
			String loanContractName, String borrowerType, String customerId,
			String guarantee, BigDecimal loanAmount, String periodType,
			int periodTerm, String loanStartDate, String loanEndDate,
			String rateType, BigDecimal rate, String purpose, String industry,
			String loanType, String unionFlag, String payType, String signDate,
			String repaySource, String status, String isExtend)
			throws ParseException {
		contractService.addNew(id, loanContractId, loanContractName,
				BorrowerTypeEnum.PERSION, customerId, null, loanAmount,
				PeriodTypeEnum.valueOf(periodType), periodTerm,
				DateUtils.parseDate(loanStartDate, "yyyy-MM-dd"),
				DateUtils.parseDate(loanEndDate, "yyyy-MM-dd"),
				RateTypeEnum.valueOf(rateType), rate,
				LoanPurposeEnum.valueOf(purpose),
				IndustryEnum.valueOf(industry), LoanTypeEnum.valueOf(loanType),
				UnionFlagEnum.valueOf(unionFlag), PayTypeEnum.valueOf(payType),
				DateUtils.parseDate(signDate, "yyyy-MM-dd"), repaySource,
				LoanstatusEnum.valueOf(status), YesNoEnum.valueOf(isExtend));
		return "redirect:/loan/list";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/enterprise/edit")
	public String enterpriseEdit(Model model) {
		model.addAttribute("list", contractService.listAll());
		model.addAttribute("scaleTypes", ScaleType.values());
		model.addAttribute("industryTypes", IndustryType.values());
		model.addAttribute("industryinvolveds", IndustryEnum.values());
		return "/loan/enterpriseEdit";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/personnel/edit")
	public String personnelEdit(Model model) {
		model.addAttribute("certTypes", CertType.values());
		return "/loan/personnelEdit";
	}
}
