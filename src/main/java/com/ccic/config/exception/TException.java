package com.ccic.config.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class TException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int exceptionCode = 0;
	private String errorCode="";
    private String errorMessage="";

    public TException(String errMsg) {
        super(errMsg);
    }
    public TException(Throwable cause) {
        super(cause);
    }

    public TException(String errMsg, int exceptionCode) {
        super(errMsg);
        this.exceptionCode = exceptionCode;
    }
    public int getExceptionCode() {
        return this.exceptionCode;
    }

    public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

    public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDetailMessage() {
        ByteArrayOutputStream ostr = new ByteArrayOutputStream();
        super.printStackTrace(new PrintStream(ostr));
        try {
        	ostr.flush();
        	ostr.close();
        	return ostr.toString();
        } catch (IOException ex) {
            return super.getMessage();
        }
    }
}
