package tr.com.sedatpolat.kalah.model.exception;

import tr.com.sedatpolat.kalah.model.constant.ExceptionMessages;

/**
 * 
 * @author sedpol
 *
 */
public class InvalidOperationException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;
	private String errorCode;
	
	public InvalidOperationException(ExceptionMessages exceptionMessages) {
		this.message = exceptionMessages.getText();
		this.errorCode = exceptionMessages.getCode();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
