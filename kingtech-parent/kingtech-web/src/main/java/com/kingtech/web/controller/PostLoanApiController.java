package com.kingtech.web.controller;

import java.math.BigDecimal;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.enums.LoanClassificationEnum;
import com.kingtech.enums.RepayStatusEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.OtherBaddebtModel;
import com.kingtech.model.OtherOverdueInfoModel;
import com.kingtech.model.ProvisionInfoModel;
import com.kingtech.model.RepayExtendInfoModel;
import com.kingtech.model.RepayExtendPlanModel;
import com.kingtech.model.RepayInfoModel;
import com.kingtech.model.ext.ModelExt;
import com.kingtech.model.ext.RepayExtendInfoModelExt;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.ContractService;
import com.kingtech.web.commons.base.service.ExtendRepayPlanService;
import com.kingtech.web.commons.base.service.ExtendRepayService;
import com.kingtech.web.commons.base.service.PostLoanService;
import com.kingtech.web.commons.base.service.ProvisionService;
import com.kingtech.web.commons.base.service.RepayInfoService;

@Controller
@RequestMapping("/postLoan")
public class PostLoanApiController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ExtendRepayPlanService repayExtendPlanService;

	@Autowired
	private ExtendRepayService extendRepayService;
	
	@Autowired
	private PostLoanService postLoanService;
	
	@Autowired
	private ProvisionService provisionService;
	
	@Autowired
	private RepayInfoService repayInfoService;
	
	/**
	 * 还款信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "repayinfo")
	public String repayInfo(Model model) {
		model.addAttribute("contracts", postLoanService.listAllContract());
		return "/postloan/repayInfo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getRepayInfo/{id}")
	@ResponseBody
	public RepayInfoModel getRepayInfo(Model model,@PathVariable String id) {
		return postLoanService.getRepayInfoById(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "add/repayInfo")
	public String addNewRepayInfo(Model model,String id,
								  String repayDate,
								  BigDecimal repayAmount,
								  BigDecimal repayPrincipalAmount,
								  BigDecimal repayInterestAmount,
								  String loanContractId){
		postLoanService.addNewRepayInfo(id, repayDate, repayAmount, repayPrincipalAmount, repayInterestAmount, loanContractId);
		return "redirect:/postLoan/repayinfo";
	}

	/**
	 * 展期还款计划信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "extensionrepayplaninfo")
	public String extensionRepayPlanInfo(Model model) {
		model.addAttribute("contracts", contractService.listAll());
		model.addAttribute("repayStatus", RepayStatusEnum.values());
		model.addAttribute("overdueFlags", YesNoEnum.values());
		return "/postloan/extensionRepayPlanInfo";
	}
	
	/**
	 * 展期还款计划信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get/extensionrepayplaninfo/{id}", method = RequestMethod.GET)
	public RepayExtendPlanModel getRepayExtendPlan(Model model,@PathVariable("id") String id) {
		return repayExtendPlanService.getById(id);
	}
	
	/**
	 * 展期还款计划信息save
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add/extensionrepayplaninfo", method = RequestMethod.POST)
	public String saveExtensionRepayPlanInfo(Model model,
											String id,
											String loanContractId, 
											String count,
											String endDate,
											String principal, 
											String interest) {
		repayExtendPlanService.addNew(id, loanContractId, count, endDate, principal, interest);
		
		return "redirect:/postLoan/extensionrepayplaninfo";
	}

	/**
	 * 展期还款信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "extensionrepayinfo")
	public String extensionRepayInfo(Model model) {
		model.addAttribute("contracts", contractService.listAll());
		return "/postloan/extensionRepayInfo";
	}
	
	
	/**
	 * 展期还款信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "extensionrepayinfo/data")
	public PagedResult<RepayExtendInfoModelExt> extensionRepayInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return extendRepayService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	
	/**
	 * 还款信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "repayInfo/data")
	public PagedResult<ModelExt> repayInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return repayInfoService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	
	/**
	 * 展期还款计划
	 * @param model
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "extendRepayPlan/data")
	public PagedResult<ModelExt> extendRepayPlan(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return repayExtendPlanService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	

	
	/**
	 * 坏账信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "baddebtsinfo")
	public String badDebtsInfo(Model model) {
		model.addAttribute("contracts", postLoanService.listAllContract());
		model.addAttribute("list", postLoanService.listAllOtherBaddebt());
		return "/postloan/badDebtsInfo";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "getBaddebtsInfo/{id}")
	@ResponseBody
	public OtherBaddebtModel getBaddebtsInfo(Model model,@PathVariable String id) {
		return postLoanService.getBaddebtInfoById(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "add/baddebtsInfo")
	public String addNewBaddebtsInfo(Model model,String id,
								    String setDate,
								    BigDecimal badMoney,
								    String  followupWork,
								    String loanContractId){
		postLoanService.addNewBaddebtInfo(id,setDate,badMoney,followupWork, loanContractId);
		return "redirect:/postLoan/baddebtsinfo";
	}

	

	/**
	 * 逾期信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "overdueinfo")
	public String overdueInfo(Model model) {
		model.addAttribute("contracts", contractService.listAll());
		model.addAttribute("list", postLoanService.listAllOverdue());
		return "/postloan/overdueInfo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getOverdueInfo/{id}")
	@ResponseBody
	public OtherOverdueInfoModel getOverdueInfo(Model model,@PathVariable String id) {
		return postLoanService.getOverDueInfoById(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "add/overdueInfo")
	public String addNewOverdueInfo(Model model,String id,
								    String overdueDate,
								    BigDecimal overdueMoney,
								    BigDecimal overdueInterest,
								    BigDecimal balance,
								    String  remarks,
								    String loanContractId){
		postLoanService.addNewOverDueInfo(id, overdueDate, overdueMoney, overdueInterest, balance, remarks, loanContractId);
		return "redirect:/postLoan/overdueinfo";
	}

	/**
	 * 计提信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "provisioninfo")
	public String accruedInfo(Model model) {
		model.addAttribute("loanClassificationEnum", LoanClassificationEnum.values());
		model.addAttribute("list", provisionService.listAll());
		return "/postloan/provisionInfo";
	}
	/**
	 * 编辑还款计划
	 * @param model
	 * @param id
	 * @param loanContractId
	 * @param extendNum
	 * @param repayDate
	 * @param repayAmount
	 * @param repayPrincipalAmount
	 * @param repayInterestAmount
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "extendrepay/edit")
	public String extendrepayEdit(Model model,
								String id,
								String loanContractId,
								String repayTime,
								BigDecimal money, 
								BigDecimal interest,
								BigDecimal penaltyInterest,
								BigDecimal penalty,
								BigDecimal serviceCharge,
								BigDecimal otherCharge) {
		try {
			extendRepayService.addOrEdit(id, loanContractId, DateUtils.parseDate(repayTime, "yyyy-MM-dd"), money, interest, penaltyInterest, penalty, serviceCharge, otherCharge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/postLoan/extensionrepayinfo";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "provision/edit")
	public String provisionEdit(Model model, String id,
								BigDecimal provisionMoney, String provisionDate,
								BigDecimal provisionScale,
								String loanClassification, BigDecimal balance) {
		try {
			provisionService.addOrEdit(id, provisionMoney, DateUtils.parseDate(provisionDate, "yyyy-MM-dd"),
					                   provisionScale, 
					                   LoanClassificationEnum.valueOf(loanClassification), 
					                   balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/postLoan/provisioninfo";
	}
	
	/**
	 * 展期还款信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "extendrepay/detail/{id}")
	public RepayExtendInfoModel extendrepayDetail(Model model,@PathVariable("id") String id) {
		RepayExtendInfoModel rf = extendRepayService.getById(id);
		return rf;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "provision/detail/{id}")
	public ProvisionInfoModel provisionDetail(Model model,@PathVariable("id") String id) {
		ProvisionInfoModel pi = provisionService.getById(id);
		return pi;
	}
}
