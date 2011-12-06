package com.brettchild.springmvc.exception;

public class MissingDataException extends Exception {

	private static final long serialVersionUID = 6515231760922261633L;

	public MissingDataException(String message) {
		super(message);
	}
}
