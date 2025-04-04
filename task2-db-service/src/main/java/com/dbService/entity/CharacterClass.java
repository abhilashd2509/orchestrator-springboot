package com.dbService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "character_classes")
public class CharacterClass {
    @Id
    private Long id;
    private String name;
    private String color;
    private Long parentId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public CharacterClass(Long id, String name, String color, Long parentId) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.parentId = parentId;
	}
	public CharacterClass() {
		
	}

	

 
}