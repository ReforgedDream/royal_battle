package exceptions;

import utils.SettingsConst;

public class UsersLimitReachedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsersLimitReachedException() {
		super("Total number of users reached " + SettingsConst.MAX_USERS_COUNT + ", which is the top limit");
	}

	public UsersLimitReachedException(String arg0) {
		super(arg0);
	}

	public UsersLimitReachedException(Throwable arg0) {
		super(arg0);
	}

	public UsersLimitReachedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UsersLimitReachedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
