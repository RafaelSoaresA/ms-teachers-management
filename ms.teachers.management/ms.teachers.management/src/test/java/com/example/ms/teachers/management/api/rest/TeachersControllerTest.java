package com.example.ms.teachers.management.api.rest;

import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.FallbackInstructorManagement;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TeachersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private Instructor instructor;
    @Autowired
    private FallbackInstructorManagement fallbackInstructorManagement;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Deve retornar a lista de professores")
    public void returnTeachersList() throws Exception {

        mockMvc.perform(get("/teachers")
                .header("Authorization", "aaa")
                .header("Origin", "eee"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar o professor pelo id")
    public void returnTeacherById() throws Exception {

        mockMvc.perform(get("/teachers/{id}", 1)
                .header("Authorization", "aaa")
                .header("Origin", "eee"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve criar um professor")
    public void createTeacher() throws Exception {


        mockMvc.perform(post("/teachers")
                .header("Authorization", "aaa")
                .header("Origin", "eee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(instructor)))
                .andExpect(status().isOk());

    }
}
