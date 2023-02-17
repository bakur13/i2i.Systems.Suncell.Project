package com.suncell.rest.api.repository;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;

import com.suncell.rest.api.dbHelper.HzOperations;
import com.suncell.rest.api.dbHelper.OracleDbHelper;
import com.suncell.rest.api.dbHelper.VoltDbHelper;
//import com.i2i.project.hzoperations.HzOperations;
import com.suncell.rest.api.encryption.Encryption;
import com.suncell.rest.api.resources.NewSubscriber;
import com.suncell.rest.api.resources.Subscriber;



public class SubscriberRepository {

	HzOperations hazelcastOperations;
	private int tempUI ;
	
	
	public SubscriberRepository(){ 
		this.hazelcastOperations =new HzOperations();
		hazelcastOperations.Connection("52.29.94.156:5701","MSISDN","dev");
		try{
			
			getUI();
		}catch(Exception e){
			e.printStackTrace();
		}
		
    }
	
	OracleDbHelper oracleDbHelper = new OracleDbHelper();
    Encryption encryption = new Encryption();
    
    
   
    //Bütün Subscriber lar getiriyoruz.
    public List<Subscriber> getSubscribers() throws SQLException {
        Connection connection = oracleDbHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from SUBSCRIBER");
        List<Subscriber> subscriberList = new ArrayList<Subscriber>();
        while (resultSet.next()) {
            subscriberList.add(new Subscriber(
                    resultSet.getInt("SUBSC_ID"),
                    resultSet.getString("MSISDN"),
                    resultSet.getString("NAME"),
                    resultSet.getString("SURNAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getDate("SDATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getString("SECURITY_QUESTION")));
        }
        connection.close();
        return subscriberList;
    }
    public List<Subscriber> getSubscriber(String MSISDN) throws IOException, ProcCallException {
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;
        List<Subscriber> subscriber = new ArrayList<>();

        response = client.callProcedure("ShowUserInformation",MSISDN);
        VoltTable subscriberTable = response.getResults()[0];
        long SUBSC_ID = subscriberTable.fetchRow(0).getLong(0);
        String _MSISDN = subscriberTable.fetchRow(0).getString(1);
        String NAME = subscriberTable.fetchRow(0).getString(2);
        String SURNAME = subscriberTable.fetchRow(0).getString(3);
        String EMAIL = subscriberTable.fetchRow(0).getString(4);
        String PASSWORD = subscriberTable.fetchRow(0).getString(5);
        Date DATE = new Date(2022,8,19);
        String STATUS = subscriberTable.fetchRow(0).getString(7);
        String SECURITY_QUESTION = subscriberTable.fetchRow(0).getString(8);

       
        subscriber.add(new Subscriber(SUBSC_ID,_MSISDN,NAME,SURNAME,EMAIL,PASSWORD,DATE,STATUS,SECURITY_QUESTION));

        return  subscriber;

    }
    //oracle veritabanına ekleme işlemi
    public void addSubscriberOracleDb(NewSubscriber newSubscriber) throws SQLException {
        OracleDbHelper oracleDbHelper = new OracleDbHelper();
        Connection connection = oracleDbHelper.getConnection();
        String sql = "{call pack_subscriber_operation.create_subscriber(?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        String encryptedPassword = encryption.encrypt(newSubscriber.getPassword());
        
        callableStatement.setInt(1,tempUI);
        callableStatement.setString(2, newSubscriber.getMSISDN());
        callableStatement.setString(3, newSubscriber.getName());
        callableStatement.setString(4, newSubscriber.getSurname());
        callableStatement.setString(5, newSubscriber.getEmail());
        callableStatement.setString(6, newSubscriber.getPassword());
        callableStatement.setString(7,newSubscriber.getSecurityQuestion());
        callableStatement.setInt(8, newSubscriber.getPackageId());
        callableStatement.execute();
        connection.close();
        
    }
    
    public void addSubscriberVoltDb(NewSubscriber newSubscriber) throws SQLException, IOException, ProcCallException {
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
       // String encryptedPassword = encryption.encrypt(newSubscriber.getPassword());
        ClientResponse response;
        client.callProcedure(
                "InsertUser",
                (getUI()),
                newSubscriber.getMSISDN(),
                newSubscriber.getName(),
                newSubscriber.getSurname(),
                newSubscriber.getEmail(),
                newSubscriber.getPassword(),
                newSubscriber.getSecurityQuestion());
        
        
        response = client.callProcedure("getPackageAmount",newSubscriber.getPackageId());
        VoltTable tablePackageInfo = response.getResults()[0];
        tablePackageInfo.advanceRow();
        long amountVoice =tablePackageInfo.getLong(0);
        long amountSms =tablePackageInfo.getLong(1);
        long amountData =tablePackageInfo.getLong(2);
        response = client.callProcedure("InsertBalance",tempUI,amountVoice,amountSms,amountData,700,newSubscriber.getPackageId());
        
        
    	hazelcastOperations.insertMsisdn(newSubscriber.getMSISDN(), tempUI,"01.01.1990");
  
    }
    //Oracle tarafından subcriber id almak.
    public int getUI() throws SQLException {
        OracleDbHelper dbHelper = new OracleDbHelper();
        Connection connection = dbHelper.getConnection();
        String sql = "{? =call pack_subscriber_operation.get_subscriber_id}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        int UI = callableStatement.getInt(1);
        System.out.println("--------------------------------" + UI);
        //System.out.println(callableStatement.wasNull());
        this.tempUI = UI;
        connection.close();
        return UI;
       
    }
}
