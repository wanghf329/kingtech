package com.kingtech.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.enums.CertType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.ScaleType;
import com.kingtech.web.commons.base.service.BorrowerService;

@RequestMapping("/borrower")
@Controller
public class BorrowerApiController {
	
	@Autowired
	private BorrowerService borrowerService;
	

	@RequestMapping(method = RequestMethod.GET,value="/corporationList")
	public String corporationList(Model model) { 
		System.out.println("-------------");
		return "borrower/corporationBorrowerList";
	}  
	@RequestMapping(method = RequestMethod.GET, value = "/personList")
	public String personList(Model model){
		return "borrower/personBorrowerList"; 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/enterprise/edit")
	public String enterpriseEdit(Model model) {
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/enterprise/add")
	public String saveEnterprise(Model model,  String corporateName,
			String scale, String industryType, String industryinvolved,
			String organizationcode, String regCode, String regOffice,
			String regDate, String nationalregNum, String landRegNum,
			String licence, String licenceEndDate, String nature, int employNum,
			String legalRepresentative, String bulidDate, String actualController,BigDecimal regCapital,
			BigDecimal reallyCapital, String businessScope, String regAddress,String contactAddressProvince,
			String contactAddresscity,String contactAddressDistrict,String contactAddress,String postcode,
			String phone,String linkman,String fax,String email, String webSite)
			throws ParseException {
		borrowerService.addEnterprise(corporateName, scale, industryType, 
									 industryinvolved, organizationcode, regCode, regOffice, regDate, 
									 nationalregNum, landRegNum, licence, licenceEndDate,
									 nature, employNum, legalRepresentative, bulidDate,
									 actualController, regCapital, reallyCapital, businessScope,
									 regAddress, contactAddressProvince, contactAddresscity, 
									 contactAddressDistrict, contactAddress, postcode, phone, 
									 linkman, fax, email, webSite);
		return "redirect:/corporationList";
	}
	
}
