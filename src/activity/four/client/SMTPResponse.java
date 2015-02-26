package activity.four.client;

import java.util.Arrays;

public class SMTPResponse {
	private int code;
	private String message;
	private String responseType;
	
	/* Response Types */
	public static final String ERROR = "ERROR";
	public static final String INFO = "INFO";
	
	/* Error Codes */
	public static final int NOT_AVAILABLE = 421;
	public static final int MAIL_ACTION_NOT_TAKEN = 450;
	public static final int ACTION_ABORTED = 451;
	public static final int ACTION_NOT_TAKEN = 452;
	public static final int COMMAND_NOT_RECOGNIZED = 500;
	public static final int SYNTAX_ERROR = 501;
	public static final int COMMAND_NOT_IMPLEMENTED = 502;
	public static final int BAD_SEQUENCE = 503;
	public static final int NOT_IMPLEMENTED = 504;
	public static final int MAIL_BOX_UNAVAILABLE = 550;
	public static final int USER_NOT_LOCAL = 551;	
	public static final int MAIL_ACTION_ABORTED = 552;
	public static final int MAILBOX_NAME_NOT_ALLOWED = 553;
	public static final int TRANSACTION_FAILED = 554;

	public static final int[] ERROR_CODES = {
		NOT_AVAILABLE, MAIL_ACTION_NOT_TAKEN,
		ACTION_ABORTED, ACTION_NOT_TAKEN,
		COMMAND_NOT_RECOGNIZED, SYNTAX_ERROR, 
		COMMAND_NOT_IMPLEMENTED, BAD_SEQUENCE,
		NOT_IMPLEMENTED, MAIL_BOX_UNAVAILABLE,
		USER_NOT_LOCAL, MAIL_ACTION_ABORTED,
		MAILBOX_NAME_NOT_ALLOWED, TRANSACTION_FAILED
	};
	
	/* Info Codes */
	public static final int SYSTEM_STATUS = 211;
	public static final int HELP_MESSAGE = 214;
	public static final int SERVICE_READY = 220;
	public static final int SERVICE_CLOSING = 221;
	public static final int MAIL_ACTION_OK = 250;
	public static final int NOT_LOCAL = 251;
	public static final int START_MAIL_INPUT = 354;

	public static final int[] INFO_CODES = {
		SYSTEM_STATUS, HELP_MESSAGE, SERVICE_READY,
		SERVICE_CLOSING, MAIL_ACTION_OK, NOT_LOCAL,
		START_MAIL_INPUT
	};

	public SMTPResponse(String response) throws SMTPException {
		String token[] = response.split(" ");
		try {
			this.code = Integer.parseInt(token[0]);
			if (Arrays.binarySearch(ERROR_CODES, this.code) > -1) {
				this.responseType = ERROR;
			} else if (Arrays.binarySearch(INFO_CODES, this.code) > -1) {
				this.responseType = INFO;
			} else {
				throw new SMTPException("Invalid status code.");
			}
		} catch (NumberFormatException nfe) {
			throw new SMTPException("Invalid status code.");
		}
		this.message = "";
		for (int i = 1; i < token.length; i++) {
			this.message += token[i];
		}
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getResponseType() {
		return this.responseType;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public static SMTPResponse parse(String s) throws SMTPException {
		String[] tokens;
		if ((tokens = s.split(" ")).length > 1) {
			if (Arrays.binarySearch(ERROR_CODES, Integer.parseInt(tokens[0])) > -1) {
				throw new SMTPException("Server response error.");
			}
		} else {
			throw new SMTPException("Invalid Server Response...");
		}
		return new SMTPResponse(s);
	}
}
