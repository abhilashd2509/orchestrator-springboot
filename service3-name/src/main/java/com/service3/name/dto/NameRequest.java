package com.service3.name.dto;

import jakarta.validation.constraints.NotBlank;

public class NameRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Surname is required")
    private String surname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public NameRequest(@NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Surname is required") String surname) {
		
		this.name = name;
		this.surname = surname;
	}
}
