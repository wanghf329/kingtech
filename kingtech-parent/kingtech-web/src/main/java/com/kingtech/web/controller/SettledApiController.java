package com.kingtech.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.model.SettledInfoModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.ContractService;

@Controller
@RequestMapping("/settled")
public class SettledApiController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private SettledInfoDAO settledInfoDAO;
	
	/**
	 * 放款信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String list(Model model) {
		return "/loan/settledList";
	}
	
	/**
	 * 放款信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/single")
	public String single(Model model,@RequestParam("loanContractId") String loanContractId) {
		model.addAttribute("settledInfo", settledInfoDAO.getByloanContractId(loanContractId));
		model.addAttribute("loanContractIds", contractService.listAll());
		return "/loan/settled/settledInfoEdit";
	}
	
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public PagedResult<SettledInfoModel> settleInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		return contractService.pageListSettledInfo(PageInfo.page(firstIndex, pageSize));
	}
}
