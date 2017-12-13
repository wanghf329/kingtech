
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
import com.kingtech.enums.CollateralTypeFor1Enum;
import com.kingtech.enums.CollateralTypeFor2Enum;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanTypeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.PeriodTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.SynResponseModel;
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
	
	@RequestMapping(method = RequestMethod.GET,value="/supplement")
	public String supplement(Model model, @RequestParam("loanContractId") String loanContractId) { 
		model.addAttribute("loanContractId", loanContractId);
		model.addAttribute("collateralType1",CollateralTypeFor1Enum.values());
		model.addAttribute("collateralType2",CollateralTypeFor2Enum.values());
		model.addAttribute("collateralList", contractService.listCollateralByLoanContractId(loanContractId));
		model.addAttribute("guaranteeList", contractService.listGuaranteeByLoanContractId(loanContractId));
		model.addAttribute("repayPlanList", contractService.listRepayPlanByLoanContractId(loanContractId));
		model.addAttribute("settledInfoList", contractService.listSettledInfoByLoanContractId(loanContractId));
		model.addAttribute("contract",contractService.getById(loanContractId));
		return "/loan/loanSupplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addCollateral")
	public String addCollateral(Model model, String[] id, String loanContractId,
			String[] pledgeType, String[] collateralType, String[] collateralName, String[] warrantNum, 
			BigDecimal[] evaluationValue, String[] warrantHolder, String[] collateralAddr, String[] handleDate) throws ParseException {
		contractService.addCollateral(id, loanContractId, 
				pledgeType, collateralType, 
				collateralName, warrantNum, evaluationValue, warrantHolder, collateralAddr, 
				handleDate);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addGuarantee")
	public String addGuarantee(Model model,String loanContractId,
			String[] name, String[] cardNum, String[] phone, String[] address) throws ParseException {
		contractService.addGuarantee(loanContractId, 
				name, cardNum, phone, address);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addRepayPlan")
	public String addRepayPlan(Model model, String id, String loanContractId,
			String[] repayDate, BigDecimal[] principal, BigDecimal[] interest) throws ParseException {
		contractService.addRepayPlan(loanContractId, repayDate, principal, interest);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addSettledInfo")
	public String addSettledInfo(Model model, String id, String loanContractId,
			BigDecimal[] money, String[] loanDate, String[] debtStartDate, String[] debtEndDate) throws ParseException {
		contractService.addSettledInfo(loanContractId, money, loanDate, debtStartDate, debtEndDate);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String save(Model model, String id, String loanContractId,
			String loanContractName, String borrowerType,String borrowerId, String customerId,
			String guarantee, BigDecimal loanAmount, String periodType,
			int periodTerm, String loanStartDate, String loanEndDate,
			String rateType, BigDecimal rate, String purpose, String industry,
			String loanType, String unionFlag, String payType, String signDate,
			String repaySource, String status, String isExtend)
			throws ParseException {
		contractService.addNew(id, loanContractId, loanContractName,
				BorrowerTypeEnum.valueOf(borrowerType), borrowerId,customerId, null, loanAmount,
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

	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/push/{id}")
	public SynResponseModel push(Model model,@PathVariable("id") String id) { 
		SynResponseModel synresponseModel = paymentApi.contractInfoApi(id, IdentifierType.A);
		return synresponseModel;
	}  
	
	
}

