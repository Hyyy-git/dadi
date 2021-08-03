package com.ccic.config.exception;

import org.springframework.transaction.TransactionException;

/**
 * @description transaction runtime Exception
 */
public class TransactionRuntimeException extends TransactionException {

	private static final long serialVersionUID = 1L;
	
	public TransactionRuntimeException(String msg) {
		super(msg);
	}

	public TransactionRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
