package com.suncell.rest.api.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.suncell.rest.api.dbHelper.OracleDbHelper;
import com.suncell.rest.api.encryption.Encryption;
import com.suncell.rest.api.mail.MailSender;
import com.suncell.rest.api.resources.ForgotPassword;

public class ForgotPasswordRepository {
    public ResponseEntity forgotPassword(ForgotPassword forgotPassword) throws SQLException {
        MailSender mailSender = new MailSender();
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "{? = call pack_subscriber_operation.forget_password(?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);

        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, forgotPassword.getEmail());
        callableStatement.setString(3, forgotPassword.getSecurityQuestion());
        callableStatement.execute();

        String password = callableStatement.getString(1);

        if (password != null) {

            //Encryption encryption = new Encryption();

            //String password1 = encryption.Decryption(password);
            String toEmail = forgotPassword.getEmail();
            String subject = "Eyecell Password";
            String body = "Your password is " + password;

            mailSender.sendEmail(toEmail, subject, body);

            return new ResponseEntity<>("E-mail is sent", HttpStatus.OK);

        } else
            return new ResponseEntity<>("User not Found", HttpStatus.BAD_REQUEST);
    }
}
