package com.myProject.myPj.common.controller;


import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myProject.myPj.common.etc.MailService;
import com.myProject.myPj.common.service.CommonService;

@Controller
public class CommonController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value={"/common/sendEmail.do"})
	public String getUserList(@RequestParam Map<String, Object> paramMap,Model model) throws UnsupportedEncodingException {
		mailService.sendMail(paramMap);
		
		return "jsonView";
	}
}