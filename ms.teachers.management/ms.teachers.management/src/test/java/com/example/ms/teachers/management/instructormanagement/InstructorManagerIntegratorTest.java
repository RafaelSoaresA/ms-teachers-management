package com.example.ms.teachers.management.instructormanagement;

import com.example.ms.teachers.management.core.port.out.InstructorManagerPortOut;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.InstructorManagerIntegrator;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.FallbackInstructorManagement;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.InstructorManagementFeign;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.InstructorListToTeacherListOutputMapper;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.InstructorToTeacherOutputMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class InstructorManagerIntegratorTest {

    @MockBean
    private Instructor instructor;

    private ObjectMapper objectMapper;

    @Autowired
    InstructorManagementFeign objfeign;

    @Autowired
    private InstructorManagerIntegrator instructorManagerIntegrator = new InstructorManagerIntegrator(null, objfeign, null, null);


    @Autowired
    private InstructorListToTeacherListOutputMapper instructorListToTeacherListOutputMapper;
    @Autowired
    private FallbackInstructorManagement fallbackInstructorManagement;

    @BeforeEach
    public void setup(){
        objectMapper = new ObjectMapper();
    }

    /*@Test
    public void returnCatchExceptionGetTeachers() throws Exception {

        String authorization = "Bearer token";
        String origin = "origin";
        //FeignException feignException = new FeignException(1,""); // = new RuntimeException("Falha na chamada da operação getTeachers da Teachers Manager", null);

        // Cria uma Response com um status de erro HTTP


        // Cria uma FeignException com base na resposta
        FeignException feignException = FeignException.errorStatus("getInstructor", response);

        when(objfeign.getInstructor(authorization, origin)).thenThrow(feignException);

        // Act & Assert
        FeignException thrownException = assertThrows(FeignException.class, () -> {
            instructorManagerIntegrator.getTeachers(authorization, origin);
        });

        assertEquals("Falha na chamada da operação getTeachers da Teachers Manager", thrownException.getMessage());
       // assertEquals(feignException.getCause(), thrownException.getCause());
    }*/
}
