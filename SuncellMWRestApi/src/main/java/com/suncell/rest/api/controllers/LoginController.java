package com.suncell.rest.api.controllers;

import java.sql.SQLException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suncell.rest.api.repository.LoginRepository;

@RestController("/login")
@CrossOrigin
public class LoginController {

	 LoginRepository loginRepository = new LoginRepository();

	    @GetMapping(value = "/{MSISDN}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public String loginCheck(@PathVariable String MSISDN, @PathVariable String password) throws SQLException {
	        return loginRepository.loginCheck(MSISDN, password);
	    }
	    
}
