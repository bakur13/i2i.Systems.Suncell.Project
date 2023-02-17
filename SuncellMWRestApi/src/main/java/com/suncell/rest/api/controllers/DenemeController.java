package com.suncell.rest.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="*",allowedHeaders="*")
@Slf4j
@RestController
@RequestMapping("/api/auth")

public class DenemeController {

	 Logger logger =LoggerFactory.getLogger(this.getClass());

	
	@GetMapping("/login")
	public String login(String MSISDN,String password)
	{
		if(MSISDN.equals("mustafa")&&password.equals("1234"))
		{
			logger.info("Sisteme Login Başarılı bir şekilde yapılmıştır.");
			return "İşlem Başarılı";
		}
		logger.info("Sisteme Login Başarısızdır.");

		return "İşlem Başarısız";
	}
	@PostMapping("/register")
	public String register(String MSISDN,String password)
	{
		if(MSISDN.equals("")&&password.equals(""))
		{
			return "İşlem Başarısız Lütfen Bütün bilgileri giriniz.";
		}
		return "İşlem Başarılı";
	}
	
}
