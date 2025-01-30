package com.example.ms.teachers.management.instructormanagement.feign;

import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.FallbackInstructorManagement;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.InstructorManagementFeign;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FallbackInstructorManagementTest {

    @Autowired
    private final FallbackInstructorManagement fallbackInstructorManagement = new FallbackInstructorManagement();

    @MockBean
    private Instructor instructor;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        objectMapper = new ObjectMapper();
    }

    @Test
    public void returnExceptionGetInstructor() throws Exception {

        InstructorManagementFeign fallback = fallbackInstructorManagement.create(new RuntimeException("Test Exception"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            fallback.getInstructor("Bearer token", "origin");
        });

        assertEquals("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes", thrown.getMessage());
    }

    @Test
    public void returnExceptionGetInstructorById() throws Exception {
        InstructorManagementFeign fallback = fallbackInstructorManagement.create(new RuntimeException("Test Exception"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            fallback.getInstructorById("Bearer token", "origin", 1);
        });

        assertEquals("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes", thrown.getMessage());
    }

    @Test
    public void returnExceptionCreateInstructor() throws Exception{
        InstructorManagementFeign fallback = fallbackInstructorManagement.create(new RuntimeException("Test Exception"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            fallback.createInstructor("Bearer token", "origin", instructor);
        });

        assertEquals("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes", thrown.getMessage());
    }
}
