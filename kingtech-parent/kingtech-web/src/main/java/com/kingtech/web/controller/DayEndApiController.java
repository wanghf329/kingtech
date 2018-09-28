package com.kingtech.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingtech.model.DayEndDzModel;
import com.kingtech.model.misc.PageInfo;
import com.kingtech.model.misc.PagedResult;
import com.kingtech.web.commons.base.service.DayEndService;

@Controller
@RequestMapping("/dayend")
public class DayEndApiController {
	
	@Autowired
	private DayEndService dayEndService;
	
	
	/**
	 * 放款信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String list(Model model) {
		return "/dayend/dayendList";
	}
	
	
	@ResponseBody
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/list/data")
	public PagedResult<DayEndDzModel> dayendInfo(Model model,
												 @RequestParam("start") Integer firstIndex,
									 			 @RequestParam("length") Integer pageSize) {
		
		return dayEndService.pageListDayEndInfo(PageInfo.page(firstIndex, pageSize));
	}
	
	/**
	 * save
	 * @param model
	 * @param dayend
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String list(Model model,DayEndDzModel dayend) {
		dayEndService.save(dayend);
		return "redirect:/dayend/list";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }	
}
