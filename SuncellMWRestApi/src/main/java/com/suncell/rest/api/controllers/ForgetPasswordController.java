package com.suncell.rest.api.controllers;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suncell.rest.api.repository.ForgotPasswordRepository;
import com.suncell.rest.api.resources.ForgotPassword;

@RestController
@RequestMapping("/forgotpassword")
public class ForgetPasswordController {
	 ForgotPasswordRepository forgotPasswordRepository;

	    @PostMapping("/password")
	    public ResponseEntity forgotPassword(ForgotPassword forgotPassword) throws SQLException {
	        forgotPasswordRepository = new ForgotPasswordRepository();
	        return forgotPasswordRepository.forgotPassword(forgotPassword);
	    }
}
