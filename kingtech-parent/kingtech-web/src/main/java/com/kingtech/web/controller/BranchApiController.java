package com.kingtech.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.kingtech.dao.entity.Employee;
import com.kingtech.enums.CertType;
import com.kingtech.enums.EducationEnum;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.LoanMethodEnum;
import com.kingtech.enums.LoanPurposeEnum;
import com.kingtech.enums.LoanstatusEnum;
import com.kingtech.enums.PayTypeEnum;
import com.kingtech.enums.SexEnum;
import com.kingtech.enums.UnionFlagEnum;
import com.kingtech.enums.YesNoEnum;
import com.kingtech.model.EmployeeModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.BranchService;
import com.kingtech.web.commons.base.service.CapitalService;
import com.kingtech.web.commons.base.service.EmployeeService;
import com.kingtech.web.commons.base.service.ShareholderService;


@RequestMapping("/branch")
@Controller
public class BranchApiController {
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private CapitalService capitalService;
	
	@Autowired
	private ShareholderService shareholderService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET,value="/employeeList")
	public String employeeList(Model model) { 
		return "/branch/employeeList";
	}  
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public PagedResult<Employee> employeeListDate(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return employeeService.pageList(PageInfo.page(firstIndex, pageSize));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	public String editEmployee(Model model,  String id) {
		
		model.addAttribute("sexTypes", SexEnum.values());
		model.addAttribute("cardTypes", CertType.values());
		model.addAttribute("educations", EducationEnum.values());
		model.addAttribute("isLeaders", YesNoEnum.values());
		
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("employee", employeeService.getById(id));
		}
		return "/branch/employeeEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String saveEmployee(Model model, EmployeeModel employeeModel) {
		employeeService.addNew(employeeModel);
		return "redirect:/branch/employeeList";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	
	
}
