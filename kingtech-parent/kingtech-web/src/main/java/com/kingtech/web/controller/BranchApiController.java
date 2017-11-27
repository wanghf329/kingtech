package com.kingtech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/branch")
@Controller
public class BranchApiController {
	
	
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
	

}
