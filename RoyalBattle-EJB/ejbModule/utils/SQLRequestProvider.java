package utils;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SQLRequestProvider {
	
	public void doRequest(Connection conn, String request) {
		
		try {
			conn.beginRequest();
			

			
			conn.endRequest();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
