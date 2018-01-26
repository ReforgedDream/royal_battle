package utils;

public abstract class SettingsConst {
	
	//database settings
	public static final String SQL_SERVER = "localhost";
	public static final String PORT_NUMBER = "3306";
	public static final String DATABASE = "rb";
	public static final String USER_NAME = "rbapp";
	public static final String PATH_TO_PASSWORD_FILE = System.getProperty("user.dir") + "/" + "password";
	
	//gameplay settings
	public static final int DEFAULT_HEALTH = 100;
	public static final int DEFAULT_DAMAGE = 10;
}
