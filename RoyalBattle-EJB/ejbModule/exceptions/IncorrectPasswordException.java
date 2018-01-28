package exceptions;

public class IncorrectPasswordException extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException() {
		super("Password incorrect");
	}

	public IncorrectPasswordException(String arg0) {
		super(arg0);
	}

	public IncorrectPasswordException(Throwable arg0) {
		super(arg0);
	}

	public IncorrectPasswordException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IncorrectPasswordException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
