package com.kingtech.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kingtech.common.config.BaseConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignUtils {
	/**
	 * 排序组装验签数据
	 * @param info
	 * @param key
	 * @return
	 */
	
	 public static String getSignStr(Map<String, String> info) {

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
	      String  result = ret.substring(0, ret.length() - 1)+"&app_key="+BaseConfig.APPKEY;
	      log.info("计算验证签名的原始数据result={}",result);
	      return MD5.MD5Encode(result).toLowerCase();
	    }
	 
	 public static  String getToken(String roundStr ){
		 
		 Map<String, String> map = new HashMap<String, String>();
		 
		 map.put("round_str", roundStr);
		 map.put("client_id",BaseConfig.CLIENTID);
		 return getSignStr(map);
	 }
	 

}
