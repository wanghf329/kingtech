package com.kingtech.web.commons.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.Labels;
import com.kingtech.common.utils.HttpUtil;
import com.kingtech.common.utils.MD5;
import com.kingtech.model.BaseModel;
import com.kingtech.model.SynResponseModel;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class BaseAbstract {
	
	
	public static  Map<String, String> getDataAndSign(BaseModel obj){
		
		
		
		String dataStr = JSON.toJSONString( obj); //请求数据
		String dataSign =JSON.toJSONString( obj,Labels.includes("sign")); //验签数据
		Map<String, String> dataMap = JSON.parseObject(dataStr, Map.class);
		Map<String, String> signMap = JSON.parseObject(dataSign, Map.class);
		
		String sign = getCheckStr(signMap, obj.getAppKey());
		dataMap.put("sign", sign);
		
		return dataMap;
		
	}
	
	
	 public static String getCheckStr(Map<String, String> info,String key) {

	        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(info.entrySet());
	        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
	            @Override
	            public int compare(Entry<String, String> arg0, Entry<String, String> arg1) {
	                return (arg0.getKey()).compareTo(arg1.getKey());
	            }
	        });
	        StringBuilder ret = new StringBuilder();

	        for (Map.Entry<String, String> entry : infoIds) {
	        	ret .append(entry.getKey()) ;
	        	ret .append("=") ;
	        	ret .append(entry.getValue()) ;
	        	ret .append("&") ;
	        }
	      String  result = ret.substring(0, ret.length() - 1)+"app_key="+key;
	      log.info("计算验证签名的原始数据result={}",result);
	      return MD5.MD5Encode(result).toLowerCase();
	    }
	
	 public SynResponseModel getResponse(Map<String, String> request) {

			try {
				String response = HttpUtil.postFormResponse("", request);
				return JSON.parseObject(response, SynResponseModel.class);

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
	 
}
