package com.example.ms.teachers.management.infraestructure.rest.instructormanagement;

import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.InstructorManagementFeign;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.InstructorList;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.InstructorListToTeacherListOutputMapper;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.InstructorToTeacherOutputMapper;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstructorManagerIntegratorTest {

    @Mock
    private InstructorListToTeacherListOutputMapper instructorListToTeacherListOutputMapper;

    @Mock
    private InstructorManagementFeign feign;

    @Mock
    private InstructorToTeacherOutputMapper instructorToTeacherOutputMapper;

    @InjectMocks
    private InstructorManagerIntegrator instructorManagerIntegrator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTeachers_success() {
        String authorization = "auth";
        String origin = "origin";
        var response = mock(TeacherListDtoOutput.class);
        when(feign.getInstructor(authorization, origin)).thenReturn(mock(InstructorList.class));
        when(instructorListToTeacherListOutputMapper.responseGetTeacherListMapper(any(InstructorList.class))).thenReturn(response);

        var result = instructorManagerIntegrator.getTeachers(authorization, origin);

        assertNotNull(result);
        assertEquals(response, result);
        verify(feign).getInstructor(authorization, origin);
        verify(instructorListToTeacherListOutputMapper).responseGetTeacherListMapper(any(InstructorList.class));
    }

    @Test
    void getTeachers_feignException() {
        String authorization = "auth";
        String origin = "origin";
        when(feign.getInstructor(authorization, origin)).thenThrow(FeignException.class);

        assertThrows(RuntimeException.class, () -> instructorManagerIntegrator.getTeachers(authorization, origin));
        verify(feign).getInstructor(authorization, origin);
    }

    @Test
    void getTeacherById_success() {
        String authorization = "auth";
        String origin = "origin";
        Integer id = 1;
        var response = mock(TeacherDtoOutput.class);
        when(feign.getInstructorById(authorization, origin, id)).thenReturn(mock(Instructor.class));
        when(instructorToTeacherOutputMapper.responseGetTeacher(any(Instructor.class))).thenReturn(response);

        var result = instructorManagerIntegrator.getTeacherById(authorization, origin, id);

        assertNotNull(result);
        assertEquals(response, result);
        verify(feign).getInstructorById(authorization, origin, id);
        verify(instructorToTeacherOutputMapper).responseGetTeacher(any(Instructor.class));
    }

    @Test
    void getTeacherById_feignException() {
        String authorization = "auth";
        String origin = "origin";
        Integer id = 1;
        when(feign.getInstructorById(authorization, origin, id)).thenThrow(FeignException.class);

        assertThrows(RuntimeException.class, () -> instructorManagerIntegrator.getTeacherById(authorization, origin, id));
        verify(feign).getInstructorById(authorization, origin, id);
    }

    @Test
    void createTeacherResponse_success() {
        String authorization = "auth";
        String origin = "origin";
        var instructor = mock(Instructor.class);
        var response = mock(TeacherDtoOutput.class);
        when(feign.createInstructor(authorization, origin, instructor)).thenReturn(mock(Instructor.class));
        when(instructorToTeacherOutputMapper.responseGetTeacher(any(Instructor.class))).thenReturn(response);

        var result = instructorManagerIntegrator.createTeacherResponse(authorization, origin, instructor);

        assertNotNull(result);
        assertEquals(response, result);
        verify(feign).createInstructor(authorization, origin, instructor);
        verify(instructorToTeacherOutputMapper).responseGetTeacher(any(Instructor.class));
    }

    @Test
    void createTeacherResponse_feignException() {
        String authorization = "auth";
        String origin = "origin";
        var instructor = mock(Instructor.class);
        when(feign.createInstructor(authorization, origin, instructor)).thenThrow(FeignException.class);

        assertThrows(RuntimeException.class, () -> instructorManagerIntegrator.createTeacherResponse(authorization, origin, instructor));
        verify(feign).createInstructor(authorization, origin, instructor);
    }
}