package org.service3.name;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.service3.name.dto.NameRequest;

class NameRequestTest {

    @Test
    void getName_shouldReturnName() {
        NameRequest request = new NameRequest("John", "Doe");
        assertEquals("John", request.getName());
    }

    @Test
    void getSurname_shouldReturnSurname() {
        NameRequest request = new NameRequest("John", "Doe");
        assertEquals("Doe", request.getSurname());
    }

    @Test
    void setName_shouldUpdateName() {
        NameRequest request = new NameRequest("John", "Doe");
        request.setName("Jane");
        assertEquals("Jane", request.getName());
    }

    @Test
    void setSurname_shouldUpdateSurname() {
        NameRequest request = new NameRequest("John", "Doe");
        request.setSurname("Smith");
        assertEquals("Smith", request.getSurname());
    }
}