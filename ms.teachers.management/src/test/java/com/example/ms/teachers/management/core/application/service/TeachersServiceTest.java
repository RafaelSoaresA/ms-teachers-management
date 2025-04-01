package com.example.ms.teachers.management.core.application.service;


import com.example.ms.teachers.management.core.port.out.InstructorManagerPortOut;
import com.example.ms.teachers.management.core.port.out.SendEventProducerPortOut;
import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeachersServiceTest {

    @Mock
    private InstructorManagerPortOut instructorManagerPortOut;

    @Mock
    private SendEventProducerPortOut sendEventProducerPortOut;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TeachersService teachersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve listar professores com sucesso")
    void listTeacherSuccess() {
        TeacherListDtoOutput mockTeacherListDto = mock(TeacherListDtoOutput.class);

        when(instructorManagerPortOut.getTeachers(any(), any())).thenReturn(mockTeacherListDto);
        when(modelMapper.map(any(), eq(TeacherListDtoOutput.class))).thenReturn(mockTeacherListDto);

        var result = teachersService.listTeacher("auth", "origin");

        assertNull(result);
        verify(instructorManagerPortOut, times(1)).getTeachers(any(), any());
        verify(sendEventProducerPortOut, times(1)).sendTeacherInfoEvent(any());
    }

    @Test
    @DisplayName("Deve retornar professor pelo id com sucesso")
    void getTeacherByIdSuccess() {
        TeacherDtoOutput mockTeacherDto = mock(TeacherDtoOutput.class);

        when(instructorManagerPortOut.getTeacherById(any(), any(), anyInt())).thenReturn(mockTeacherDto);
        when(modelMapper.map(any(), eq(TeacherDtoOutput.class))).thenReturn(mockTeacherDto);

        var result = teachersService.getTeacherById("auth", "origin", 1);

        assertNull(result);
        verify(instructorManagerPortOut, times(1)).getTeacherById(any(), any(), anyInt());
    }

    @Test
    @DisplayName("Deve criar professor com sucesso")
    void createTeacherSuccess() {
        TeacherDtoOutput mockTeacherDto = mock(TeacherDtoOutput.class);

        when(instructorManagerPortOut.createTeacherResponse(any(), any(), any())).thenReturn(mockTeacherDto);
        when(modelMapper.map(any(), eq(TeacherDtoOutput.class))).thenReturn(mockTeacherDto);

        var result = teachersService.createTeacher("auth", "origin", new Instructor());

        assertNull(result);
        verify(instructorManagerPortOut, times(1)).createTeacherResponse(any(), any(), any());
    }
}