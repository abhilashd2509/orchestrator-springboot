package com.dbService.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dbService.dto.CharacterClassDto;
import com.dbService.dto.CharacterClassResponse;
import com.dbService.entity.CharacterClass;
import com.dbService.repository.CharacterClassRepository;


@Service
public class CharacterClassService {

    private final CharacterClassRepository repository;
    
    private static final Logger log = LogManager.getLogger(CharacterClassService.class);

    public CharacterClassService(CharacterClassRepository repository) {
	
		this.repository = repository;
	}

    public List<CharacterClassResponse> getAllClasses() {
        log.info("Fetching all character classes");
        return repository.findAllByOrderByIdAsc().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    private CharacterClassResponse mapToResponse(CharacterClass characterClass) {
        return CharacterClassResponse.builder()
                .id(characterClass.getId())
                .name(characterClass.getName())
                .color(characterClass.getColor())
                .parentId(characterClass.getParentId())
                .build();
    }

    public CharacterClassResponse getClassById(Long id) {
        log.info("Fetching character class by ID: {}", id);
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));
    }

    public List<CharacterClassDto> getClassHierarchy() {
        log.info("Building character class hierarchy");
        List<CharacterClass> allClasses = repository.findAllByOrderByIdAsc();
        Map<Long, CharacterClassDto> dtoMap = new HashMap<>();
        List<CharacterClassDto> rootClasses = new ArrayList<>();

        // Create all DTOs
        for (CharacterClass characterClass : allClasses) {
            CharacterClassDto dto = new CharacterClassDto();
            dto.setName(characterClass.getName());
            dto.setColor(characterClass.getColor());
            dto.setSubClasses(new ArrayList<>());
            dtoMap.put(characterClass.getId(), dto);
        }

        // Build hierarchy
        for (CharacterClass characterClass : allClasses) {
            CharacterClassDto dto = dtoMap.get(characterClass.getId());
            if (characterClass.getParentId() == 0) {
                rootClasses.add(dto);
            } else {
                CharacterClassDto parentDto = dtoMap.get(characterClass.getParentId());
                if (parentDto != null) {
                    parentDto.getSubClasses().add(dto);
                }
            }
        }

        return rootClasses;
    }

 	
}
