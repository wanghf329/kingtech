package com.kingtech.common.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BaseConfig    {
//	
	@Value("#{constants}")
	private Properties constants;
	
	public static String REQUEST_URL ="http://183.63.254.107:8882/supervise-receive/api";
	
	public static String APPKEY="626ubwgf7s4198ik";
	
	public static String CLIENTID="6351307698887657";
	
	public static String QUERY_URL;
	
	public static String USERNAME;
	public static String PASSWORD;
	/**
	 * 社会信用代码
	 */
	public static String CREDITCODE;
	
	@PostConstruct
	public void init(){
		QUERY_URL = constants.getProperty("query.url");
		REQUEST_URL = constants.getProperty("requset.url");
		APPKEY = constants.getProperty("appKey");
		CLIENTID = constants.getProperty("clientId");
		USERNAME = constants.getProperty("username");
		PASSWORD = constants.getProperty("password");
		CREDITCODE = constants.getProperty("creditcode");
	}

	

}
