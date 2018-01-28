package com.royalbattle;

import utils.Credentials;
import utils.FileReaderBean;
import utils.SettingsConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.Stateless;

import exceptions.IncorrectPasswordException;
import exceptions.UsernameOutOfBoundsException;
import exceptions.UsersLimitReachedException;

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
    
    public GameChar doLogin(Credentials credentials) throws SQLException, IncorrectPasswordException, UsersLimitReachedException {
    	
    	Connection con = getConnection();
    	Statement stmt = null;

    	GameChar character = null;
    	
        String createString =
            "CREATE TABLE IF NOT EXISTS " + SettingsConst.DATABASE +
            ".chars " +
            "(id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(" + Integer.toString(SettingsConst.MAX_NAME_LENGTH) + ") NOT NULL, " +
            "password INT NOT NULL, " +
            "health INT NOT NULL, " +
            "damage INT NOT NULL, " +
            "rating INT NOT NULL, " +
            "PRIMARY KEY (id));";

        String findUserQuery = 
        		"SELECT * FROM " + SettingsConst.DATABASE +
        		".chars" + " WHERE name = '" + credentials.getUsername() + "';";
        
        String countUsersQuery = 
        		"SELECT COUNT(*) FROM " + SettingsConst.DATABASE + ".chars" + ";";
        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            stmt.executeUpdate(createString);
            
            ResultSet rsFind = stmt.executeQuery(findUserQuery);
            
            // TODO the case when there's already two or more rows with same names is uncovered
            // thus users should not be created or modified else than through the game
            
            if(rsFind.first()) {
            	//there is already this name in the table
            	            	
            	character = new GameChar(rsFind.getInt(1), 
            			rsFind.getString(2), 
            			rsFind.getInt(3), 
            			rsFind.getInt(4), 
            			rsFind.getInt(5), 
            			rsFind.getInt(6));
            	
            	//we don't need the statement, the connection and the result set anymore
            	if (stmt != null)
            		stmt.close();
            	if(!con.isClosed())
            		con.close();
            	if(!rsFind.isClosed())
            		rsFind.close();
            	
            	if(credentials.equals(character.getCredentials())) {
            		//the password is correct
            		
            		return character;
            		
            	} else {
            		//username found in database, but the password is incorrect
            		
            		throw new IncorrectPasswordException();
            	}
            	
            } else {
            	//no such user
            	
            	//close previously opened result set
            	if(!rsFind.isClosed())
            		rsFind.close();
            	
            	ResultSet rsUserCount = stmt.executeQuery(countUsersQuery);
            	rsUserCount.first();
            	if(rsUserCount.getInt(1) >= SettingsConst.MAX_USERS_COUNT) {
            		//The maximum number of records is reached
            		
            		//we don't need the statement, the connection and the result set anymore
                	if (stmt != null)
                		stmt.close();
                	if(!con.isClosed())
                		con.close();
                	if(!rsUserCount.isClosed())
                		rsUserCount.close();
                	
            		throw new UsersLimitReachedException();
            	}
            	
            	//there's still a room for another user row
            	
            	//close previously opened result set
            	if(!rsUserCount.isClosed())
            		rsUserCount.close();
            	
            	//create a new character with specified credentials
            	character = new GameChar(credentials);
            	
            	//get the table
            	ResultSet uprs = stmt.executeQuery(
                        "SELECT * FROM " + SettingsConst.DATABASE +
                        ".chars");
            	//move cursor to 'update' row, insert values and move back to safe place
            	uprs.moveToInsertRow();
                uprs.updateInt(1, character.getId());
                uprs.updateString(2, character.getCredentials().getUsername());
                uprs.updateInt(3, character.getCredentials().getPassword());
                uprs.updateInt(4, character.getHealth());
                uprs.updateInt(5, character.getDamage());
                uprs.updateInt(6, character.getRating());
                uprs.insertRow();
                uprs.beforeFirst();
                
              //we don't need the statement, the connection and the result set anymore
            	if (stmt != null)
            		stmt.close();
            	if(!con.isClosed())
            		con.close();
            	if(!uprs.isClosed())
            		uprs.close();
            	
                return character;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UsernameOutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
        	//finally close the connection
        	if (stmt != null)
        		stmt.close();
        	if(!con.isClosed())
        		con.close();
        }
        
        return null;

    }
}
