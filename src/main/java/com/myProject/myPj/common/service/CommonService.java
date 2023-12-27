package com.myProject.myPj.common.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.myPj.common.mapper.CommonMapper;

@Service
public class CommonService {

	@Autowired
	private CommonMapper commonMapper;
	private static final String IS_MOBILE = "MOBI";
	private static final String IS_PC = "PC"; 
	
	public String getPkId(Map<String,String> map) {
		
	    return commonMapper.getNextPK(map);
	}
	
	
	
	public String isDevice(HttpServletRequest req) {
		String userAgent = req.getHeader("User-Agent").toUpperCase();
		if(userAgent.indexOf(IS_MOBILE) > -1) {
			return IS_MOBILE;    
		} else {
			return IS_PC;    }
	}
}