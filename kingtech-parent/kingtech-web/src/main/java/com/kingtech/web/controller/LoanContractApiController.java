
package com.kingtech.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CardTypeEnum;
import com.kingtech.enums.DywTypeEnum;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanMethodEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.TermTypeEnum;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.enums.ZywTypeEnum;
import com.kingtech.model.ContractDywModel;
import com.kingtech.model.ContractModel;
import com.kingtech.model.ContractZywModel;
import com.kingtech.model.GuaranteeModel;
import com.kingtech.model.RepayPlanModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.szsm.model.SynResponseModel;
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
	 * 合同列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String list(Model model) {
		return "/loan/loanList";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	public String edit(Model model,@RequestParam("id") String id) {
		model.addAttribute("borrowerType", BorrowerTypeEnum.values());
		model.addAttribute("termType", TermTypeEnum.values());
		model.addAttribute("rateType", RateTypeEnum.values());
		model.addAttribute("purpose", LoanPurposeEnum.values());
		model.addAttribute("industry", IndustryEnum.values());
		model.addAttribute("LoanMethod", LoanMethodEnum.values());
		model.addAttribute("unionFlag", UnionFlagEnum.values());
		model.addAttribute("status", LoanstatusEnum.values());
		model.addAttribute("yesNoEnum", YesNoEnum.values());
		model.addAttribute("repayMethod", PayTypeEnum.values());
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("contract",contractService.getById(id));
		}
		return "/loan/loanEdit";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/supplement")
	public String supplement(Model model, @RequestParam("loanContractId") String loanContractId) { 
		model.addAttribute("loanContractId", loanContractId);
		model.addAttribute("collateralType1",DywTypeEnum.values());
		model.addAttribute("collateralType2",ZywTypeEnum.values());
		model.addAttribute("cardTypes", CardTypeEnum.values());
		
		model.addAttribute("dywList", contractService.listContractDyw(loanContractId));
		model.addAttribute("zywList", contractService.listContractZyw(loanContractId));
		
		model.addAttribute("guaranteeList", contractService.listGuaranteeByLoanContractId(loanContractId));
		model.addAttribute("repayPlanList", contractService.listRepayPlanByLoanContractId(loanContractId));
		model.addAttribute("contract",contractService.getById(loanContractId));
		return "/loan/loanSupplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addDyw")
	public String addCollateral(Model model, String loanContractId,DywTypeEnum[] pledgeType,String[] name,BigDecimal[] worth,String[] address,String[] unit) throws ParseException {
		List<ContractDywModel> dyw = Lists.newArrayList();
		for(int i=1;i<pledgeType.length;i++){
			dyw.add(new ContractDywModel(pledgeType[i],name[i],worth[i],address[i],unit[i]));
		}
		contractService.addDyw(loanContractId,dyw);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addZyw")
	public String addCollateral(Model model,String loanContractId,ZywTypeEnum[] pledgeType,String[] name,BigDecimal[] worth,String[] address,String[] unit) throws ParseException {
		List<ContractZywModel> zyw = Lists.newArrayList();
		for(int i=1;i<pledgeType.length;i++){
			zyw.add(new ContractZywModel(pledgeType[i],name[i],worth[i],address[i],unit[i]));
		}		
		contractService.addZyw(loanContractId,zyw);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addGuarantee")
	public String addGuarantee(Model model,String loanContractId,
							   String[] name,CardTypeEnum[] cardType, String[] cardNumber,String[] address) throws ParseException {
		List<GuaranteeModel> guaranteeList = Lists.newArrayList();
		for (int i = 1; i < name.length; i++) {
			guaranteeList.add(new GuaranteeModel(name[i], cardType[i],cardNumber[i], address[i]));
		}
		contractService.addGuarantee(loanContractId, guaranteeList);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addRepayPlan")
	public String addRepayPlan(Model model,String loanContractId,
							   Date[] endDate, BigDecimal[] money, BigDecimal[] interest) throws ParseException {
		List<RepayPlanModel> repayPlanList = Lists.newArrayList();
		for (int i = 1; i < endDate.length; i++) {
			repayPlanList.add(new RepayPlanModel(endDate[i], money[i],interest[i]));
		}
		contractService.addRepayPlan(loanContractId, repayPlanList);
		model.addAttribute("loanContractId", loanContractId);
		return "redirect:/loan/supplement";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addSettledInfo")
	public String addSettledInfo(Model model,String loanContractId,
								 BigDecimal money, Date loanTime, Date startDate, Date endDate)
								 throws ParseException {
		contractService.addSettledInfo(loanContractId, money, loanTime,startDate, endDate);
		return "redirect:/settled/list";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String save(Model model, ContractModel contract)
			throws ParseException {
		contractService.save(contract);
		return "redirect:/loan/list";
	}

	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/push/{id}")
	public SynResponseModel push(Model model,@PathVariable("id") String id) { 
		SynResponseModel synresponseModel = paymentApi.contractInfoApi(id,IdentifierType.A);
		return synresponseModel;
	}  
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public PagedResult<ContractModel> repayInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return contractService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

