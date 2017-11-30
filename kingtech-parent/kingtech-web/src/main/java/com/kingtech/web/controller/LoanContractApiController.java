
package com.kingtech.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CertType;
import com.kingtech.enums.CollateralTypeEnum;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.PledgeTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.ScaleType;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.BorrowerService;
import com.kingtech.web.commons.base.service.BranchService;
import com.kingtech.web.commons.base.service.ContractService;

@RequestMapping("/loan")
@Controller
public class LoanContractApiController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private PaymentApi paymentApi;
	
	@Autowired
	private BorrowerService borrowerService;

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
	public String edit(Model model,@RequestParam("id") String id) {
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
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("contract",contractService.getById(id));
		}
		return "/loan/loanEdit";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/supplement/{loanContractId}")
	public String supplement(@PathVariable String loanContractId, Model model) { 
		model.addAttribute("loanContractId", loanContractId);
		return "/loan/loanSupplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add/supplement")
	public String addSupplement(Model model, String loanContractId,
			String pledgeType, String collateralType, String collateralName, String warrantNum, BigDecimal evaluationValue, String warrantHolder, String collateralAddr, String handleDate,
			String name, String cardNum, String phone, String address,
			String repayDate, BigDecimal principal, BigDecimal interest,
			BigDecimal money, String loanDate, String debtStartDate, String debtEndDate) throws ParseException {
		contractService.addCollateral(loanContractId, 
				PledgeTypeEnum.valueOf(pledgeType), CollateralTypeEnum.valueOf(collateralType), 
				collateralName, warrantNum, evaluationValue, warrantHolder, collateralAddr, 
				StringUtils.isEmpty(handleDate) ? null : DateUtils.parseDate(handleDate, "yyyy-MM-dd"));
		contractService.addGuarantee(loanContractId, 
				name, cardNum, phone, address);
		contractService.addRepayPlan(loanContractId, 
				DateUtils.parseDate(repayDate, "yyyy-MM-dd"), 
				principal, interest);
		contractService.addSettledInfo(loanContractId, money, 
				DateUtils.parseDate(loanDate, "yyyy-MM-dd"), 
				DateUtils.parseDate(debtStartDate, "yyyy-MM-dd"), 
				DateUtils.parseDate(debtEndDate, "yyyy-MM-dd"));
		return "redirect:/loan/list";
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
				BorrowerTypeEnum.S_0, customerId, null, loanAmount,
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

	
	
	@RequestMapping(method = RequestMethod.GET,value="/push/{id}")
	public String supplement(Model model,@PathVariable("id") String id) { 
		paymentApi.contractInfoApi(id, IdentifierType.A);
		return "redirect:/loan/list";
	}  
	
	
}

