package utils;

import exceptions.PasswordIsNullException;
import exceptions.UsernameOutOfBoundsException;

public class Credentials {

	private String username;
	//actually a hash
	private int password;
	
	//constructor
	public Credentials(String username, String password) throws UsernameOutOfBoundsException, PasswordIsNullException{
		
		if(username.length() > SettingsConst.MAX_NAME_LENGTH ||
				username.length() < 1 ||
				username == null) {
			throw new UsernameOutOfBoundsException();
		}
		
		if(password == null || password.length() < 1) {
			throw new PasswordIsNullException();
		}
		
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public Credentials(String username, int passwordHash) throws UsernameOutOfBoundsException{
		
		if(username.length() > SettingsConst.MAX_NAME_LENGTH ||
				username.length() < 1 ||
				username == null) {
			throw new UsernameOutOfBoundsException();
		}
	
		this.setUsername(username);
		this.password = passwordHash;
	}

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	//returns a password hash
	public int getPassword() {return password;}

	/**
	 *Should be rewrited with at least reasonably strong hash algorithm
	 *Actually a well-known, thoroughly inspected, open-source cryptographic library should be used
	 * 
	 *In terms of REAL information security, 
	 *the current implementation as ridiculous as storing the password string itself
	 */
	public void setPassword(String password) {
	
		this.password = password.hashCode();  
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Credentials other = (Credentials) obj;
		
		if (this.username.equals(other.getUsername()) && this.password == other.getPassword()) {		
			return true;
		} else {
			return false;
		}
	}
	
}