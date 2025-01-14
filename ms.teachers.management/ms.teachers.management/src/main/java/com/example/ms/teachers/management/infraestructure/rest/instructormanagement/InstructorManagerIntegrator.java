package com.example.ms.teachers.management.infraestructure.rest.instructormanagement;

import com.example.ms.teachers.management.core.port.in.dto.SalaryDto;
import com.example.ms.teachers.management.core.port.in.dto.SubjectDto;
import com.example.ms.teachers.management.core.port.out.InstructorManagerPortOut;
import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.InstructorManagementFeign;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.InstructorListToTeacherListOutputMapper;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.InstructorToTeacherOutputMapper;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper.TeacherToInstructorOutputMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstructorManagerIntegrator implements InstructorManagerPortOut {

    private final InstructorListToTeacherListOutputMapper instructorListToTeacherListOutputMapper;
    private final InstructorManagementFeign feign;
    private final InstructorToTeacherOutputMapper instructorToTeacherOutputMapper;
    private final TeacherToInstructorOutputMapper teacherToInstructorOutputMapper;

    @Override
    public TeacherListDtoOutput getTeachers(String authorization, String origin) {
        log.info("[ADAPTER OUT = TeacherManagementIntegrator.getTeachers] - try");
        try{
            var response = feign.getInstructor(authorization, origin);
            log.info("[ADAPTER OUT = TeacherManagementIntegrator.getTeachers] - Enviando a requisição para a operacao getTeachers da API Teacher Manager");
            return instructorListToTeacherListOutputMapper.responseGetTeacherListMapper(response);
        }catch (FeignException e){
            log.error("[ADAPTER OUT = TeacherManagementIntegrator.getTeachers] - Falha na chamada da operacao getTeachers da API Teacher Manager . erro: {}", e.getMessage());
            throw new RuntimeException("Falha na chamada da operação getTeachers da Teachers Manager", e.getCause());
        }
    }

    @Override
    public TeacherDtoOutput getTeacherById(String authorization, String origin, Integer id){
        log.info("[ADAPTER OUT = TeacherManagementIntegrator.getTeacherById  - try]");
        try{
            var response = feign.getInstructorById(authorization, origin, id);
            log.info("[ADAPTER OUT = TeacherManagementIntegrator.getTeacherById] - Enviando a requisição para a operacao getTeacherById da API Teacher Manager");
            return instructorToTeacherOutputMapper.responseGetTeacher(response);
        }catch (FeignException e){
            log.error("[ADAPTER OUT = TeacherManagementIntegrator.getTeacherById] - Falha na chamada da operacao getTeacherById da API Teacher Manager . erro: {}", e.getMessage());
            throw new RuntimeException("Falha na chamada da operação getTeacherById da Teachers Manager", e.getCause());
        }
    }

    @Override
    public TeacherDtoOutput createTeacherResponse(String authorization, String origin, Instructor instructor){
        log.info("[ADAPTER OUT = TeacherManagementIntegrator.createTeacherResponse  - try]");
        try{
            var response = feign.createInstructor(authorization, origin, instructor);
            log.info("[ADAPTER OUT = TeacherManagementIntegrator.createTeacherResponse] - Enviando a requisição para a operacao createTeacher da API Teacher Manager");
            return instructorToTeacherOutputMapper.responseGetTeacher(response);
        }catch (FeignException e){
            log.error("[ADAPTER OUT = TeacherManagementIntegrator.createTeacherResponse] - Falha na chamada da operacao getTeacherById da API Teacher Manager . erro: {}", e.getMessage());
            throw new RuntimeException("Falha na chamada da operação createTeacher da Teachers Manager", e.getCause());
        }
    }

}
