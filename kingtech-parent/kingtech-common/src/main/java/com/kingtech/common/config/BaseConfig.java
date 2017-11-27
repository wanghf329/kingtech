package com.kingtech.common.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BaseConfig    {
//	
	@Value("#{constants}")
	private Properties constants;
	
	public static String REQUEST_URL;
	
	@PostConstruct
	public void init(){
		REQUEST_URL = constants.getProperty("requset.url");
	}

	

}