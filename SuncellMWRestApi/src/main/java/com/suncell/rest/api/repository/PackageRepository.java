package com.suncell.rest.api.repository;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.suncell.rest.api.resources.Package;
import com.suncell.rest.api.resources.PackageList;
import com.suncell.rest.api.dbHelper.OracleDbHelper;


public class PackageRepository {

	  //oracle package listesini getiriyoruz.
	  public List<Package> packageList() throws SQLException {
	        OracleDbHelper oracleDbHelper = new OracleDbHelper();
	        Connection connection = oracleDbHelper.getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("select * from PACKAGE");
	        List<Package> packageList = new ArrayList<Package>();
	        while (resultSet.next()) {
	            packageList.add(new Package(resultSet.getInt("PACKAGE_ID"),
	                    resultSet.getString("PACKAGE_NAME"),
	                    resultSet.getInt("AMOUNT_VOICE"),
	                    resultSet.getInt("AMOUNT_DATA"),
	                    resultSet.getInt("AMOUNT_SMS"),
	                    resultSet.getInt("DURATION")));
	        }
	        connection.close();
	        return packageList;
       }
	  //oracle package listesinden ıd ve name getiriyoruz.
	  public List<PackageList> packageLists() throws SQLException {

	        OracleDbHelper oracleDbHelper = new OracleDbHelper();
	        Connection connection = oracleDbHelper.getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("select * from PACKAGE");
	        List<PackageList> packageLists = new ArrayList<PackageList>();
	        while (resultSet.next()) {
	            packageLists.add(new PackageList(resultSet.getLong("PACKAGE_ID"),
	            resultSet.getString("PACKAGE_NAME")));
	        }
	        connection.close();
	        return packageLists;

	    }
	  
	  
}
