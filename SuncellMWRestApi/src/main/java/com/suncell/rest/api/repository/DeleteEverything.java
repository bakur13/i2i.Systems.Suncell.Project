package com.suncell.rest.api.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.suncell.rest.api.dbHelper.OracleDbHelper;

public class DeleteEverything {

    PreparedStatement preparedStatement ;
    public void deleteVoltDb() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:voltdb://34.159.58.171:49153");
        String sql = "DELETE FROM SUBSCRIBERS";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    public void deleteOracleDb() throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "TRUNCATE TABLE SUBSCRIBER";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    public void deleteMap(){
    	/*HzOperations hazelcastOperations = new HzOperations();
    	hazelcastOperations.clearMapValues();
    	hazelcastOperations.clearMapValues();
        hazelcastCohazelcastOperationsnfiguration.clearMapValues();
        HzOperations hazelcastOperations;*/
    }
}
