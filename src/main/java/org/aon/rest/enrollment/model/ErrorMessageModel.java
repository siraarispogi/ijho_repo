package org.aon.rest.enrollment.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessageModel {
	
	private String errorMessage;
	
	public ErrorMessageModel() {
	}
	
	public ErrorMessageModel(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
