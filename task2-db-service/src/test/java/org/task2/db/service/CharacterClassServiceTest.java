package org.task2.db.service;



import com.dbService.dto.CharacterClassDto;
import com.dbService.dto.CharacterClassResponse;
import com.dbService.entity.CharacterClass;
import com.dbService.repository.CharacterClassRepository;
import com.dbService.service.CharacterClassService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterClassServiceTest {

    @Mock
    private CharacterClassRepository repository;

    @InjectMocks
    private CharacterClassService service;

    private CharacterClass warrior;
    private CharacterClass mage;
    private CharacterClass paladin;
    private CharacterClass fireMage;

    @BeforeEach
    void setUp() {
        // Setup test data
        warrior = new CharacterClass(1L, "Warrior", "#FF0000", 0L);
        mage = new CharacterClass(2L, "Mage", "#0000FF", 0L);
        paladin = new CharacterClass(3L, "Paladin", "#FFFF00", 1L);
        fireMage = new CharacterClass(4L, "Fire Mage", "#FF4500", 2L);
    }

    @Test
    void getAllClasses_ShouldReturnAllClassesOrderedById() {
        // Arrange
        List<CharacterClass> mockClasses = Arrays.asList(warrior, mage, paladin, fireMage);
        when(repository.findAllByOrderByIdAsc()).thenReturn(mockClasses);

        // Act
        List<CharacterClassResponse> result = service.getAllClasses();

        // Assert
        assertEquals(4, result.size());
        assertEquals("Warrior", result.get(0).getName());
        assertEquals("Mage", result.get(1).getName());
        assertEquals("Paladin", result.get(2).getName());
        assertEquals("Fire Mage", result.get(3).getName());
        
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void getClassById_WithValidId_ShouldReturnClass() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(warrior));

        // Act
        CharacterClassResponse result = service.getClassById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Warrior", result.getName());
        assertEquals("#FF0000", result.getColor());
        assertEquals(0L, result.getParentId());
        
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getClassById_WithInvalidId_ShouldThrowException() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.getClassById(99L);
        });
        
        assertEquals("Class not found with ID: 99", exception.getMessage());
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void getClassHierarchy_ShouldReturnProperHierarchy() {
        // Arrange
        List<CharacterClass> mockClasses = Arrays.asList(warrior, mage, paladin, fireMage);
        when(repository.findAllByOrderByIdAsc()).thenReturn(mockClasses);

        // Act
        List<CharacterClassDto> result = service.getClassHierarchy();

        // Assert
        assertEquals(2, result.size()); // Should have 2 root classes
        
        // Verify Warrior hierarchy
        CharacterClassDto warriorDto = result.get(0);
        assertEquals("Warrior", warriorDto.getName());
        assertEquals(1, warriorDto.getSubClasses().size());
        assertEquals("Paladin", warriorDto.getSubClasses().get(0).getName());
        
        // Verify Mage hierarchy
        CharacterClassDto mageDto = result.get(1);
        assertEquals("Mage", mageDto.getName());
        assertEquals(1, mageDto.getSubClasses().size());
        assertEquals("Fire Mage", mageDto.getSubClasses().get(0).getName());
        
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void getClassHierarchy_WithNoSubclasses_ShouldReturnOnlyRootClasses() {
        // Arrange
        List<CharacterClass> mockClasses = Arrays.asList(warrior, mage);
        when(repository.findAllByOrderByIdAsc()).thenReturn(mockClasses);

        // Act
        List<CharacterClassDto> result = service.getClassHierarchy();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Warrior", result.get(0).getName());
        assertEquals(0, result.get(0).getSubClasses().size());
        assertEquals("Mage", result.get(1).getName());
        assertEquals(0, result.get(1).getSubClasses().size());
    }

    @Test
    void getClassHierarchy_WithOrphanedSubclass_ShouldNotIncludeOrphan() {
        // Arrange
        // Create an orphan class (parent doesn't exist)
        CharacterClass orphan = new CharacterClass(5L, "Orphan", "#000000", 99L);
        List<CharacterClass> mockClasses  = Arrays.asList(warrior, mage, orphan);
        when(repository.findAllByOrderByIdAsc()).thenReturn(mockClasses);

        // Act
        List<CharacterClassDto> result = service.getClassHierarchy();

        // Assert
        assertEquals(2, result.size()); // Only warrior and mage should be in root
        // Verify no subclasses were added to any root class
        assertEquals(0, result.get(0).getSubClasses().size());
        assertEquals(0, result.get(1).getSubClasses().size());
    }

    @Test
    void mapToResponse_ShouldCorrectlyMapEntityToResponse() throws Exception  {
        // Act
    	 Method method = CharacterClassService.class.getDeclaredMethod("mapToResponse", CharacterClass.class);
    	    method.setAccessible(true); // bypass private access

    	    CharacterClassResponse response = (CharacterClassResponse) method.invoke(service, warrior);


        // Assert
        assertEquals(warrior.getId(), response.getId());
        assertEquals(warrior.getName(), response.getName());
        assertEquals(warrior.getColor(), response.getColor());
        assertEquals(warrior.getParentId(), response.getParentId());
    }
}
