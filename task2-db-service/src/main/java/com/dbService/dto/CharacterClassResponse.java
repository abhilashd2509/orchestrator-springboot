package com.dbService.dto;

public class CharacterClassResponse {
    private Long id;
    private String name;
    private String color;
    private Long parentId;

    // Private constructor
    private CharacterClassResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.color = builder.color;
        this.parentId = builder.parentId;
    }
    
    public CharacterClassResponse(Long id, String name, String color, Long parentId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.parentId = parentId;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public Long getParentId() { return parentId; }

    // Builder class
    public static class Builder {
        private Long id;
        private String name;
        private String color;
        private Long parentId;

        public Builder id(Long Long) {
            this.id = Long;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public CharacterClassResponse build() {
            return new CharacterClassResponse(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}