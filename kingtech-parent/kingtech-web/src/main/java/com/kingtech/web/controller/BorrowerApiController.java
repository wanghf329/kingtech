package com.kingtech.web.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.EnterpriseCustomer;
import com.kingtech.dao.entity.PersonalCustomer;
import com.kingtech.enums.BorrowerTypeEnum;
import com.kingtech.enums.CertType;
import com.kingtech.enums.IndustryEnum;
import com.kingtech.enums.IndustryType;
import com.kingtech.enums.PushStatus;
import com.kingtech.enums.ScaleType;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.BorrowerService;
import com.kingtech.web.commons.base.service.ContractService;

@RequestMapping("/borrower")
@Controller
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
	@RequestMapping(method = RequestMethod.GET,value="list/S_0")
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
		model.addAttribute("certTypes", CertType.values());
		if(StringUtils.isNotEmpty(id)){
			model.addAttribute("model",borrowerService.getPersonnel(id));
			List<Contract> list = contractService.listByUserIdAndPushstatus(id,PushStatus.INPROSESS, PushStatus.SUCCESS);
			model.addAttribute("canEdit", list.size() == 0);
		}
		return "/loan/personnelEdit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/enterprise/add")
	public String saveEnterprise(Model model,String id , String corporateName,
			String scale, String industryType, String industryinvolved,
			String organizationcode, String regCode, String regOffice,
			String regDate, String nationalregNum, String landRegNum,
			String licence, String licenceEndDate, String nature, Integer employNum,
			String legalRepresentative, String bulidDate, String actualController,BigDecimal regCapital,
			BigDecimal reallyCapital, String businessScope, String regAddress,String contactAddressProvince,
			String contactAddresscity,String contactAddressDistrict,String contactAddress,String postcode,
			String phone,String linkman,String fax,String email, String webSite)
			throws ParseException {
		borrowerService.addEnterprise( id ,corporateName, scale, industryType, 
									 industryinvolved, organizationcode, regCode, regOffice, regDate, 
									 nationalregNum, landRegNum, licence, licenceEndDate,
									 nature, employNum, legalRepresentative, bulidDate,
									 actualController, regCapital, reallyCapital, businessScope,
									 regAddress, contactAddressProvince, contactAddresscity, 
									 contactAddressDistrict, contactAddress, postcode, phone, 
									 linkman, fax, email, webSite);
		return "redirect:/borrower/corporationList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/personenl/add")
	public String savePersonenl(Model model,String id,String name, String sex, String category,
								String cardNum, String phone, String farmersFlag,
								String education, String fax, String email, String marriage,
								String nationality, String birthDate, String nation,
								String addressProvince, String addressCity, String addressDistrict,
								String address, String postCode, String residence,
								String nativePlace, String workUnit, String post) throws ParseException {
			
		borrowerService.addPersonnel(id,name, sex, category, cardNum, phone, farmersFlag,
									education, fax, email, marriage, nationality,
									birthDate, nation, addressProvince, addressCity, 
									addressDistrict, address, postCode, residence,
									nativePlace, workUnit, post);
				
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
}
