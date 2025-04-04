package com.service1.orchestrator.dto;

import jakarta.validation.constraints.NotBlank;

public record NameRequest  (
	    @NotBlank(message = "Name is required") String name,
	    @NotBlank(message = "Surname is required") String surname
	) {}
