package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.common.utils.DataTablesResponse;
import com.kingtech.dao.entity.Branch;
import com.kingtech.dao.entity.Capital;
import com.kingtech.model.CapitalModel;
import com.kingtech.model.InstitutionInfoModel;
import com.kingtech.web.commons.base.service.BranchService;
import com.kingtech.web.commons.base.service.CapitalService;


@RequestMapping("/branch")
@Controller
public class BranchApiController {
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private CapitalService capitalService;
	
	
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
    							 @RequestParam("corporateName") String corporateName,
    							 @RequestParam("legalRepresentative") String legalRepresentative,
    							 double regCapital,
						   		 @RequestParam("buildDate") String buildDate,
						   		 @RequestParam("openingDate") String openingDate,
						   		 @RequestParam("siteArea") String siteArea,
						   		 @RequestParam("businessAddr") String businessAddr,
						   		 @RequestParam("organizationCode") String organizationCode,
						   		 @RequestParam("licence") String licence,
						   		 @RequestParam("nationalRegNum") String nationalRegNum,
						   		 @RequestParam("landRegNum") String landRegNum,
						   		 @RequestParam("businessScope") String businessScope) {
    	branchService.addNewBranchInfo(corporateName, legalRepresentative, regCapital, buildDate, openingDate, siteArea, businessAddr, organizationCode, licence, nationalRegNum, landRegNum, businessScope);
    	return "/branch/branchBaseList";
    	
    }
	
    
	@RequestMapping(value = "/add/capital", method = RequestMethod.POST)
	public String addCapital(Model model, String id,String financingChannel,
							 double financingMoney, String financingTime, 
							 String expirationTime,
							 String replyTime) {
		Capital cap = capitalService.addNew(id,financingChannel, financingMoney, financingTime, expirationTime, replyTime, "BRANCHID");
		return "redirect:/branch/capitalList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCapital/{id}", method = RequestMethod.GET)
	public CapitalModel addCapital(Model model,@PathVariable("id") String id) {
		return capitalService.getById(id);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getBranchInfo/{id}", method = RequestMethod.GET)
	public InstitutionInfoModel changeBranch(Model model,@PathVariable("id") String id) {
		return capitalService.getById(id);
	}
	
}
