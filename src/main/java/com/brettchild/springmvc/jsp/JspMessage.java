package com.brettchild.springmvc.jsp;

public class JspMessage {

	private String code;
	private String message;
	private JspMessageLevel level;
	private String[] arguments;
	
	public JspMessage() {}
	
	public JspMessage(String code, String defaultMessage, String...arguments) {
		this.code = code;
		this.message = defaultMessage;
		this.level = JspMessageLevel.INFO;
		this.arguments = arguments;
	}
	
	public JspMessage(String code, String defaultMessage, JspMessageLevel level, String...arguments) {
		this.code = code;
		this.message = defaultMessage;
		this.level = level;
		this.arguments = arguments;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JspMessageLevel getLevel() {
		return level;
	}

	public void setLevel(JspMessageLevel level) {
		this.level = level;
	}
	
	

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "JspMessage [code=" + code + ", message="
				+ message + ", level=" + level + ", arguments=" + arguments + "]";
	}


	
	
}
