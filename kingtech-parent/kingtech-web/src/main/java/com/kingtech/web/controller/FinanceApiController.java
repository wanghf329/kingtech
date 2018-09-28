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
import com.kingtech.dao.entity.Capital;
import com.kingtech.dao.entity.FinanceMonthBalance;
import com.kingtech.dao.entity.RepaymentFinance;
import com.kingtech.enums.ChannelTypeEnum;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.RateTypeEnum;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.FinanceMonthBalanceModel;
import com.kingtech.model.FinanceRepayPlanModel;
import com.kingtech.model.RepaymentFinanceModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.szsm.model.SynResponseModel;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.CapitalService;
import com.kingtech.web.commons.base.service.FinanceMonthBalanceService;
import com.kingtech.web.commons.base.service.FinanceRepayPlanService;
import com.kingtech.web.commons.base.service.RepaymentFinanceService;

@RequestMapping("/finance")
@Controller
public class FinanceApiController {
	
	@Autowired
	private CapitalService capitalService;
	
	@Autowired
	private RepaymentFinanceService repaymentFinanceService;
	
	@Autowired
	private FinanceMonthBalanceService financeMonthBalanceService;
	
	@Autowired
	private FinanceRepayPlanService financeRepayPlanService;
	
	@Autowired
	private PaymentApi paymentApi;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/capitalList")
	public String captailList(Model model) { 
		capitalService.syncCapitalPushStatus();
		return "/finance/capitalList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/repaymentList")
	public String repaymentList(Model model) { 
		return "/finance/repaymentList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/monthBalanceList")
	public String monthBalanceList(Model model) {
		financeMonthBalanceService.syncMonthBalancePushStatus();
		return "/finance/monthBalanceList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/capital/supplement")
	public String supplement(Model model, @RequestParam("financeId") String financeId) { 
		model.addAttribute("capital", capitalService.getById(financeId));
		model.addAttribute("financeId", financeId);
		model.addAttribute("repayPlanList", financeRepayPlanService.listfinanceRepayById(financeId));
		return "/finance/capitalSupplement";
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/capitalList/data")
	public PagedResult<Capital> capitalListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return capitalService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/repaymentList/data")
	public PagedResult<RepaymentFinance> repaymentListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return repaymentFinanceService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/monthBalanceList/data")
	public PagedResult<FinanceMonthBalance> monthBalanceListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return financeMonthBalanceService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/capital/edit")
	public String editCapital(Model model,  String id) {
		
		model.addAttribute("channels", ChannelTypeEnum.values());
		model.addAttribute("rateTypes", RateTypeEnum.values());
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("capital", capitalService.getById(id));
		}
		return "/finance/capitalEdit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/repayment/edit")
	public String editRepayment(Model model,  String id) {
		model.addAttribute("financeNumbers", capitalService.listFinanceNumberByStatus(RecordStatus.NORMAL));
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("repayment", repaymentFinanceService.getById(id));
		}
		return "/finance/repaymentEdit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/monthBalance/edit")
	public String editFinanceBalance(Model model,  String id) {
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("monthBalance", financeMonthBalanceService.getById(id));
		}
		return "/finance/monthBalanceEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/capital/save")
	public String saveCapital(Model model, CapitalModel capitalModel) {
		capitalService.addNew(capitalModel);
		return "redirect:/finance/capitalList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/repayment/save")
	public String saveRepayment(Model model, RepaymentFinanceModel repayment) {
		repaymentFinanceService.addNew(repayment);
		return "redirect:/finance/repaymentList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/monthBalance/save")
	public String saveFinanceBalance(Model model, FinanceMonthBalanceModel financeBalance) {
		financeMonthBalanceService.addNew(financeBalance);
		return "redirect:/finance/monthBalanceList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/supplement/addRepayPlan")
	public String addRepayPlan(Model model,String financeId,
							   Date[] endDate, BigDecimal[] money, BigDecimal[] interest) throws ParseException {
		List<FinanceRepayPlanModel> repayPlanList = Lists.newArrayList();
		for (int i = 1; i < endDate.length; i++) {
			repayPlanList.add(new FinanceRepayPlanModel(interest[i], endDate[i], money[i], i));
		}
		capitalService.addRepayPlan(financeId, repayPlanList);
		model.addAttribute("financeId", financeId);
		return "redirect:/finance/capital/supplement";
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/capital/push/{id}")
	public SynResponseModel capitalDelete(Model model,@PathVariable("id") String id) { 
		SynResponseModel result = paymentApi.financeInfoApi(id,IdentifierType.A);
		return result;
	}  
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/monthBalance/delete/{id}")
	public SynResponseModel monthBalanceDelete(Model model,@PathVariable("id") String id) { 
		return paymentApi.financeMonthBalanceApi(id, IdentifierType.D);
	}  
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	
	

}
