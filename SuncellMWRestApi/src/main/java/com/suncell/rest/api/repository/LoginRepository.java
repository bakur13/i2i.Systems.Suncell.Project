package com.suncell.rest.api.repository;

import java.sql.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.suncell.rest.api.dbHelper.OracleDbHelper;
import com.suncell.rest.api.encryption.Encryption;
import com.suncell.rest.api.resources.Login;
import com.suncell.rest.api.resources.Subscriber;

public class LoginRepository {

	 Encryption encryption = new Encryption();
	    
	    
	    public String loginCheck(@PathVariable String MSISDN, @PathVariable String password) throws SQLException {
	        OracleDbHelper oracleDbHelper = new OracleDbHelper();
	        Connection connection = oracleDbHelper.getConnection();
	        //String encryptedPassword = encryption.encrypt(password);

	        CallableStatement callableStatement = connection.prepareCall("{? = call system.pack_subscriber_operation.login(?,?)}");
	        callableStatement.registerOutParameter(1, Types.INTEGER);
	        callableStatement.setString(2, MSISDN);
	        callableStatement.setString(3, password);
	        callableStatement.execute();

	        int checkUser = callableStatement.getInt(1);
      	

	        if (checkUser == 1) {
	        	   return "Basarılı";

	        } else
	            return "Basarısız";
	    }
	    
}
