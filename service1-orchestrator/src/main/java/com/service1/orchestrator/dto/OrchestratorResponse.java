package com.service1.orchestrator.dto;

public class OrchestratorResponse {
	 private String message;

	public OrchestratorResponse() {
		
	}

	public OrchestratorResponse(String message) {
	
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
