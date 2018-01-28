package exceptions;

import utils.SettingsConst;

public class UsernameOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameOutOfBoundsException() {
		super("Name length should be between 1 and " + SettingsConst.MAX_NAME_LENGTH + " symbols");
	}

	public UsernameOutOfBoundsException(String arg0) {
		super(arg0);
	}

	public UsernameOutOfBoundsException(Throwable arg0) {
		super(arg0);
	}

	public UsernameOutOfBoundsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UsernameOutOfBoundsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
