package com.kingtech.web.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CardTypeEnum;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.ScaleType;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.EnterpriseCustomerModel;
import com.kingtech.model.PersonalCustomerModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.BorrowerService;
import com.kingtech.web.commons.base.service.ContractService;

@Slf4j
@Controller
@RequestMapping("/borrower")
public class BorrowerApiController {
	
	@Autowired
	private BorrowerService borrowerService;
	
	@Autowired
	private ContractService contractService;
	

	@RequestMapping(method = RequestMethod.GET,value="/corporationList")
	public String corporationList(Model model) { 
		model.addAttribute("list",borrowerService.listAllEnterpries());
		return "borrower/corporationBorrowerList";
	}  
	@RequestMapping(method = RequestMethod.GET, value = "/personList")
	public String personList(Model model){
		model.addAttribute("list",borrowerService.listAllPersonal());
		return "borrower/personBorrowerList"; 
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="list/S_1")
	public List<EnterpriseCustomer> listByType1(Model model) {
			return borrowerService.listAllEnterpries();
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="list/S_2")
	public List<PersonalCustomer> listByType2(Model model) {
			return borrowerService.listAllPersonal();
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/enterprise/edit")
	public String enterpriseEdit(Model model,@RequestParam("id") String id) {
		
		model.addAttribute("scaleTypes", ScaleType.values());
		model.addAttribute("industryTypes", IndustryType.values());
		model.addAttribute("industryinvolveds", IndustryEnum.values());
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("model",borrowerService.getEnterprise(id));
			List<Contract> list = contractService.listByUserIdAndPushstatus(id,PushStatus.INPROSESS, PushStatus.SUCCESS);
			model.addAttribute("canEdit", list.size() == 0);
		}
		return "/loan/enterpriseEdit";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/personnel/edit")
	public String personnelEdit(Model model,@RequestParam("id") String id) {
		model.addAttribute("cardTypes", CardTypeEnum.values());
		model.addAttribute("educations", EducationEnum.values());
		model.addAttribute("yesNoEnum", YesNoEnum.values());
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("model",borrowerService.getPersonnel(id));
			List<Contract> list = contractService.listByUserIdAndPushstatus(id,PushStatus.INPROSESS, PushStatus.SUCCESS);
			model.addAttribute("canEdit", list.size() == 0);
		}
		return "/loan/personnelEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/enterprise/save")
	public String saveEnterprise(Model model,EnterpriseCustomerModel enterprise)
			throws ParseException {
		borrowerService.addEnterprise(enterprise);
		return "redirect:/borrower/corporationList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/personenl/save")
	public String savePersonenl(Model model,PersonalCustomerModel personal) throws ParseException {
		log.info(personal.toString());
		borrowerService.addPersonnel(personal);
		return "redirect:/borrower/personList";
	}
	

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="list/{type}")
	public List listByType(@PathVariable("type") BorrowerTypeEnum type, Model model) {
		if(BorrowerTypeEnum.S_1.equals(type)){
			return borrowerService.listAllEnterpries();
		}else{
			return borrowerService.listAllPersonal();
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getEnterprise/{id}", method = RequestMethod.GET)
	public EnterpriseCustomer getEnterprise(Model model,@PathVariable("id") String id) {
		return borrowerService.getEnterprise(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPersonal/{id}", method = RequestMethod.GET)
	public PersonalCustomer getPersonal(Model model,@PathVariable("id") String id) {
		return borrowerService.getPersonnel(id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "person/data")
	public PagedResult<PersonalCustomer> repayInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return borrowerService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
