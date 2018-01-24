package com.royalbattle;

import utils.FileReaderBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class DatabaseBean
 */
@Stateless
@LocalBean
public class DatabaseBean {
	
	private static final String SQL_SERVER = "localhost";
	private static final String PORT_NUMBER = "3306";
	private static final String DATABASE = "rb";
	private static final String USER_NAME = "rbapp";
	private final String PASSWORD = new FileReaderBean(System.getProperty("user.dir") + "\\password").ReadFile();
	
    /**
     * Default constructor. 
     */
    public DatabaseBean() {
        // TODO Auto-generated constructor stub
    }
        
    public Connection getConnection() throws SQLException {

       Connection conn = null;
       
       conn = DriverManager.getConnection("jdbc:" + "mysql:" + "//" +
                       SQL_SERVER + ":" + PORT_NUMBER + "/" + DATABASE + "?" +
                       "verifyServerCertificate=false&useSSL=true" + "&" +
                       "useUnicode=true" + "&" +
                       "useLegacyDatetimeCode=false&serverTimezone=UTC",
                       USER_NAME, PASSWORD);

       return conn;
    }
}
