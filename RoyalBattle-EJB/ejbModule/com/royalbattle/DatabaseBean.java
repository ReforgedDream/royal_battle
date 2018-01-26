package com.royalbattle;

import utils.FileReaderBean;
import utils.SettingsConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class DatabaseBean
 */
@Stateless
public class DatabaseBean {
	
	private final String PASSWORD = new FileReaderBean(SettingsConst.PATH_TO_PASSWORD_FILE).ReadFile();
	
	/**
     * Default constructor. 
     */
    public DatabaseBean() {
        // TODO Auto-generated constructor stub
    }
        
    public Connection getConnection() throws SQLException {

       Connection conn = null;
       
       conn = DriverManager.getConnection("jdbc:" + "mysql:" + "//" +
    		   			SettingsConst.SQL_SERVER + ":" + SettingsConst.PORT_NUMBER + "/" + SettingsConst.DATABASE + "?" +
                       "verifyServerCertificate=false&useSSL=true" + "&" +
                       "useUnicode=true" + "&" +
                       "useLegacyDatetimeCode=false&serverTimezone=UTC",
                       SettingsConst.USER_NAME, PASSWORD);

       return conn;
    }
    
    public void createTable() throws SQLException {
    	
    	Connection con = getConnection();

        String createString =
            "CREATE TABLE IF NOT EXISTS " + SettingsConst.DATABASE +
            ".chars " +
            "(id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(32) NOT NULL, " +
            "password VARCHAR(32) NOT NULL, " +
            "health INT NOT NULL, " +
            "damage INT NOT NULL, " +
            "rating INT NOT NULL, " +
            "PRIMARY KEY (id));";

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
