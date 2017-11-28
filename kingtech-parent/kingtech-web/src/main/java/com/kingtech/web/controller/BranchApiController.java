package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.common.utils.DataTablesResponse;
import com.kingtech.common.utils.Response;
import com.kingtech.dao.entity.Branch;
import com.kingtech.web.commons.base.service.BranchService;
import com.kingtech.web.commons.base.service.CapitalService;
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
	
	
	@RequestMapping(method = RequestMethod.GET,value="")
	public String branchBaseInfo(Model model) { 
		return "/branch/branchBaseList";
	} 
	
	@RequestMapping(method = RequestMethod.GET,value="/personalList")
	public String personalList(Model model) { 
		return "/branch/personalList";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/shareholderList")
	public String shareholderList(Model model) { 
		return "/branch/shareholderList";
	}  
	
	@RequestMapping(method = RequestMethod.GET,value="/capitalList")
	public String capitalList(Model model) { 
		return "/branch/capitalList";
	}  
	
	
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesResponse findAssetList(Integer draw, 
									    	@RequestParam("start") Integer firstIndex,
									        @RequestParam("length") Integer pageSize) {
		
		Pageable pageable;
		if(pageSize == -1){ //不分页
			pageable = null;
		}else{
			pageable = new PageRequest(firstIndex/pageSize,pageSize);
		}
		Page<Branch> page = branchService.listByInstitutionInfo(pageable);
		return DataTablesResponse.format(draw, page);
    }
    
    @RequestMapping(value = "/add/branch", method=RequestMethod.POST )
    @ResponseBody
    public Response addBranchInfo(@RequestParam("corporateName") String corporateName,
    							 @RequestParam("legalRepresentative") String legalRepresentative,
						   		 @RequestParam("regCapital") String regCapital,
						   		 @RequestParam("buildDate") String buildDate,
						   		 @RequestParam("openingDate") String openingDate,
						   		 @RequestParam("siteArea") String siteArea,
						   		 @RequestParam("businessAddr") String businessAddr,
						   		 @RequestParam("organizationCode") String organizationCode,
						   		 @RequestParam("licence") String licence,
						   		 @RequestParam("nationalRegNum") String nationalRegNum,
						   		 @RequestParam("landRegNum") String landRegNum,
						   		 @RequestParam("businessScope") String businessScope) {
    	return Response.success();
    	
    }
	
    
	@RequestMapping(value = "/add/capital", method = RequestMethod.POST)
	public String addCapital(Model model, String financingChannel,
							 double financingMoney, String financingTime, 
							 String expirationTime,
							 String replyTime) {
		capitalService.addNew(financingChannel, financingMoney, financingTime, expirationTime, replyTime, "BRANCHID");
		return "/branch/branchBaseList";
	}
	
	@RequestMapping(value = "/add/shareholder", method = RequestMethod.POST)
	public String addShareholder(Model model, String partnerType,
								String holder, String holdingScale, String contributionAmount,
								String joinTime, String gender, String quitTime, String branchId) {
		
		System.out.println("holdingScale:  " + holdingScale);
		shareholderService.addNew(partnerType, holder, holdingScale, contributionAmount,
								joinTime, gender, quitTime, "BRANCHID");
		return "/branch/shareholderList";
	}
}
