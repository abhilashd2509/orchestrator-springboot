package org.task2.db.service;
import com.dbService.controller.CharacterClassController;
import com.dbService.dto.CharacterClassDto;
import com.dbService.dto.CharacterClassResponse;
import com.dbService.service.CharacterClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterClassControllerTest {

    @Mock
    private CharacterClassService service;

    @InjectMocks
    private CharacterClassController controller;

    private List<CharacterClassResponse> classResponses;
    private List<CharacterClassDto> classDtos;

    @BeforeEach
    void setUp() {
    	
    	
    	classResponses = Arrays.asList(
                new CharacterClassResponse(1L, "Warrior", "color1", 1L),
                new CharacterClassResponse(2L, "Mage", "color2", null)
        );


         classDtos = Arrays.asList(
                  new CharacterClassDto("Warrior", "color3", Arrays.asList(
                          new CharacterClassDto("Berserker", "color4", null)
                  )),
                  new CharacterClassDto("Mage", "color5", null)
          );

    }

    @Test
    void getAllClasses_shouldReturnOkAndListOfClasses() {
        when(service.getAllClasses()).thenReturn(classResponses);

        ResponseEntity<List<CharacterClassResponse>> response = controller.getAllClasses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(classResponses, response.getBody());
        verify(service, times(1)).getAllClasses();
    }

    @Test
    void getClassById_shouldReturnOkAndClassById() {
        long id = 1L;
        when(service.getClassById(id)).thenReturn(classResponses.get(0));

        ResponseEntity<CharacterClassResponse> response = controller.getClassById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(classResponses.get(0), response.getBody());
        verify(service, times(1)).getClassById(id);
    }

    @Test
    void getClassHierarchy_shouldReturnOkAndClassHierarchy() {
        when(service.getClassHierarchy()).thenReturn(classDtos);

        ResponseEntity<List<CharacterClassDto>> response = controller.getClassHierarchy();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(classDtos, response.getBody());
        verify(service, times(1)).getClassHierarchy();
    }
}