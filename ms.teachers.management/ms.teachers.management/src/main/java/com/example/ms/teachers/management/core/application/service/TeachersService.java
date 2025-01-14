package com.example.ms.teachers.management.core.application.service;

import com.example.ms.teachers.management.core.port.in.TeacherPortIn;
import com.example.ms.teachers.management.core.port.in.dto.TeacherDto;
import com.example.ms.teachers.management.core.port.in.dto.TeacherListDto;
import com.example.ms.teachers.management.core.port.out.InstructorManagerPortOut;
import com.example.ms.teachers.management.core.port.out.SendEventProducerPortOut;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeachersService implements TeacherPortIn {

    private final InstructorManagerPortOut instructorManagerPortOut;

    private final SendEventProducerPortOut sendEventProducerPortOut;

    private final ModelMapper modelMapper;

    @Override
    public TeacherListDto listTeacher(String authorization, String origin){
        log.info("[SERVICE - teacherManagerPorOut.getTeachers] - Executar a chamada da API de TeacherManagement");
        var teacherListOutput = instructorManagerPortOut.getTeachers(authorization, origin);
        log.info("[SERVICE - teacherManagerPorOut.getTeachers] - Chamada da API de TeacherManagement realizada com sucesso");

        sendEventProducerPortOut.sendTeacherInfoEvent(teacherListOutput);

        return modelMapper.map(teacherListOutput, TeacherListDto.class);
    }

    @Override
    public TeacherDto getTeacherById(String authorization, String origin, Integer id){
        log.info("[SERVICE - teacherManagerPorOut.getTeacherById] - Executar a chamada da API de TeacherManagement");
        var teacherOutput = instructorManagerPortOut.getTeacherById(authorization, origin, id);
        log.info("[SERVICE - teacherManagerPorOut.getTeacherById] - Chamada da API de TeacherManagement realizada com sucesso");

        return modelMapper.map(teacherOutput, TeacherDto.class);
    }


    @Override
    public TeacherDto createTeacher(String authorization, String origin, Instructor instructor){
        log.info("[SERVICE - teacherManagerPorOut.createTeacher] - Executar a chamada da API de TeacherManagement");
        var teacherOutput = instructorManagerPortOut.createTeacherResponse(authorization, origin, instructor);
        log.info("[SERVICE - teacherManagerPorOut.createTeacher] - Chamada da API de TeacherManagement realizada com sucesso");
        return modelMapper.map(teacherOutput, TeacherDto.class);
    }
}
