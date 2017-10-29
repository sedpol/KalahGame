package tr.com.sedatpolat.kalah.model.constant;

/**
 * 
 * @author sedpol
 *
 */
public enum ExceptionMessages {
	
	START_WITH_ZERO_STONED_PIT	("W001", "You cannot start with zero stoned pit!"),
	CANNOT_START_WITH_KALAH		("W002", "You cannot start with kalah!"),
	
	INVALID_START_PIT_INDEX		("E001", "Invalid pit index!");
	
	private String code; 
	private String text;

	ExceptionMessages(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
