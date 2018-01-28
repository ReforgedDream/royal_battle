package exceptions;

public class PasswordIsNullException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordIsNullException() {
		super("Password should not be null");
	}

	public PasswordIsNullException(String arg0) {
		super(arg0);
	}

	public PasswordIsNullException(Throwable arg0) {
		super(arg0);
	}

	public PasswordIsNullException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PasswordIsNullException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
