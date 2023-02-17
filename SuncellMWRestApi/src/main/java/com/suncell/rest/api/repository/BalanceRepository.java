package com.suncell.rest.api.repository;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;

import com.suncell.rest.api.dbHelper.OracleDbHelper;
import com.suncell.rest.api.dbHelper.VoltDbHelper;
import com.suncell.rest.api.resources.RemainingBalanceForUser;
import com.suncell.rest.api.resources.TotalBalanceForAllUsers;

public class BalanceRepository
{
	OracleDbHelper oracleDbHelper = new OracleDbHelper();
     public List<TotalBalanceForAllUsers> getBalances() throws SQLException {
     Connection connection = oracleDbHelper.getConnection();
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("select * from BALANCE");
     List<TotalBalanceForAllUsers> balances = new ArrayList<TotalBalanceForAllUsers>();
     while (resultSet.next()) {
     balances.add(new TotalBalanceForAllUsers(
     resultSet.getInt("SUBSC_ID"),
     resultSet.getInt("PACKAGE_ID"),
     resultSet.getInt("BAL_LVL_VOICE"),
     resultSet.getInt("BAL_LVL_SMS"),
     resultSet.getInt("BAL_LVL_DATA"),
     resultSet.getDate("SDATE"),
     resultSet.getDate("EDATE")));
     }
     connection.close();
     return balances;
     }
     
     public RemainingBalanceForUser getBalanceByMSISDNinList(String MSISDN) throws IOException, ProcCallException {
         RemainingBalanceForUser balanceForUser = new RemainingBalanceForUser();
         VoltDbHelper voltDbHelper = new VoltDbHelper();
         Client client = voltDbHelper.client();
         ClientResponse response;

         response = client.callProcedure("getMSISDNWithId", MSISDN);
         VoltTable tableMSISDNWitId = response.getResults()[0];
         tableMSISDNWitId.advanceRow();
         long SUBSC_ID = tableMSISDNWitId.getLong(0);

         response = client.callProcedure("getVoiceAmountBySubscId", SUBSC_ID);
         VoltTable tableVoiceAmount = response.getResults()[0];
         long voice = tableVoiceAmount.fetchRow(0).getLong(0);
         balanceForUser.setVoice(voice);

         response = client.callProcedure("getDataAmountBySubscId", SUBSC_ID);
         VoltTable tableDataAmount = response.getResults()[0];
         long data = tableDataAmount.fetchRow(0).getLong(0);
         balanceForUser.setData(data);

         response = client.callProcedure("getSmsAmountBySubscId", SUBSC_ID);
         VoltTable tableSmsAmount = response.getResults()[0];
         long SMS = tableSmsAmount.fetchRow(0).getLong(0);
         balanceForUser.setSms(SMS);

         response = client.callProcedure("getPriceBySubscId", SUBSC_ID);
         VoltTable tablePriceAmount = response.getResults()[0];
         long price = tablePriceAmount.fetchRow(0).getLong(0);
         balanceForUser.setPrice(price);
         
         RemainingBalanceForUser a= new RemainingBalanceForUser(data,SMS,voice,price);

         return a;
     }
     

}
