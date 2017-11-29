package com.kingtech.web.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.entity.Capital;
import com.kingtech.model.BranchInfoModel;
import com.kingtech.model.CapitalModel;
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
	
	
	@RequestMapping(method = RequestMethod.GET,value="")
	public String branchBaseInfo(Model model) { 
		model.addAttribute("list",branchService.listByInstitutionInfo());
		return "/branch/branchBaseList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/personalList")
	public String personalList(Model model) { 
		return "/branch/personalList";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/shareholderList")
	public String shareholderList(Model model) { 
		model.addAttribute("list",capitalService.listAll());
		return "/branch/shareholderList";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/capitalList")
	public String capitalList(Model model) { 
		model.addAttribute("list",capitalService.listAll());
		return "/branch/capitalList";
	}  
	
    @RequestMapping(value = "/add/branch", method=RequestMethod.POST )
    public String addBranchInfo(Model model,
    							 String id,
    							 @RequestParam("corporateName") String corporateName,
    							 @RequestParam("legalRepresentative") String legalRepresentative,
    							 BigDecimal regCapital,
						   		 @RequestParam("buildDate") String buildDate,
						   		 @RequestParam("openingDate") String openingDate,
						   		 @RequestParam("siteArea") String siteArea,
						   		 @RequestParam("businessAddr") String businessAddr,
						   		 @RequestParam("organizationCode") String organizationCode,
						   		 @RequestParam("licence") String licence,
						   		 @RequestParam("nationalRegNum") String nationalRegNum,
						   		 @RequestParam("landRegNum") String landRegNum,
						   		 @RequestParam("businessScope") String businessScope) {
    	Branch branch = branchService.addNewBranchInfo(id,corporateName, legalRepresentative, regCapital, buildDate, openingDate, siteArea, businessAddr, organizationCode, licence, nationalRegNum, landRegNum, businessScope);
    	return "redirect:/branch";
    	
    }
	
    
	@RequestMapping(value = "/add/capital", method = RequestMethod.POST)
	public String addCapital(Model model, String id,String financingChannel,
							 double financingMoney, String financingTime, 
							 String expirationTime,
							 String replyTime) {
		Capital cap = capitalService.addNew(id,financingChannel, financingMoney, financingTime, expirationTime, replyTime, "BRANCHID");
		return "redirect:/branch/capitalList";
	}
	
	@RequestMapping(value = "/add/shareholder", method = RequestMethod.POST)
	public String addShareholder(Model model, String partnerType,
								String holder, String holdingScale, String contributionAmount,
								String joinTime, String gender, String quitTime, String branchId) {
		shareholderService.addNew(partnerType, holder, holdingScale, contributionAmount,
								  joinTime, gender, quitTime, "BRANCHID");
		return "redirect:/branch/shareholderList";
	}
	
	@RequestMapping(value = "/add/employee", method = RequestMethod.POST)
	public String addEmployee(Model model, String name, String loginName, String phone, String email, String postalAddress,
							  String department, String sex, String idNumber, String education,
							  String executiveFlag, String post, String replyTime, String entryTime,
							  String status, String quitTime, String branchId) {
		employeeService.addNew(name, loginName, phone, email, postalAddress,
							   department, sex, idNumber, education,
							   executiveFlag, post, replyTime, entryTime,
							   status, quitTime, "BRANCHID");
		return "redirect:/branch/personalList";
	}

	@ResponseBody
	@RequestMapping(value = "/getCapital/{id}", method = RequestMethod.GET)
	public CapitalModel addCapital(Model model,@PathVariable("id") String id) {
		return capitalService.getById(id);
	}
	

	
	@ResponseBody
	@RequestMapping(value = "/getBranchInfo/{id}", method = RequestMethod.GET)
	public BranchInfoModel changeBranch(Model model,@PathVariable("id") String id) {
		return branchService.getBranchInfoById(id);
	}

	@RequestMapping(value = "/delCapital/{id}", method = RequestMethod.GET)
	public String delCapital(Model model,@PathVariable("id") String id) {
		capitalService.delById(id);
		return "redirect:/branch/capitalList";
	}

}
