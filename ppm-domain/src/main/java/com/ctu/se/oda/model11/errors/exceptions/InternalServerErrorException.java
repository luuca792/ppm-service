package com.ctu.se.oda.model11.errors.exceptions;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorException(String message) {
        super(message);
    }
}
