package com.dbService.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dbService.entity.CharacterClass;
import com.dbService.repository.CharacterClassRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CharacterClassRepository repository) {
        return args -> {
            if (repository.count() == 0) {
            	 repository.save(new CharacterClass(1L, "Warrior", "red", 0L));
                 repository.save(new CharacterClass(2L, "Wizard", "green", 0L));
                 repository.save(new CharacterClass(3L, "Priest", "white", 0L));
                 repository.save(new CharacterClass(4L, "Rogue", "yellow", 0L));
                 repository.save(new CharacterClass(5L, "Fighter", "blue", 1L));
                 repository.save(new CharacterClass(6L, "Paladin", "lightblue", 1L));
                 repository.save(new CharacterClass(7L, "Ranger", "lightgreen", 1L));
                 repository.save(new CharacterClass(8L, "Mage", "grey", 2L));
                 repository.save(new CharacterClass(9L, "Specialist wizard", "lightgrey", 2L));
                 repository.save(new CharacterClass(10L, "Cleric", "red", 3L));
                 repository.save(new CharacterClass(11L, "Druid", "green", 3L));
                 repository.save(new CharacterClass(12L, "Priest of specific mythos", "white", 3L));
                 repository.save(new CharacterClass(13L, "Thief", "yellow", 4L));
                 repository.save(new CharacterClass(14L, "Bard", "blue", 4L));
                 repository.save(new CharacterClass(15L, "Assassin", "lightblue", 13L));
            }
        };
    }
}

