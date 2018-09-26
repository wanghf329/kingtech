package com.test;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.Labels;
import com.kingtech.common.config.BaseConfig;
import com.kingtech.common.utils.HttpUtil;
import com.kingtech.common.utils.RandomUtil;
import com.kingtech.common.utils.SignUtils;
import com.kingtech.model.EmployeeModel;
import com.kingtech.web.commons.base.BaseAbstract;

public class Test extends BaseAbstract{
	
	
	
	
	
	public static void main(String[] args) {
		EmployeeModel employeeModel = new EmployeeModel( RandomUtil.random8Len(),
				"91310000775785552J00003",
				null,
				"唐傻", 
				"15596656333",
				null, 
				"陕西省西安市", 
				"高管",
			    1, 
				1, 
				"610324198808182967", 
				2, 
				0,
				"职员", 
				"2018-08-20",
				 null);
		System.err.println(employeeModel.getName());

		String dataSign = JSON.toJSONString(employeeModel, Labels.includes("sign")); // 验签数据
		System.err.println(dataSign);
		Map<String, Object> signMap = JSON.parseObject(dataSign, Map.class);
		System.err.println(signMap);
		String sign = SignUtils.getSignStr(signMap);
		employeeModel.setSign(sign);
		
		try {
			
		String result  =	HttpUtil.postJsonResponse(BaseConfig.REQUEST_URL+ "/pushCompanyEmployeeData" , JSON.toJSONString(employeeModel));
		System.err.println(result);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
