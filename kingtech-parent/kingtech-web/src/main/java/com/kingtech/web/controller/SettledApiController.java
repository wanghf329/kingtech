package com.kingtech.web.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.dao.entity.Contract;
import com.kingtech.dao.entity.SettledInfo;
import com.kingtech.dao.rdbms.SettledInfoDAO;
import com.kingtech.enums.IdentifierType;
import com.kingtech.enums.RecordStatus;
import com.kingtech.model.SettledInfoModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.szsm.model.SynResponseModel;
import com.kingtech.web.commons.base.api.PaymentApi;
import com.kingtech.web.commons.base.service.ContractService;

@Controller
@RequestMapping("/settled")
public class SettledApiController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private SettledInfoDAO settledInfoDAO;
	
	@Autowired
	private PaymentApi paymentApi;
	
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
		
		Map<String,SettledInfo> map = new HashedMap();
		for(SettledInfo s : settledInfoDAO.findAll()){
			if(RecordStatus.NORMAL.equals(s.getRecordStatus())){
				map.put(s.getLoanContractId(), s);
			}
		}
		
		List<Contract> all = contractService.listAll();
		List<Contract> fitler = all.stream().filter(c->{
			return map.get(c.getId()) == null;
		}).collect(Collectors.toList());
		
		
		model.addAttribute("loanContractIds", StringUtils.isEmpty(loanContractId) ? fitler : all);//新增过滤已经存在的
		return "/loan/settled/settledInfoEdit";
	}
	
	
	
	@ResponseBody
	@Transactional
	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public PagedResult<SettledInfoModel> settleInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		
		contractService.syncSettledInfoPushStatus();
		return contractService.pageListSettledInfo(PageInfo.page(firstIndex, pageSize));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/delete/{id}")
	public SynResponseModel push(Model model,@PathVariable("id") String id) { 
		SynResponseModel synresponseModel = paymentApi.settleInfoApi(id, IdentifierType.D);
		return synresponseModel;
	}  
}
