package com.service1.orchestrator.dto;

public class OrchestratorResponse {
	 private String response;

	public OrchestratorResponse() {
		
	}

	public OrchestratorResponse(String response) {
	
		this.response = response;
	}

	public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
