package utils;

import java.sql.*;

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
