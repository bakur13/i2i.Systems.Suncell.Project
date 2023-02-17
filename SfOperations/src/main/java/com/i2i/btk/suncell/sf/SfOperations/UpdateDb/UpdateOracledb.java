package com.i2i.btk.suncell.sf.SfOperations.UpdateDb;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateOracledb {
	private Logger logger = (Logger)LogManager.getLogger(UpdateOracledb.class.getName());
	@Autowired
	public UpdateOracledb() {	
	}
	public void update(String msisdn,int partKey, String dataType, int amount) {
		
		CallableStatement callableStatement=null;
		Connection conn=null;
		
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
		     conn = DriverManager.getConnection("jdbc:oracle:thin:@34.89.81.202:49161:xe", "system", "oracle");
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		if(dataType.equalsIgnoreCase("voice")) {
			
			try {
				 logger.info("------> VOICE <------");
                 callableStatement = conn.prepareCall( "{call pack_dml_operation.update_voice(?,?,?)}");

                 callableStatement.setString(1, msisdn );
                 callableStatement.setLong(2, partKey);
                 callableStatement.setLong(3,  amount);

                 callableStatement.execute();
                 System.out.println("executed");
                 logger.info("Datas sent to Oracle Database");
                 callableStatement.close();
                 conn.close();
			}catch(Exception e) {e.printStackTrace();}
			
		}else if(dataType.equalsIgnoreCase("sms")) {
			
			try {
				 logger.info("------> SMS <------");
                 callableStatement = conn.prepareCall( "{call pack_dml_operation.update_sms(?,?,?)}");
                 
                 callableStatement.setString(1, msisdn );
                 callableStatement.setLong(2, partKey);
                 callableStatement.setLong(3,  amount);

                 callableStatement.execute();
                 System.out.println("executed");
                 logger.info("Datas sent to Oracle Database");
                 callableStatement.close();
                 conn.close();
			}catch(Exception e) {e.printStackTrace();}
			
		}else if(dataType.equalsIgnoreCase("data")) {
			try {
			 logger.info("------> DATA <------");
             callableStatement = conn.prepareCall( "{call pack_dml_operation.update_data(?,?,?)}");

             callableStatement.setString(1, msisdn );
             callableStatement.setLong(2, partKey);
             callableStatement.setLong(3,  amount);

             callableStatement.execute();
             System.out.println("executed");
             logger.info("Datas sent to Oracle Database");
             callableStatement.close();
             conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
