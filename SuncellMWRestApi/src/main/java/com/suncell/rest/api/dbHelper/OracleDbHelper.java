package com.suncell.rest.api.dbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class OracleDbHelper {

	private final String username = "system";
    private final String password = "oracle";
    private final String dBurl = "jdbc:oracle:thin:@34.89.81.202:49161";

    public OracleDbHelper() {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Londra");
        TimeZone.setDefault(timeZone);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dBurl, username, password);
    }
}

