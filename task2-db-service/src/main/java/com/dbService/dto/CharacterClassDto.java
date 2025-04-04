package com.dbService.dto;

import java.util.List;

public class CharacterClassDto {
	 private String name;
	    private String color;
	    private List<CharacterClassDto> subClasses;
		public CharacterClassDto() {
			super();
		}
		public CharacterClassDto(String name, String color, List<CharacterClassDto> subClasses) {
			super();
			this.name = name;
			this.color = color;
			this.subClasses = subClasses;
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
		public List<CharacterClassDto> getSubClasses() {
			return subClasses;
		}
		public void setSubClasses(List<CharacterClassDto> subClasses) {
			this.subClasses = subClasses;
		}
} 