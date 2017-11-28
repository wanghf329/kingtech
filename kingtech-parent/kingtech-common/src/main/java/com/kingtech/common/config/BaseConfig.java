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
	
	public static String REQUEST_URL;
	
	public static String APPKEY;
	
	public static String CLIENTID;
	
	public static String USERNAME;
	public static String PASSWORD;
	/**
	 * 社会信用代码
	 */
	public static String CREDITCODE;
	
	@PostConstruct
	public void init(){
		REQUEST_URL = constants.getProperty("requset.url");
		APPKEY = constants.getProperty("appKey");
		CLIENTID = constants.getProperty("clientId");
		USERNAME = constants.getProperty("username");
		PASSWORD = constants.getProperty("password");
		CREDITCODE = constants.getProperty("creditcode");
	}

	

}
