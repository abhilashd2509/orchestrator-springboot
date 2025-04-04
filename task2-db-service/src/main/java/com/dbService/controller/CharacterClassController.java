package com.dbService.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dbService.annotation.LogMethodParam;
import com.dbService.dto.CharacterClassDto;
import com.dbService.dto.CharacterClassResponse;
import com.dbService.service.CharacterClassService;


@RestController
@RequestMapping("/api/classes")
@Tag(name = "Character Class Controller", description = "Manages character class hierarchy")
public class CharacterClassController {
	
	private final CharacterClassService service;

    public CharacterClassController(CharacterClassService service) {

		this.service = service;
	}
    

    
    

    @GetMapping
    @Operation(summary = "Get all classes as flat list")
    public ResponseEntity<List<CharacterClassResponse>> getAllClasses() {
        return ResponseEntity.ok(service.getAllClasses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get class by ID")
    public ResponseEntity<CharacterClassResponse> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClassById(id));
    }

    @GetMapping("/hierarchy")
    @Operation(summary = "Get class hierarchy as nested structure")
    @LogMethodParam
    public ResponseEntity<List<CharacterClassDto>> getClassHierarchy() {
        return ResponseEntity.ok(service.getClassHierarchy());
    }
    
    
    
}
